package advisor

class PokerstarsHistoryParser {
	
	private String historyFilePath;

	public PokerstarsHistoryParser(String historyFilePath) {
		
		this.historyFilePath = historyFilePath
		
	}
	
	public void parse() {
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(historyFilePath))
		
		String sCurrentLine;
		
		def rawText = []
 
		while ((sCurrentLine = bufferedReader.readLine()) != null) {		
			
			rawText.add(sCurrentLine)
			
		}
		
		
		
		try {
			if (bufferedReader != null)bufferedReader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		def handStartIndexes = []
		
		
		rawText.eachWithIndex() { obj, i -> 
			
			println "${obj}" 
			
			if(obj.startsWith('PokerStars Hand')) {
				handStartIndexes.add(i)
			}	
			
		};	
	
	

		println "****************${handStartIndexes.size - 1} hands in this file)*****************"
		
		
		for (int i = 0; i < handStartIndexes.size - 1; i++) {
			
			def obj = handStartIndexes.get(i)
			
			int start = obj
			int end = handStartIndexes.get(i+1)
			
			println "***************************************************************************************************************"
			
			for (int j = 0; j < end - start; j++) {
				
				println rawText.get(start + j)
						
			}
			
			
		}
		
	}
	
	
}
