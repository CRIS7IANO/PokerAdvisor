package analyser.preflop;


/*
 * http://en.wikipedia.org/wiki/Texas_hold_'em_starting_hands#Sklansky_hand_groups
 */

public enum HandGroup {
	ONE("Group one - Playable all Positions"),
	TWO("Group two - Playable all Positions"),
	THREE("Group three - Playable all Positions"),
	FOUR("Group four - Playable all Positions"),
	FIVE("Group five - Playable Middle and Late"),
	SIX("Group six - Playable Late"),
	SEVEN("Group seven - Playable Late if know one has bet"),
	EIGHT("Group eight - Fold"),
	NINE("Group nine - Fold");
	
	private final String description
	
	
	HandGroup (String description) {
		this.description = description
	}
	
	public String description() {
		return description
	}
	
	public static obtainHandGroup(int handGroupInt) {
		
		HandGroup handGroup;
		
		switch (handGroupInt) {
			case 1: handGroup = HandGroup.ONE
					 break;
			case 2: handGroup = HandGroup.TWO
					 break;
			case 3: handGroup = HandGroup.THREE
					 break;
			case 4: handGroup = HandGroup.FOUR
					 break;
			case 5: handGroup = HandGroup.FIVE
					 break;
			case 6: handGroup = HandGroup.SIX
					 break;
			case 7: handGroup = HandGroup.SEVEN
					 break;
			case 8: handGroup = HandGroup.EIGHT
					 break;
			case 9: handGroup = HandGroup.NINE
					 break;
			default:
					handGroup = HandGroup.NINE
					 break;
			
		}
		
		return handGroup
		
		
	}
	
}
