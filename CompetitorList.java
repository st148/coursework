import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.System;



/**
 * Software Engineering Foundations Assignment 1 <p>
 * CompetitorList class contain an ArrayList of all 15 competitor objects from STCompetitor class.
 *
 * @author Sheron Tong (H00316746)
 */

public class CompetitorList {
	
	private ArrayList<STCompetitor> compList;
	
	/**Creates an empty ArrayList compList */
	public CompetitorList() {
		compList = new ArrayList<STCompetitor> (); }
	
	/**Adding information from STCompetitor class to ArrayList compList
	 * (each competitor)
	 * @param c representing each competitor in STCompetitor
	 */
	public void add(STCompetitor c) {
		compList.add(c);
	}
	/**Read the input file line by line until the last line in the document.
	 * @param filename the name of the file need to be read (.csv in text format)
	 * 
	 */
	public void readFile(String filename) {
		try {
			File f = new File(filename);
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				String inputLine = scanner.nextLine();
				if (inputLine.length() !=0) {processLine(inputLine);}
				
			}
		}
		
		catch (FileNotFoundException fnf) {
			System.out.println(filename + " is no found, please check again later.");
			System.exit(0);
		}
	}
	
	/**Separate the text in file line by line to different parameter we need,
	 * and add the value to its variable for each competitor.<p>
	 * 1st string in the line = Competitor id<p>
	 * 2nd string in the line = Competitor name<p>
	 * 3rd string in the line = Competitor level<p>
	 * 4th string in the line = age of competitor (need to turn into integer)<p>
	 * 5th string in the line = gender of competitor<p>
	 * 6th string in the line = country origin of competitor<p>
	 * last item in the line = scores stored for all the games in the competitor.
	 * An array is created to store the scores. (Need to turn to integer from String)
	 * @param line each line in text file.
	 */
	private void processLine(String line) {
		//String
		try {
			String parts [] = line.split(",");
			int id = Integer.parseInt(parts[0].trim());
			Name name = new Name(parts[1]);
			String level = parts[2];
			int age = Integer.parseInt(parts[3].trim());
			String gender = parts[4];
			String country = parts[5];
		
			//Scores are at the end of the line
			int scorelength = parts.length - 6;
			String allscores[] = new String[scorelength];
			System.arraycopy(parts, 6, allscores, 0 , scorelength);
			int scores[] = new int[scorelength];
			for (int i = 0; i < allscores.length ; i++){
				scores[i] = Integer.parseInt(allscores[i].trim());
			}
			
			
			//Create Competitor object and add to the list
			STCompetitor c = new STCompetitor (id, name, level, age, gender, country, scores);
			this.add(c);
		
			}
		catch(NumberFormatException nfe) {
			String error = "Number conversion error in '" + line + "' - " + nfe.getMessage();
			System.out.println(error);
		}
		catch(ArrayIndexOutOfBoundsException air) {
			String error = "Not enough items in: '" + line + "' index position : " + air.getMessage();
			System.out.println(error);
		}
		catch(ArrayStoreException ase){
			System.out.println("ArrayStoreException is found. " + ase.getMessage());
		}
	}
	/**Write the report into a text file.
	 * @param filename the name of the file will be created.
	 * @param report The string created in the method getAllComp(), 
	 * which is the report included all the information of competitors.
	 */
	public void writeToFile (String filename, String report) {
		FileWriter fw;
		try {
			fw = new FileWriter(filename);
			fw.write("The report for all competitor in the ST GAME: \n" );
			fw.write(report);
			fw.close();
		}
		catch (FileNotFoundException fnf) {
			System.out.println(filename + "is no found, please check again later.");
			System.exit(0);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
	}
	/**Creating a table for the report that would be written in text file.
	 * Calling methods from STCompetitor class to get ID, Name, Level, Age, Gender, 
	 * Country, Score and OverallScore.
	 * @return report including all the information and overall score in a table format.
	 */
	//write all information of competitor into the report (string)
	public String getAllComp() {
		String report = "";
		//Title of table
		report += String.format("%-4s", "ID");
		report += String.format("%-25s", "Name");
		report += String.format("%-14s", "Level");
		report += String.format("%-4s", "Age");
		report += String.format("%-8s", "Gender");
		report += String.format("%-15s", "Country");
		report += String.format("%-20s", "Scores");
		report += String.format("%-7s", "Overall");
		report += "\n";
		//Adding data row by row until the end of the arraylist
			for (STCompetitor c : compList) {
				report += String.format("%-4d", c.getID());
				report += String.format("%-25s", c.getName().getFullName());
				report += String.format("%-14s", c.getLevel());
				report += String.format("%-4d", c.getAge());
				report += String.format("%-8s", c.getGender());
				report += String.format("%-15s", c.getCountry());
				report += String.format("%-20s", Arrays.toString(c.getScoreArray()));
				report += String.format("%-7.2f", c.getOverallScore());
				report += "\n";
			}
			return report;
		}
	/**Return the size of the ArrayList compList. 
	 * (Number of objects stored in the compList)
	 * @return the size of Competitor List
	 */
	public int getSize() {
		return compList.size();
	}
	/**Find the Competitor by entering index number. 
	 * @param index Requested index number of compList (starting from 0)
	 * @return the required competitor from STCompetitor class
	 */
	public STCompetitor getAtIndex(int index) {
		return compList.get(index);
	}
	
	/**Find the Competitor by entering Competitor id. 
	 * In case, wrong id is entered, return null.
	 * @param id Requested Competitor number
	 * @return the required competitor from STCompetitor class
	 */
	public STCompetitor findById (int id) {
		for (STCompetitor c: compList) {
			if(c.getID() == id) {return c;}
		}
	return null;
	}
	
	/**Find the highest overall score among competitors.
	 * @return the highest overall score
	 */
	public double getHighestScore() {
		double highscore = 0.0;
		for (STCompetitor c: compList){
			if (c.getOverallScore() > highscore){
				highscore = c.getOverallScore();
			}
		}
	return highscore;}
	
	/**Find the winner of the competition. In case, no one is found, return null.
	 * @return The competitor with the highest overall score.
	 */
	public STCompetitor findChampion(){
		for (STCompetitor c: compList){
			if (c.getOverallScore() == this.getHighestScore()){
				return c;}
			}		
			return null;	
			}
	
	/**Find the lowest overall score among competitors.
	 * @return the lowest overall score
	 */
	public double getLowestScore() {
		double lowscore = 5.0;
		for (STCompetitor c: compList){
			if (c.getOverallScore() < lowscore){
				lowscore = c.getOverallScore();
			}
		}
	return lowscore;}
	
	/**Find the average of overall score among competitors.
	 * @return the average score
	 */
	public double getAveScore(){
		double total = 0.0;
		for (STCompetitor c: compList){
			total += c.getOverallScore();
		}
		return total/compList.size();
	}
	
	/**Find the number of competitors who get the overall score which is below average.
	 * @return the number of competitors below average.
	 */
	public int CountLowAve(){
		int count = 0;
		for (STCompetitor c: compList){
			if (c.getOverallScore() < this.getAveScore()){
				count++;
			}
		}
		return count;
	}
	
	/**Find the number of competitors who get the overall score which is above average.
	 * @return the number of competitors above average.
	 */
	public int CountHighAve(){
		int count = 0;
		for (STCompetitor c: compList){
			if (c.getOverallScore() > this.getAveScore()){
				count++;
			}
		}
		return count;
	}
	
	/**Find the number of competitors in each level.
	 * @param level Enter the level that needed to be count.
	 * @return the number of competitors in the required level
	 */
	public int CountLevel(String level){
		int count = 0;
		for (STCompetitor c:compList){
			if (c.getLevel().contentEquals(level)){
				count ++;}
		}
		return count;
	}
	
	/**Find the number of competitors get the same score in each games.
	 * @param game Enter the which game is wanted to consider
	 * @param mark Enter the score required to count
	 * @return the number of competitors getting the required score in specified game
	 */
	public int FindEachGameScore(int game, int mark){
		int count = 0;
		for (STCompetitor c:compList){
			if (c.getScore(game) == mark){
				count++;
			}
		}
		return count;
	}
}
