package advisor.parser.pokerStars

import advisor.parser.DOM.Act
import advisor.parser.DOM.Hand;
import advisor.parser.DOM.Player;
import advisor.parser.DOM.Postflop
import advisor.parser.DOM.Preflop
import advisor.parser.DOM.River
import advisor.parser.DOM.ShowDown
import advisor.parser.DOM.Street
import advisor.parser.DOM.Turn

class PokerstarsHistoryParser {
	
	private String historyFilePath;
	
	Map players

	public PokerstarsHistoryParser(String historyFilePath) {
		
		this.historyFilePath = historyFilePath
		
		this.players = [:]
		
	}
	
	public List parse() {
		
		this.players.clear()
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(historyFilePath))
		
		String sCurrentLine;
		
		def rawText = []
 
		while ((sCurrentLine = bufferedReader.readLine()) != null) {		
			
			rawText.add(sCurrentLine)
			
		}	
		
		try {
			if (bufferedReader != null) 
				bufferedReader.close()
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		def handStartIndexes = []

		rawText.eachWithIndex() { obj, i -> 
			if(obj.startsWith('PokerStars Hand')) {
				handStartIndexes.add(i)
			}	
		};	
	
		def parsedHands = []
		
		for (int i = 0; i < handStartIndexes.size - 1; i++) {

			int start = handStartIndexes.get(i)
			int end = handStartIndexes.get(i+1)
			

			def hand = []
			for (int j = 0; j < end - start; j++) {
				hand.add(rawText.get(start + j))		
			}
			
		
			parsedHands.add(buildHand(hand))
			
			
			
		}
		
		return parsedHands
		
	}
	
	private Hand buildHand(List handDetailList) {
		
		
		//Build the summary of the hand
		def parsedHand = new Hand()
		
		parsedHand.tableName = handDetailList.get(1)
		
		handDetailList.find {
			
			if (it.contains("*** HOLE CARDS ***")) {
				return true
			} else if(it.startsWith('Seat 1:')) {
			
				parsedHand.seat1 = obtainPlayerSummary(it)
				
			} else if(it.startsWith('Seat 2:')) {
			
				parsedHand.seat2 = obtainPlayerSummary(it)
				
			} else if(it.startsWith('Seat 3:')) {
			
				parsedHand.seat3 = obtainPlayerSummary(it)
				
			} else if(it.startsWith('Seat 4:')) {
			
				parsedHand.seat4 = obtainPlayerSummary(it)
				
			} else if(it.startsWith('Seat 5:')) {
			
				parsedHand.seat5 = obtainPlayerSummary(it)
				
			} else if(it.startsWith('Seat 6:')) {
			
				parsedHand.seat6 = obtainPlayerSummary(it)
				
			} else if(it.startsWith('Seat 7:')) {
			
				parsedHand.seat7 = obtainPlayerSummary(it)
				
			} else if(it.startsWith('Seat 8:')) {
			
				parsedHand.seat8 = obtainPlayerSummary(it)
				
			} else if(it.startsWith('Seat 9:')) {
			
				parsedHand.seat9 = obtainPlayerSummary(it)
				
			}
			return false
			
		}
		
		
		//preflop
		parsedHand.preflop = new Preflop()
		parseStreet(handDetailList, parsedHand.preflop, '*** HOLE CARDS ***', '*** FLOP ***')
		
		//postFlop
		parsedHand.postflop = new Postflop()
		parseStreet(handDetailList, parsedHand.postflop, '*** FLOP ***', '*** TURN ***')
		
		//turn
		parsedHand.turn = new Turn()
		parseStreet(handDetailList, parsedHand.turn, '*** TURN ***', '*** RIVER ***')
		
		//river
		parsedHand.river = new River()
		parseStreet(handDetailList, parsedHand.river, '*** RIVER ***', '*** SHOW DOWN ***')
		
		//show down
		parsedHand.showDown = new ShowDown()
		parseStreet(handDetailList, parsedHand.river, '*** SHOW DOWN ***', '*** SUMMARY ***')
		
		
		return parsedHand
		
	}
	
	private Player obtainPlayerSummary(String txt) {
			
		def name = txt.substring(7, txt.indexOf("(") - 1).trim()
		
		if(players.get(name)) {
			return players.get(name)
		} else {
			def player = new Player()
			player.name = name
				
			String chipsStr = txt.substring(txt.indexOf("(") + 1, txt.indexOf(")")).replaceFirst("in chips", "").trim()
			player.chips = Integer.valueOf(chipsStr)
				
			return player
		}

	}
	
	

	private void parseStreet(def handDetailList, Street street, String streetStart, String streetFinish) {
		
		//find the start index for preflop
		int index = handDetailList.findIndexOf {	
			if(it.contains(streetStart)) {
				return true
			} else {
				return false
			}
		}
		
		
		handDetailList.findIndexOf(index + 1) {
			//exit when we encounter the flop
			if(it.contains(streetFinish)){
				return true
			} else {
			
				def player
				def name
				
				//Owning player
				//TODO set hole cards
				if(it.contains("Dealt to ")) {
					
					name = it.replace("Dealt to ", "")
					name = name.substring(0, name.indexOf("[")).trim()
					
					if(players.get(name)) {
						player = players.get(name)
					} else {
						player = new Player(name: name)
					}
					
				}
				
				//find player
				if(it.contains(":")) {
					
					name = it.substring(0, it.indexOf(":"))
					
					if(players.get(name)) {
						player = players.get(name)
					} else {
						player = new Player(name: name)
					}
					
					//TODO find action
					
					def act = new Act(player: player)
					street.acts.add(act)
					
				}
				

				return false
			}
			
		}
	
		
	}
		
}
