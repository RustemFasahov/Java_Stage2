import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler {
    private MyServer myServer;
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private String name;

    public String getName(){
        return name;
    }

    public ClientHandler(MyServer myServer, Socket socket){
        this.myServer = myServer;
        this.socket = socket;
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
            name = "";
            new Thread(()-> {
                try {
                    while (true) {
                        String str = in.nextLine();
                        if (str.startsWith("/auth")) {
                            String[] parts = str.split("\\s");
                            String nick = myServer.getAuthService().getNickByLoginPass(parts[1],parts[2]);
                            if(nick != null){
                                if(!myServer.isNickBusy(nick)){
                                    sendMessage("/authok" + nick);
                                    name = nick;
                                    myServer.subscribe(this);
                                    myServer.broadCastMsg(name + " зашел в чат.");
                                    break;
                                } else {
                                    sendMessage("Учетная запись используется.");
                                }
                            }else {
                                sendMessage("Неверный логин/пароль.");
                            }
                        }
                    }
                    while (true){
                        String str = in.nextLine();
                        if(str.equals("/end"))break;
                        myServer.broadCastMsg(name + ":" + str);
                    }
                } finally {
                    myServer.broadCastMsg(name + "вышел из чата.");
                    myServer.unsubscribe(this);
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMessage (String msg){
        out.println(msg);
    }
}
