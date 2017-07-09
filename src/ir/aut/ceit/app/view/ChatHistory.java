package ir.aut.ceit.app.view;

import javax.swing.*;
import java.awt.*;

public class ChatHistory extends JFrame{

    private JPanel messages = new JPanel(new GridLayout(50, 1));

    public ChatHistory(String name) {
        setLayout(new BorderLayout(10, 20));
        setSize(500, 900);
        setLocation(700, 50);
        JLabel title = new JLabel("Chat to " + name);
        // title.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        add(title, BorderLayout.NORTH);
        messages.setSize(500, 900);
        JScrollPane scrollPane = new JScrollPane(messages);
        add(scrollPane);
    }


    public void displayMessageHistory(String from, String message, String time){

        JPanel wholeMessage = new JPanel();
        JLabel displayMessage  = new JLabel(from + " : " + message);
        JPanel messagePanel = new JPanel(new GridLayout(2, 1));
        JLabel timeLabel = new JLabel(time);

        messagePanel.add(displayMessage);
        messagePanel.add(timeLabel);

        if(from.equals("Me")){
            wholeMessage.setLayout(new FlowLayout(FlowLayout.LEFT));
            wholeMessage.add(messagePanel);

        }else {
            wholeMessage.setLayout(new FlowLayout(FlowLayout.RIGHT));
            wholeMessage.add(messagePanel);
        }


        messages.add(wholeMessage);

    }
}
