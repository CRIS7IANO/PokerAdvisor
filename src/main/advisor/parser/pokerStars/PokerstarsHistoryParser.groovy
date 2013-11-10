package advisor.parser.pokerStars

import advisor.parser.DOM.Card
import advisor.parser.DOM.Face
import advisor.parser.DOM.Suit
import advisor.parser.DOM.Hand
import advisor.parser.DOM.Player
import java.util.regex.Matcher
import java.util.regex.Pattern

class PokerstarsHistoryParser {
	
	private String historyFilePath
	private String targetPlayer

	public PokerstarsHistoryParser(String historyFilePath, String targetPlayer) {
		
		this.historyFilePath = historyFilePath
		this.targetPlayer = targetPlayer
		
	}
	
	public List parse() {
		
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
			
		
			parsedHands.add(buildHand(hand, targetPlayer))
			
			
			
		}
		
		return parsedHands
		
	}
	
	private Hand buildHand(List handDetailList, String targetPlayer) {
		
		
		//Build the summary of the hand
		def parsedHand = new Hand()
		
		parsedHand.tableName = handDetailList.get(1)
		parsedHand.player = new Player(name: targetPlayer)
		
		//preflop
		parseStreet(handDetailList, parsedHand.player, '*** HOLE CARDS ***', '*** FLOP ***')
			
		return parsedHand
		
	}

	private void parseStreet(def handDetailList, Player player, String streetStart, String streetFinish) {
		
		//find the start index for the street
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
			
		
				
				//get the hole cards
				if(it.contains("Dealt to ${targetPlayer}")) {
					
					//Dealt to tinny4u [Qc Ks]
					def pattern = ~/\[(.*?)\]/
				
					Matcher matcher = pattern.matcher(it);
					if (matcher.find()) {
						
						def cardChars = matcher.group(1).replaceAll("\\s+","").getChars()
						
					
						
						player.holeCardOne = new Card(Face.convertFaceStr (cardChars[0].toString()), Suit.convertSuitStr(cardChars[1].toString()))
				
						player.holeCardTwo = new Card(Face.convertFaceStr (cardChars[2].toString()), Suit.convertSuitStr(cardChars[3].toString()))
					
						
					}
					
				}
				
				
				return false
			}
			
		}
	
		
	}
		
}
