/** Software Engineering Foundations
* Script adapted from F21SF_Arrays1 tutorial, Heriot-Watt University 
* Last edited by: Syd Colvin
*/
public class Name {
  private String firstName;
  private String middleName;
  private String lastName;
  
  public Name(String fName, String lName) {
		firstName = fName;
		middleName = "";
		lastName = lName;
  }
  
  public Name(String fName, String mName, String lName) {
		firstName = fName;
		middleName = mName;
		lastName = lName;
}
  
  //Adapted to include names with only one name
  public Name (String fullName) {
	  int spacePos1 = fullName.indexOf(' ');
	  if (spacePos1 > 0){
		  firstName = fullName.substring(0, spacePos1);
		  int spacePos2 = fullName.lastIndexOf(' ');
		  if (spacePos1 == spacePos2){ 
			  middleName = "";
			  lastName = fullName.substring(spacePos1 + 1);}
		  else {
			  middleName = fullName.substring(spacePos1+1, spacePos2);
			  lastName = fullName.substring(spacePos2 + 1);}
	  }
	  else {
		  firstName = fullName; middleName = ""; lastName = "";
		}
  }
  
  public String getFirstName() { return firstName; }
  public String getMiddleName() { return middleName; }
  public String getLastName() { return lastName; }
  
  public void setFirstName(String fn) {
	  firstName = fn;
  }
  public void setMiddleName(String mn) {
	   middleName = mn;
  }
  public void setLastName(String ln) {
	  lastName = ln;
  }
  
  public String getFirstAndLastName() {
		return firstName + " " + lastName;
  }
  
  public String getLastCommaFirst() {
		return lastName + ", "+ firstName;
  }
  

  public String getFullName() {
	  String result = firstName + " ";
	  if (!middleName.equals("")) {
		  result += middleName + " ";
	  }
	  result += lastName;
	  return result;	  
  }
  
  //Added to find initials 
  public String getInitials() {
	 char i1 = firstName.charAt(0);
	 char i3 = lastName.charAt(0);
	 String result = "";
	 String inital1 = Character.toString(i1);
	 String inital3 = Character.toString(i3);
	  if (middleName.equals("")) {
		  result = inital1 + inital3;}
	  else{
		  char i2 = middleName.charAt(0);
		  String inital2 = Character.toString(i2);
		  result = inital1 + inital2 + inital3;} 
	 return result;
  }
   
}

