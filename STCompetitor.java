
public class STCompetitor {
	
	// instance variables
	private int CompID;
	private Name name;
	private String level;
	private int age;
	private String gender;
	private String country;
	private int [] scores;
	
	//constructor
	public STCompetitor(int id, Name n, String l, int a, String g, String c, int[] sc) {
		CompID = id;
		name = n;
		level = l;
		age = a;
		gender = g;
		country = c;
		scores = sc;
	}
	
	//Method to get Competitor's information
	public int getID() { return CompID; }
	//Level 1: Beginner; Level 2: Intermediate; Level 3: Advanced; Level 4: Expert
	public Name getName() {return name;}
	public String getLevel() { return level; }
	public int getAge() { return age; }
	public String getGender() { return gender; }
	public String getCountry() { return country; }
	
	
	//method to get all the scores
	public int[] getScoreArray() {
		return scores;
	}
	
	
	
	//Method for checking level is needed
	public boolean checkLevel() {
		//see if Competitor has the level of Advanced / Expert
		//return True if advanced or expert
		String lev = this.getLevel();
		if (lev.contentEquals("Advanced")|lev.contentEquals("Expert")) {
			return true;}
		//return False for other case (Beginner / Intermediate)
		return false;
		
	}
	
	
	//Method to get the overall score
	//the overall score = (sum of all scores * mark of level / number of scores)
	//Level 1: Beginner (Total Score x 1); Level 2: Intermediate (Total Score x 1); 
	//Level 3: Advanced (Total Score x 1.2); Level 4: Expert (Total Score x 1.2)
	//assumes that all score entered, if not, 0 will be used for calculation.
	public double getOverallScore() {
		double total = 0;
		for (int scoreIndex = 0; scoreIndex < scores.length; scoreIndex ++) {
			total += scores[scoreIndex];
		}
		// if Competitor has Advanced or Expert level, total score x 1.2
		if (this.checkLevel()) {
			total = total * 1.2;
		}
		//return overall score 
		return (double) total/scores.length;
	};
	
	public int getScore(int game){
		int score = scores[game-1];
	return score;}
	
	
	//Method to show full detail of a competitor
	public String getFullDetails() {
		String fullDetail = "";
		fullDetail = "Full Details for Competitor " + CompID + ": - \n";
		fullDetail += "Competitor number " + CompID + ", Name: ";
		fullDetail += name.getFullName() + ".\n";
		fullDetail += name.getFirstandLastName() + " is a " + level;
		fullDetail += " and receive these scores in the Competition: ";
		for (int i = 0; i<scores.length; i++) {
			fullDetail += this.getScoreArray()[i] + " ";
		}
		if (gender.contentEquals("Female")){
			fullDetail += "\nThis gives her an overall score: " 
		+ String.format("%-4.2f",this.getOverallScore()) +"\n\n";
			}
		else {
			fullDetail += "\nThis gives him an overall score: "
		+ String.format("%-4.2f",this.getOverallScore()) + "\n\n";
			}
		
		return fullDetail;
		
	}
	// method for short detail, provided while manager query by CompID.
	public String getShortDetails() {
		String shortDetail = "";
		shortDetail = "Short Detials for Competitor " + CompID + ": - \n";
		shortDetail += "CN " + CompID + " (" + name.getInitial() + ") ";
		shortDetail += "has overall score " + String.format("%-4.2f",this.getOverallScore()) + ".\n\n";
		return shortDetail;
	}
	

	
	
}
