import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String HOST = "localhost";
    private final int PORT = 8182;

    private Socket socket;
    private Scanner in;
    private Scanner userIn;
    private String userName = "Виталий";

    public Client() {
        try {
            socket = new Socket(HOST, PORT);
            in = new Scanner(socket.getInputStream());
            userIn = new Scanner(System.in);

            System.out.println("К серверу подключен.");
            System.out.println("Введите сообщение.");

            new OutSocket(socket, userName).start();

            while (true) {
                String messageServer = in.nextLine();
                System.out.println(messageServer);
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                socket.close();
                in.close();
                userIn.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
