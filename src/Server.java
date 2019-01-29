import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private final int PORT = 8182;
    private ServerSocket serverSocket;
    private Socket socket;
    private Scanner in = null;
    private String serverName = "Сервер";

    public static void main(String[] args) {
        new Server();
    }

    public Server(){
        try {
            System.out.println("Ожидание подключения к серверу.");
            serverSocket = new ServerSocket(PORT);
            socket = serverSocket.accept();
            in = new Scanner(socket.getInputStream());

            System.out.println("Сервер запущен.");
            System.out.println("Введите сообщение от имени сервера");

            new OutSocket(socket, serverName).start();

            while (true) {
                String messageUser = in.nextLine();
                System.out.println(messageUser);
                if(messageUser.equalsIgnoreCase("exit")) break;
            }

        } catch (IOException e) {
            System.out.println("Ошибка сервера.");

        } finally {
            try {
                serverSocket.close();
                socket.close();
                in.close();
                System.out.println("Сервер закрыт.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
