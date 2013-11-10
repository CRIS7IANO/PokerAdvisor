package advisor.analyser.preflop

import advisor.parser.DOM.Card;


public interface PreflopAnalyser {
	
	public HandGroup analyse(Card hole1, Card hole2);
	
}
