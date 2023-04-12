class Main {
  public static void main(String[] args) {
    Seminar hope = new Seminar();
    hope.Upload();
    hope.UploadProjects();
    hope.CalcInterest();
    hope.sortProjects();
    hope.generateSchedule();
    hope.sortStudents();
    hope.fillIn(0);
    hope.fillIn(1);
    hope.fillIn(2);
    hope.fillIn(3);
    hope.fillIn(4);
    hope.printStu();
    int a = hope.getConflicts();
    System.out.println("Total Conflicts: " + a);
    hope.printSession();
  }
}
