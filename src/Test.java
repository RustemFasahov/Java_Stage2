import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        //По идее нужно делать сервер многопоточным. Создать отдельный класс реализовать в нем интерфейс Runnuble.

        try {
            ServerSocket serverSocket = new ServerSocket(8182);
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new Line(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Реализация сервера без многопоточности.

//        try {
//            serv = new ServerSocket(8182); //На серверном сокете указывается только порт.
//            soc = serv.accept(); //Нужен для того чтобы ждать действия пользователя.
//            Scanner scanner = new Scanner(soc.getInputStream()); //Объект который принимает действия пользователя.
//            PrintWriter writer = new PrintWriter(soc.getOutputStream(),true);//Объект который отсылает пользователю
//            //Нужно писать autoFlush - true, для того чтобы сразу очищался буфер обмена.
//
//            while (true){
//                String str = scanner.nextLine(); //В данном случае считывается введенная строчка пользователя.
//                if(str.equals("exit")) break; //Если присылают слово exit то бесконечный цикл прерывается и сервер закрывается.
//                writer.println("Сервер " + str); //Данная строчка отправляет пользователю ответ.
//                System.out.println(str); //Для отслеживания работы программы. Вывод в консоль.
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                serv.close(); //Закрываем сервер для того чтобы не грузила систему.
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
