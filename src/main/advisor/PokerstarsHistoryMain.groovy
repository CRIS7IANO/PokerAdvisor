package advisor

import advisor.analyser.preflop.ChenPreflopAnalyser
import advisor.analyser.preflop.HandGroup
import advisor.parser.pokerStars.PokerstarsHistoryParser;

class PokerstarsHistoryMain {
	public static void main(String[] args) {

		PokerstarsHistoryParser parser = new PokerstarsHistoryParser(args[0], args[1])
		def parsedHands = parser.parse()
		
		println "${parsedHands.size}"
		
		
		def hand = parsedHands.get(1)
		
		parsedHands.each {
			
			println "${it.tableName}"
			println "${it.player.name}"
			println "${it.player.holeCardOne}"
			println "${it.player.holeCardTwo}"
			println "${it.player.name}"
			
			
			ChenPreflopAnalyser analyser = new ChenPreflopAnalyser()
			
			HandGroup handGroup = analyser.analyse(it.player.holeCardOne, it.player.holeCardTwo)
			
			println handGroup.description()
			
		}	
		
	}
}
