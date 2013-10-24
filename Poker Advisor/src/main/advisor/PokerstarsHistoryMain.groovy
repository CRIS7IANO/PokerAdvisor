package advisor

import advisor.parser.pokerStars.PokerstarsHistoryParser;

class PokerstarsHistoryMain {
	public static void main(String[] args) {
		
		PokerstarsHistoryParser parser = new PokerstarsHistoryParser('/Users/sean/Desktop/Poker History/tinny4u/HH20130907 Gaika VII - 1-2 - Play Money No Limit Hold\'em.txt')
		def parsedHands = parser.parse()
		
		println "${parsedHands.size}"
		
		
		def hand = parsedHands.get(1)
		
		println "${hand.tableName}"
		println '*** Preflop ****'
		println hand.preflop
		
		println '*** Postflop ****'
		println hand.postflop
		
		println '*** Turn ****'
		println hand.turn
		
		println '*** River ****'
		println hand.river
		
		println '*** Show down ****'
		println hand.showDown
		
	}
}
