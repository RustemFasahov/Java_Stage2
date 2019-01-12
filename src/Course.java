public class Course {
    private String[] course = {"run", "swim", "jump"};
    private String[] course2 = {"jump", "run", "swim"};

    public void doIt(Team t, int numberUser, int courseStage) {
        if (courseStage == 1) {
            t.playUser(course, numberUser - 1);
        } else if (courseStage == 2) {
            t.playUser(course2, numberUser - 1);
        }
    }
}
