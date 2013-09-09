package advisor

public enum Suit {
	DIAMONDS,
	HEARTS,
	SPADES,
	CLUBS;
	
	
	public static convertSuitStr (String suitStr) {
		
		Suit suit = null;
		
		switch (suitStr.toLowerCase()) {
			case 'h': suit = Suit.HEARTS
					 break;
			case 'd': suit = Suit.DIAMONDS
					 break;
			case 's': suit = Suit.SPADES
					 break;
			case 'c': suit = Suit.CLUBS
					 break;
			
		}
		
		return suit
		
	}
}
