package advisor


public enum Face {
	ACE (10, 13),
	KING (8, 12),
	QUEEN (7, 11),
	JACK (6, 10),
	TEN (5, 9),
	NINE (4.5, 8),
	EIGHT (4, 7),
	SEVEN (3.5, 6),
	SIX (3, 5),
	FIVE (2.5, 4),
	FOUR (2, 3),
	THREE (1.5, 2),
	TWO (1, 1);

	private final double points
	private final int rank
	
	Face (double points, int rank) {
		this.points = points
		this.rank = rank
	}
	
	public double points () { return points }
	public double rank () { return rank }
	
	public static convertFaceStr (String faceStr) {
		
		Face face = null;
		
		switch (faceStr.toLowerCase()) {
			case 'a': face = Face.ACE
					 break;
			case 'k': face = Face.KING
					 break;
			case 'q': face = Face.QUEEN
					 break;
			case 'j': face = Face.JACK
					 break;
			case 't': face = Face.TEN
					 break;
			case '9': face = Face.NINE
					 break;
			case '8': face = Face.EIGHT
					 break;
			case '7': face = Face.SEVEN
					 break;
			case '6': face = Face.SIX
					 break;
			case '5': face = Face.FIVE
					 break;
			case '4': face = Face.FOUR
					 break;
			case '3': face = Face.THREE
					 break;
			case '2': face = Face.TWO
					 break;
			
		}
		
		return face
		
	}

}