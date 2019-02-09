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
    private boolean conection;
    private float lastTime;
    private float carentTime;
    private float deltaTime;

    public String getName(){
        return name;
    }

    public ClientHandler(MyServer myServer, Socket socket){
        conection = true;
        lastTime = System.nanoTime();
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
                            String nick = myServer.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                            if (nick != null) {
                                if (!myServer.isNickBusy(nick)) {
                                    sendMessage("/authok" + nick);
                                    name = nick;
                                    myServer.subscribe(this);
                                    myServer.broadCastMsg(name + " зашел в чат.");
                                    break;
                                } else {
                                    sendMessage("Учетная запись используется.");
                                }
                            } else {
                                sendMessage("Неверный логин/пароль.");
                            }
                        }
                    }
                    while (true) {
                        if (conection == false) break;
                        String str = in.nextLine();
                        if (str.equals("/end")) break;
                        myServer.broadCastMsg(name + ":" + str);
                    }
                }finally {
                    myServer.broadCastMsg(name + "вышел из чата.");
                    myServer.unsubscribe(this);
                    try {

                        socket.close();
                        in.close();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            while (true) {
                carentTime = System.nanoTime();
                deltaTime = (carentTime - lastTime) / 1000000000;
                if (deltaTime > 120) {
                    conection = false;
                    sendMessage("Ошибка авторизации перезапустите программу.");
                    socket.close();
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMessage (String msg){
        out.println(msg);
    }
}
