package advisor

import gameMechanics.Hand
import gameMechanics.Player

class PokerstarsHistoryParser {
	
	private String historyFilePath;
	
	private Map players

	public PokerstarsHistoryParser(String historyFilePath) {
		
		this.historyFilePath = historyFilePath
		
		players = [:]
	}
	
	public List parse() {
		
		players.clear()
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(historyFilePath))
		
		String sCurrentLine;
		
		def rawText = []
 
		while ((sCurrentLine = bufferedReader.readLine()) != null) {		
			
			rawText.add(sCurrentLine)
			
		}	
		
		try {
			if (bufferedReader != null)bufferedReader.close();
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
		
		def parsedHand = new Hand()
		
		parsedHand.tableName = handDetailList.get(1)
		
		handDetailList.find {
			
			if(it.contains("HOLE CARDS")) {
				return true
			} else if(it.startsWith('Seat 1:')) {
			
				parsedHand.seat1 = obtainPlayer(it)
				
			} else if(it.startsWith('Seat 2:')) {
			
				parsedHand.seat2 = obtainPlayer(it)
				
			} else if(it.startsWith('Seat 3:')) {
			
				parsedHand.seat3 = obtainPlayer(it)
				
			} else if(it.startsWith('Seat 4:')) {
			
				parsedHand.seat4 = obtainPlayer(it)
				
			} else if(it.startsWith('Seat 5:')) {
			
				parsedHand.seat5 = obtainPlayer(it)
				
			} else if(it.startsWith('Seat 6:')) {
			
				parsedHand.seat6 = obtainPlayer(it)
				
			} else if(it.startsWith('Seat 7:')) {
			
				parsedHand.seat7 = obtainPlayer(it)
				
			} else if(it.startsWith('Seat 8:')) {
			
				parsedHand.seat8 = obtainPlayer(it)
				
			} else if(it.startsWith('Seat 9:')) {
			
				parsedHand.seat9 = obtainPlayer(it)
				
			}
			return false
			
		}
		
		return parsedHand
		
	}
	
	private Player obtainPlayer(String txt) {
		
		//check the player cache
		if(players.get(txt)) {
			return players.get(txt)
		} else {
			def player = new Player(name: txt.substring(7, txt.indexOf("(") - 1).trim())
			players.put(player.name, player)
			return player
		}

	}
}
