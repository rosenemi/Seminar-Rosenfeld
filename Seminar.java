import java.io.File;  // Import the File class. 
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*; //It was faster to do it this way because I needed the scanner class and array class

public class Seminar{
  int max = 74;
  int numS = 0;
  int numProjects = 18;
  int[][] size = new int[5][5]; // keep track of the number of students in each session
  int conflicts = -25; // set at -25 to counteract people wo did not fill out the survey
  //int[] interest = new int[numProjects]; old idea
  Student[] stu = new Student[max]; //Array of all the people registred to come to the party
  Project[][] schedule = new Project[5][5]; // array of project objeces which are sesssions in their roster for being presented
  ArrayList<Project>  Projects = new ArrayList<Project>();
  /* Method to upload all the students data who filled out the survey */
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
        /* Thing used to check to make sure everything was uploading correctly
        System.out.print(stu[numS].studentID + " " + stu[numS].choice1 + " " + stu[numS].choice2 + " " + stu[numS].choice3 + " " + stu[numS].choice4 + " " + stu[numS].choice5 + " ");
        */
        numS++;
      }
      myReader.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  /* Method to upload all the projects (ie sessions - I needed a word that didn't start with s)*/
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
  /* A method which tallies up the total interest in each project */
  public void CalcInterest(){
    int i = 0;
    while (i < numS){
      int add = stu[i].choice1 - 1;
      if (add > -1){
        Projects.get(add).updateInterest();
        //interest[add] = interest[add] + 1;
      }
      i++;
    }
    i = 0;
    while (i < numS){
      int add = stu[i].choice2 - 1;
      if (add > -1){
        Projects.get(add).updateInterest();
      }
      i++;
    }
    i = 0;
    while (i < numS){
      int add = stu[i].choice3 - 1;
      if (add > -1){
        Projects.get(add).updateInterest();
      }
      i++;
    }
    i = 0;
    while (i < numS){
      int add = stu[i].choice4 - 1;
      if (add > -1){
        Projects.get(add).updateInterest();
      }
      i++;
    }
    i = 0;
    while (i < numS){
      int add = stu[i].choice5 - 1;
      if (add > -1){
        Projects.get(add).updateInterest();
      }
      i++; 
    }
    /* Used to test before
    for (int x = 0; x < 18; x++){
      System.out.println(Projects.get(x).printInterest());
    } */
  }
  /* Method is used to sort the projects into an order list from most interest to least*/
  public void sortProjects(){
    Collections.sort(Projects); // resorts list each time
    /* for (int x = 0; x < 18; x++){
      System.out.println(Projects.get(x).printInterest());
    } */
  }
  /* Method that takes the sorted list and puts it into a roster. With the sessions with the most interest being the first sessions added to each of the 5 time slots */
  public void generateSchedule(){
    int a = 0;
    boolean check = true;
   for (int x = 0; x < 5; x++){
     for (int z = 0; z < 5; z++){
       Collections.sort(Projects);
       check = true;
       while (check){
       if(Projects.get(a).numSession() < 2 ){ //prevents no more than two sessions from being made for an individual session
         schedule[x][z] = Projects.get(a);
         Projects.get(a).updateInterestII();
         Projects.get(a).addSession();
         check = false; // stops the loop from continuing once an open spot has been found
       }
         else{
           a++;
         }
       }
     }
   } 
    for (int y = 0; y < 5; y++){
      for(int b = 0; b < 5; b++){
        //prints the schedule
        System.out.print("  " + schedule[b][y]);
      }
      System.out.println("");
    }
  }
  /* Method that sorts students into the sessions they want. Starting with everyone's first choice and then doing 2-5. This prevents students from having signifcant priority over other students */
  public void sortStudents(){
    int i = 0;
    while (i < numS){
      int choice1 = stu[i].choice1;
      choiceIterate(choice1, i);
      i++;
    }
    i = 0;
    while (i < numS){
      int choice2 = stu[i].choice2;
      choiceIterate(choice2, i);
      i++;
    }
    i = 0;
    while (i < numS){
      int choice3 = stu[i].choice3;
      choiceIterate(choice3, i);
      i++;
    }
    i = 0;
    while (i < numS){
      int choice4 = stu[i].choice4;
      choiceIterate(choice4, i);
      i++;
    }
    i = 0;
    while (i < numS){
      int choice5 = stu[i].choice5;
      choiceIterate(choice5, i);
      i++;
    }
  }
  /* used in the method above just prevent me from having to rewrite it 5 times */
    public void choiceIterate(int c, int num){
    boolean reset = false;
    if (c  > 0){
          for (int y = 0; y < 5; y++){
            for(int b = 0; b < 5; b++){
              if (c==schedule[b][y].projectNum && size[b][y] < 16){
                if (stu[num].getSchedule(y) == 0){          
                  stu[num].updateSchedule(y, c);
                  size[b][y]++;
                  //System.out.print(size[b][y] + " ");
                  reset = true; // prevents it from continuing after a match was made (so no duplicate sessions)
              }
            }
              if (reset){
                break;
              }
          }
            if(reset){
              break;
            }
        }
      }
    }
  /*Method that prints out the session information by student 
  */
  public void printStu(){
    for(int a = 0; a < numS; a++){
      System.out.println("Student Name: " + stu[a].name);
      System.out.println("Session 1: " + stu[a].pSchedule[0]);
      System.out.println("Session 2: " + stu[a].pSchedule[1]);
      System.out.println("Session 3: " + stu[a].pSchedule[2]);
      System.out.println("Session 4: " + stu[a].pSchedule[3]);
      System.out.println("Session 5: " + stu[a].pSchedule[4]);
      System.out.println("");
    }
  /*Method that prints out the session information by session number and time */
  }
  public void printSession(){
    for (int b = 0; b < 5; b++){
      for (int y = 0; y < 5; y++){
        System.out.println("Time Slot " + b + " Session " + schedule[b][y].projectName);
        for (int a = 0; a < numS; a++){
        if (stu[a].pSchedule[b] == schedule[y][b].projectNum){
          System.out.println(stu[a].name);
        }
      }
      System.out.println("");
      }
    }
    //System.out.print("Session: " + );
  }
  /* Method that fills students into open sessions that still have gaps in their schedule due to conflicts (or not filling out the schedule) 
  */
  public void fillIn(int b){
    boolean norepeat = true;
    for (int a = 0; a < numS; a++){
      if (stu[a].pSchedule[b] == 0){
        for (int d = 0; d < 5; d++){
          for (int c = 0; c < 5; c++){
            if (stu[a].pSchedule[c] == schedule[d][b].projectNum){
              norepeat = false;
            } 
          }
          if (size[d][b] < 16 && norepeat){
              stu[a].pSchedule[b] = schedule[d][b].projectNum;
              //System.out.println(size[d][b]);
            size[d][b]++;
              conflicts++; 
              break;
          }
          norepeat = true;
        }
      }
    }
  }
  // returns the total number of conflicts calculated
  public int getConflicts(){
    return conflicts;
  }
}
