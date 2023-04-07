import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*; 

public class Seminar{
  int max = 74;
  int numS = 0;
  Student[] stu = new Student[max]; //Array of all the people registred to come to the party
  int[][] schedule = new int[5][5];
  public void Upload(){
    try {
      File myObj = new File("SrSeminar_Data.csv");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String[] compS = myReader.nextLine().split(",");
        String uStudentID = compS[0];
        String uName = compS[1];
        int uChoice1 = Integer.parseInt(compS[2]);
        int uChoice2 = Integer.parseInt(compS[3]);
        int uChoice3 = Integer.parseInt(compS[4]);
        int uChoice4 = Integer.parseInt(compS[5]);
        int uChoice5 = Integer.parseInt(compS[6]);
        Student x = new Student(uStudentID, uName, uChoice1, uChoice2, uChoice3, uChoice4, uChoice5); //creates a new object, called student, for each line in the data file
        stu[numS] = x; // adds that People object to the array; the -1 is for indexing purposes
        numS++;
      }
      myReader.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
    public void UploadProjects(){
    try {
      File myObj = new File("Project_Data.csv");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String[] compS = myReader.nextLine().split(",");
        String pName = compS[0];
        int pNum = Integer.parseInt(compS[1]);
        Project x = new Project(pName, pNum); //creates a new object, called student, for each line in the data file
        Projects.add(x); // adds that People object to the array; the -1 is for indexing purposes
      }
      myReader.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  public void CalcInterest(){
    int i = 0;
    while (i < numS){
      int add = stu[i].choice1 - 1;
      if (add > -1){
        interest[add] = interest[add] + 1;
      }
      i++;
    }
    i = 0;
    while (i < numS){
      int add = stu[i].choice2 - 1;
      if (add > -1){
        interest[add] = interest[add] + 1;
      }
      i++;
    }
    i = 0;
    while (i < numS){
      int add = stu[i].choice3 - 1;
      if (add > -1){
        interest[add] = interest[add] + 1;
      }
      i++;
    }
    i = 0;
    while (i < numS){
      int add = stu[i].choice4 - 1;
      if (add > -1){
        interest[add] = interest[add] + 1;
      }
      i++;
    }
    i = 0;
    while (i < numS){
      int add = stu[i].choice5 - 1;
      if (add > -1){
        interest[add] = interest[add] + 1;
      }
      i++; 
    }
    for (int x = 0; x < 18; x++){
      System.out.println(interest[x]);
    }
  }
  public void sort(){
    
  }
}
