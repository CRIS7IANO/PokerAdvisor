package analyser.preflop

import deck.Card;


public interface PreflopAnalyser {
	
	public HandGroup analyse(Card hole1, Card hole2);
	
}
