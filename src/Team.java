public class Team {
    private String teamName = "";
    private String[][] users;

    public Team(String name, String user_1, String user_2, String user_3, String user_4) {
        teamName = name;
        users = new String[4][4];
        String[] names = {user_1,user_2,user_3,user_4};
        for (int i = 0; i < users.length; i++) {
            users[i][0] = names[i];
            for (int j = 1; j < users[i].length; j++) {
                users[i][j] = "X";
            }
        }
    }

    public void playUser(String[] courses, int numberUser){
        for (int i = 1, j = 0;  j < courses.length; i++, j++) {
            users[numberUser][i] = courses[j];
        }
    }

    public void printTeam(){
        System.out.println("Название команды: " + teamName);

        for (int i = 0; i < users.length; i++) {
            for (int j = 0; j < users[i].length; j++) {
                System.out.printf("%7s",users[i][j]);
            }
            System.out.println();
        }
    }
}
