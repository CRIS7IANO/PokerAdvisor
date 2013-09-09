package advisor.analyser.preflop;

import static org.junit.Assert.*;

import org.junit.Test;

import deck.Card
import deck.Face;
import deck.Suit;
import analyser.preflop.ChenPreflopAnalyser;
import analyser.preflop.HandGroup;

public class ChenPreflopAnalyserTest {

	
	
	@Test
	void testGroupOne() {
		ChenPreflopAnalyser analyse = new ChenPreflopAnalyser()
		
		
		assertEquals(HandGroup.ONE, analyse.analyse(new Card(Face.ACE, Suit.CLUBS), new Card(Face.ACE, Suit.CLUBS)))
		
	}

}
