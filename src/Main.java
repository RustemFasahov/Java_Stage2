public class Main {

    public static void main(String[] args) {
	Team team = new Team("rezzors", "vova", "misha", "sasha", "grisha");
	Course course = new Course();
	course.doIt(team,2,1);
	course.doIt(team,1,2);
	team.printTeam();
    }
}
