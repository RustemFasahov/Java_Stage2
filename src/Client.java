import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends JFrame {
    private JPanel autorizationPanel;
    private JPanel chatPanel;
    private JTextArea textArea;
    private JTextField textField;
    private JTextField loginField;
    private JTextField passwordField;
    private JButton sendButton;
    private JButton autorizationButton;
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private final String HOST = "localhost";
    private final int PORT = 8189;

    public Client() throws HeadlessException {
        setBounds(100,100,300,500);
        setTitle("Чат");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        autorizationPanel = new JPanel(new GridLayout(3,1));
        chatPanel = new JPanel(new GridLayout(3,1));
        loginField = new JTextField("User1");
        passwordField = new JTextField("Pass1");
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textField = new JTextField("Введите сообщение");
        sendButton = new JButton("Отправить сообщение");
        autorizationButton = new JButton("Зарегестрироваться");

        autorizationPanel.add(loginField);
        autorizationPanel.add(passwordField);
        autorizationPanel.add(autorizationButton);

        chatPanel.add(scrollPane);
        chatPanel.add(textField);
        chatPanel.add(sendButton);

        add(autorizationPanel);

        try {
            socket = new Socket(HOST,PORT);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(),true);
//            setAutorised(false);
            Thread t = new Thread(()-> {
                try{
                    while (true){
                        String str = in.nextLine();
                        if(str.startsWith("/authok")){
//                            setAutorised(true);
                            remove(autorizationPanel);
                            add(chatPanel);
                            repaint();
                            revalidate();
                            break;

                        }
                        loginField.setText(str);
                        textArea.append(str + "\n");
                    }
                    while (true){
                        String str = in.nextLine();
                        if(str.equals("end")){
                            break;
                        }
                        textArea.append(str + "\n");
                    }
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
//                setAuthorised(false);
            });
            t.start();
//            t.setDaemon(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        autorizationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAuthClick();
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        setVisible(true);
    }
    public void onAuthClick(){
        out.println("/auth " + loginField.getText() + " " + passwordField.getText());
        loginField.setText("");
        passwordField.setText("");
    }

    public void sendMessage(){
        out.println(textField.getText());
        textField.setText("");
    }

    public static void main(String[] args) {
        new Client();
    }
}
