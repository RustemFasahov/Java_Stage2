import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow extends JFrame {

    JPanel panel;

    public ChatWindow() {

        setBounds(450,150,400,440);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Чат");

        panel = new JPanel(new GridLayout(3,1));
        JTextField storyOfChat = new JTextField();
        JTextField message = new JTextField();
        JButton but = new JButton("Отправить");

        storyOfChat.setEditable(false);
        message.setToolTipText("Введите сообщение");

        panel.add(storyOfChat);
        panel.add(message);
        panel.add(but);
        add(panel);
        setVisible(true);

        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storyOfChat.setText(message.getText());
            }
        });
    }
}
