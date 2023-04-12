
public class Project implements Comparable<Project> {
  int interest;
  String projectName;
  int projectNum;
  int numOfSession = 0;
  public Project(String p, int pN){
    interest = 0;
    projectName = p;
    projectNum = pN;
  }
  public void updateInterest() {
    interest = interest + 1;
  }
  public void updateInterestII(){
    interest = interest - 16;
  }
  public int printInterest(){
    return interest;
  }
  public void addSession(){
    numOfSession++;
  }
  public int numSession(){
    return numOfSession;
  }

  public int compareTo(Project p) {
    return ((Project)p).interest - this.interest;
  }

  public String toString() {
    return "" + projectNum;
  }
}
