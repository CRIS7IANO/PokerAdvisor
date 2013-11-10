package advisor.parser.DOM


class Card {
	
	private Face face;
	private Suit suit;
	
	public Card (Face face, Suit suit) {
		this.face = face
		this.suit = suit
	}
	
	public String toString() {
		return "${this.face},${this.suit}"
	}
	
}
