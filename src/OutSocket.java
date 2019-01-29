import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Time;
import java.util.Scanner;

public class OutSocket extends Thread {
    Socket socket;
    Scanner userOut;
    PrintWriter out;
    String userName;

    public OutSocket(Socket socket, String userName) {
        this.socket = socket;
        this.userName = userName;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(),true);
            userOut = new Scanner(System.in);
            while (true) {
                String userMessage = userOut.nextLine();
                out.println(userName + ": " + userMessage);
            }

        } catch (IOException e1) {
            e1.printStackTrace();

        }finally {
            try {
                socket.close();
                out.close();
                userOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
