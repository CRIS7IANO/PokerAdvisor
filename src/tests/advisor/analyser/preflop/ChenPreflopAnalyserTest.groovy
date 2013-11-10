package advisor.analyser.preflop;

import static org.junit.Assert.*;

import org.junit.Test;

import advisor.parser.DOM.Card;
import advisor.parser.DOM.Face;
import advisor.parser.DOM.Suit;


public class ChenPreflopAnalyserTest {

	
	
	@Test
	void testGroupOne() {
		ChenPreflopAnalyser analyse = new ChenPreflopAnalyser()
		
		
		assertEquals(HandGroup.ONE, analyse.analyse(new Card(Face.ACE, Suit.CLUBS), new Card(Face.ACE, Suit.CLUBS)))
		
	}

}
