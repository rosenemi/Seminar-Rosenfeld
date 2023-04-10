import java.util.*;

public class Project implements Comparable<Project> {
  int interest;
  String projectName;
  int projectNum;
  public Project(String p, int pN){
    interest = 0;
    projectName = p;
    projectNum = pN;
  }
  public void updateInterest() {
    interest = interest + 1;
  }
  public int printInterest(){
    return interest;
  }

  public int compareTo(Project p) {
    return ((Project)p).interest - this.interest;
  }
}
