import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Line implements Runnable {
    Socket socket; //Поле класса создающий ссылку на соккет.

    public Line(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            while (true){
                String str = in.nextLine();
                if(str.equals("exit")) break;
                out.println("Сервер" + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
