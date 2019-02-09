import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients;
    private AuthService authService;
    private final int PORT = 8189;

    public AuthService getAuthService(){
        return authService;
    }

    public MyServer(){
        try {
            serverSocket = new ServerSocket(PORT);
            authService = new BaseAuthService();
            clients = new ArrayList<>();
            while (true){
                System.out.println("Сервер ожидает подключения");
                Socket socket = serverSocket.accept();
                System.out.println("Пользователь подключился");
                new ClientHandler(this,socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.out.println("Пользователь отключился");;
            }
        }
    }
    public synchronized boolean isNickBusy(String nick){
        for(ClientHandler o: clients){
            if(o.getName().equals(nick)) return true;
        }
        return false;
    }

    public synchronized void subscribe(ClientHandler o){
        clients.add(o);
    }

    public synchronized void unsubscribe(ClientHandler o){
        clients.remove(o);
    }

    public synchronized void broadCastMsg (String msg){
        if(msg.startsWith("/w")){
            String [] personalMsg = msg.split(" ", 3);
            for (ClientHandler o: clients){
                if(o.getName().equals(personalMsg[1])){
                    o.sendMessage(personalMsg[2]);
                }
            }
        }
        else {
            for (ClientHandler o : clients) {
                o.sendMessage(msg);
            }
        }
    }
}
