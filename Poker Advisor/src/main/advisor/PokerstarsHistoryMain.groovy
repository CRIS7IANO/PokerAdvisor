package advisor

class PokerstarsHistoryMain {
	public static void main(String[] args) {
		
		PokerstarsHistoryParser parser = new PokerstarsHistoryParser('/Users/sean/Desktop/Poker History/tinny4u/HH20130907 Gaika VII - 1-2 - Play Money No Limit Hold\'em.txt')
		def parsedHands = parser.parse()
		
		println "${parsedHands.size}"
		
		def hand = parsedHands.get(2)
		println hand.tableName
		println hand.seat1
		println hand.seat2
		println hand.seat3
		println hand.seat4
		println hand.seat5
		println hand.seat6
		println hand.seat7
		println hand.seat8
		println hand.seat9
		
		
	}
}
