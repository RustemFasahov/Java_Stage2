
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class UserSide extends JFrame { //Создаем класс пользовательская сторона
    Socket socket; //Создаем ссылку на сокет.
    Scanner in; //Ссылка на объект ввода.
    PrintWriter out; //Ссылка на объект отправки.
    JPanel panel; //Создаем ссылку на панель, на которой будут кнопки и текстовые поля.
    JTextArea textArea; //Ссылка на текстовою многосторочную арену.
    JTextField textField; //Ссылка на однострочное текстовое поле.
    JButton button; //Ссылка на кнопку.

    public static void main(String[] args) {
        new UserSide(); //Сделано для того чтобы мы могли запустить отсюда программу.
    }

    public UserSide() {//Конструктор при запуске класса.

        try {
            socket = new Socket("localhost",8182); //Присвоили ссылке объект типа Socket, которой передается
            //IP адресс и номер порта больше 1024 зарезервервированных портов.
            in = new Scanner(socket.getInputStream()); //Создание объекта считывания.
            out = new PrintWriter(socket.getOutputStream(),true);//Создание объекта отправки, с автоматической очисткой буфера.
        } catch (IOException e) {
            e.printStackTrace();
        }

        setBounds(200,200,300,300); //Координаты и размеры окна.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Создание закрытия окна.

        panel = new JPanel(new GridLayout(3,1)); //Создание объекта панели, на которой создается объект
        // содержащая 3 строки и 1 колонну.
        textArea = new JTextArea(); //Создание текстового поля многострочного.
        textArea.setLineWrap(true);//Если текст не вмещается то переносится на новую строку.
        textArea.setEditable(false); //Запрещаем редактирование поля.
        JScrollPane scrollPane = new JScrollPane(textArea); //Создаем полосу прокрутки в которую передаем текстовое поле.

        textField = new JTextField("Введите сообщение"); //Создание объекта текстового поля однострочного для ввода сообщения.
        button = new JButton("Отправить"); //Создание объекта кнопка для отправки сообщения.

        panel.add(scrollPane);
        panel.add(textField);
        panel.add(button);
        add(panel);//Добавляем во фрейм панель с предварительно добавленными в него текстовыми полями и кнопкой.

        setVisible(true);//Делаем наш экран видимым.

        new Thread(new Runnable() { //Создаем новый поток для принятия сообщений с сервера.
            @Override
            public void run() { //Переопределяем метод run
                while (true){ //Создание бесконечного цикла прослушивания.
                    if(in.hasNextLine()){ //Метод возвращает true если есть следующая входная строка.
                        String w = in.nextLine(); //Переменной присваивается значение прочитанной строки.
                        textArea.append(w); //Данный метод добавляет к имеющимся текст из переменной.
                        textArea.append("\n"); //Добавляет новую строку.
                    }
                }
            }
        }).start(); //Запускаем поток.

        button.addActionListener(new ActionListener() { //Добавлем слушатель кнопки.
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textField.getText().isEmpty()) { //Говорим если текстовое поле не пустое, то выполни.
                    out.println(textField.getText()); //Отправляем на сервер содержимое текста для ввода.
                    textField.setText(""); //Делаем текстовое поле для ввода пустым.
                }
            }
        });

        addWindowListener(new WindowAdapter() { //Добавляем слушатель экрана.
            @Override
            public void windowClosing(WindowEvent e) { //Который при закрытии.
                super.windowClosing(e); //Выключит экран.
                try {
                    socket.close(); //Закроет сокет.
                    in.close(); //Закроет ввод.
                    out.close(); //И вывод.
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
