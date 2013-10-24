package advisor

import java.io.ObjectInputStream.HandleTable.HandleList;

import gameMechanics.Position;
import advisor.analyser.preflop.ChenPreflopAnalyser
import advisor.analyser.preflop.HandGroup
import advisor.parser.DOM.Card;
import advisor.parser.DOM.Face;
import advisor.parser.DOM.Suit;


class PreflopHandMain {
	
	
	/*
	 * The Chen formula.

Score your highest card only. Do not add any points for your lower card.
A = 10 points.
K = 8 points.
Q = 7 points.
J = 6 points.
10 to 2 = 1/2 of card value. (e.g. a 6 would be worth 3 points)
Multiply pairs by 2 of one card's value. However, minimum score for a pair is 5.
(e.g. KK = 16 points, 77 = 7 points, 22 = 5 points)
Add 2 points if cards are suited.
Subtract points if their is a gap between the two cards.
No gap = -0 points.
1 card gap = -1 points.
2 card gap = -2 points.
3 card gap = -4 points.
4 card gap or more = -5 points. (Aces are high this step, so hands like A2, A3 etc. have a 4+ gap.)
Add 1 point if there is a 0 or 1 card gap and both cards are lower than a Q. (e.g. JT, 75, 32 etc, this bonus point does not apply to pocket pairs)
Round half point scores up. (e.g. 7.5 rounds up to 8)
For step 5, it's easier to refer to this extra 1 point as a "straight bonus" to save confusion between steps 4 and 5. Subtracting 1 point for 1 gap and then adding it back again for lower cards seems a bit awkward I know, but that's the way it works.
	 * 
	 * 
	 * 
	 */
	
	
	public static void main (String[] args) {
		
		while (1) {
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			
			/*
			print 'Please enter your position (b = button, l = late, m = middle, e = early): '
	
			String positionRawStr = bufferedReader.readLine()
			
			
			Position position = Position.convertPositionStr(positionRawStr)
			*/
			

	

			print 'Please enter your hole cards: '
			
			String holeCardsRawStr = bufferedReader.readLine();
			
			
			
			char[] cardChars = holeCardsRawStr.getChars()
			
			
			
			Card hole1 = new Card(Face.convertFaceStr (cardChars[0].toString()), Suit.convertSuitStr(cardChars[1].toString()))
	
			Card hole2 = new Card(Face.convertFaceStr (cardChars[2].toString()), Suit.convertSuitStr(cardChars[3].toString()))
			
			
			ChenPreflopAnalyser analyser = new ChenPreflopAnalyser()
			
			HandGroup handGroup = analyser.analyse(hole1, hole2)
			
			println handGroup.description()
			
		}
		
	}

}
