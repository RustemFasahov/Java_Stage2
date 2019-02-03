import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService {

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    public String getNickByLoginPass(String login, String pass) {
        for(Entry o: enteries){
            if(o.login.equals(login) && o.pass.equals(pass)) return o.nick;
        }
        return null;
    }

    class Entry{
        private String login;
        private String pass;
        private String nick;

        public Entry(String login, String pass,String nick){
            this.login = login;
            this.pass = pass;
            this.nick = nick;
        }
    }

    private List<Entry> enteries;

    public BaseAuthService(){
        enteries = new ArrayList<>();
        enteries.add(new Entry("User1", "Pass1", "Nick1"));
        enteries.add(new Entry("User2", "Pass2", "Nick2"));
    }
}
