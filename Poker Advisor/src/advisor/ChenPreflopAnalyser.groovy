package advisor


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



class ChenPreflopAnalyser {

	public ChenPreflopAnalyser() {}
	
	public String analyse(Card hole1, Card hole2, Position position) {
		
			
		double analysedPoints = 0.0
		
		//highest card points
		if (hole1.face.points >= hole2.face.points) {
			analysedPoints = hole1.face.points
		} else {
			analysedPoints = hole2.face.points
		}
		
		//Multiply pair points by 2, must at least be 5
		if (hole1.face.points == hole2.face.points) {
			analysedPoints = analysedPoints * 2
			
			if (analysedPoints < 5.0) {
				analysedPoints = 5.0
			}
			
		}
		
		//Add 2 points if cards are suited.
		if (hole1.suit == hole2.suit) {
			analysedPoints = analysedPoints + 2;
		}
			
		//subtract gap
		double gap = hole1.face.rank - hole2.face.rank
			
		double gapAdjustment = 0.0
		
		if (gap < 0.1) {
			gap = gap * -1.0
		}
		
		
		//1 gap
		if (gap == 2) {
			gapAdjustment = 1
		}
		
		//2 gap
		if (gap == 3) {
			gapAdjustment = 2
		}
		
		//3 gap
		if (gap == 4) {
			gapAdjustment = 4
		}
		
		//4 gap	
		if (gap >= 5) {
			gapAdjustment = 5
		}
		
		
		if (gap != 1 && gap != 0.0) {
			
			analysedPoints = analysedPoints - gapAdjustment
		}	
		
		
		//apply gap points
		if ((gap == 1 || gap == 2) && (hole1.face.rank < Face.QUEEN.rank && hole2.face.rank < Face.QUEEN.rank)) {
			analysedPoints = analysedPoints + 1
		} 
		
		double chenPoints = Math.round(analysedPoints)
		
		return buildReport(chenPoints, position)

		
	}
	
	private String buildReport(double chenPoints, Position position) {
		
		
		/*
		 * Early Position
Raise = Score is 9 or higher
Call = Score is 8 or higher
Fold = Score is lower than 8
Middle Position
Raise = Score is 9 or higher
Call = Score is 7 or higher
Fold = Score is lower than 7
Late Position
Raise = Score is 9 or higher
Call = Score is 6 or higher
Fold = Score is lower than 6
		 */
		
		if (position == Position.EARLY) {
			
			if (chenPoints >= 10) {
				return 'CONSIDER RAISING OR FOLD'
			} else {
				return 'YOU SHOULD FOLD'		
			}
		}
		
		if (position == Position.MIDDLE) {
			
			if (chenPoints >= 9) {
				return 'CONSIDER RAISING OR FOLD'
			} else {
				return 'YOU SHOULD FOLD'
			}
			
		}
		
		if (position == Position.LATE || position == Position.BUTTON) {
			
			if (chenPoints >= 9) {
				return 'TRY RAISING'
			} else if (chenPoints >= 8) {
				return 'CONSIDER RAISING OR CALL'
			} else if (chenPoints >= 7) {
				return 'CALL MINIMAL'
			} else {
				return 'YOU SHOULD FOLD'
			}
			
		}
		
	}
	
}
