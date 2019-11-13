
abstract public class Competitor {
	
	// instant variables 
	private int compNumber;			// Competitor's Number 
	private String compNameStart; 	// Competitor's Name 
	private int level;				// Competitor's Level !!int?
	private String scores;			// Competitor's scores !!string?
	
	//Constructor 
	public  Competitor(int CompetitorNumber, String CompetitorName, int CompetitorLevel, String Scores)
	{
		this.compNumber = CompetitorNumber;
		compNameStart = CompetitorName;
		level = CompetitorLevel;
		scores = Scores;
		
		try {									//Alter when level format formalised.
			if (this.level > 4)
				throw new Exception();
		}
		catch (Exception e){
			String error = e + " Level is set too high.";
			System.out.println(error);
		}
	}
	
	//Return Values 
	public Name getCompName() {
		Name compName = new Name(compNameStart);
		return compName;
	} 
	public String getCompetitorNumber(){			//This is assuming input is string 
		return Integer.toString(compNumber);
	}	
	public String getLevel(){
		return Integer.toString(level);				//This changes level output to string 
	}
	
	public abstract double getOverallScore();
	

}
