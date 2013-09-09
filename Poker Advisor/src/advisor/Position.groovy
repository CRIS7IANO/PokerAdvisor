package advisor

public enum Position {
	BUTTON,
	LATE,
	MIDDLE,
	EARLY;
	
	
	public static convertPositionStr (String positionStr) {
		
		Position position = null;
		
		switch (positionStr.toLowerCase()) {
			case 'b': position = Position.BUTTON
					 break;
			case 'l': position = Position.LATE
					 break;
			case 'm': position = Position.MIDDLE
					 break;
			case 'e': position = Position.EARLY
					 break;
			
			
		}
		
		return position
		
	}
	
	
}
