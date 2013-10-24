package advisor.parser.DOM

import java.util.List;

abstract class Street {
	
	int pot
	List acts
	
	public Street() {
		this.acts = []
	}
	
	public String toString() {
		def str = ""
		
		acts.each {
			str += "${it.player},"
		}
		
		return str
		
	}
	
	public abstract void setCards() 
	
	

}
