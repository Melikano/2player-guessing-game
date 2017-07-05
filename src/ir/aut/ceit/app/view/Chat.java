package ir.aut.ceit.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chat extends JFrame {

    private JPanel messages = new JPanel(new GridLayout(50, 1));
    private String myMessage;

    public Chat(String name) {
        setLayout(new BorderLayout(10, 20));
        setSize(500, 900);
        setLocation(700, 50);
        JLabel title = new JLabel("Chat to " + name);
       // title.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        add(title, BorderLayout.NORTH);
        messages.setSize(500, 900);
        JScrollPane scrollPane = new JScrollPane(messages);
        add(scrollPane);
        SendNewMessage();
    }

    private void SendNewMessage() {

        JPanel sendMessagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField mMessage = new JTextField("type here...      ");
        mMessage.setColumns(30);
        JButton sendButton = new JButton("Send");
        sendMessagePanel.add(mMessage);
        sendMessagePanel.add(sendButton);
        add(sendMessagePanel, BorderLayout.SOUTH);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == sendButton) {
                    myMessage = mMessage.getText();
                    System.out.println(myMessage);
                    displayMessage("Me", myMessage);
                }
            }
        });
    }

    public void displayMessage(String from, String message){
        JPanel messagePanel = new JPanel(new BorderLayout());
        JLabel displayMessage = new JLabel(from + " : " + message+ "  ");
        if(from.equals("Me")){
            messagePanel.add(displayMessage, BorderLayout.WEST);
        }else {
            messagePanel.add(displayMessage, BorderLayout.EAST);
        }

        messages.add(messagePanel);
        setVisible(true);

    }

}
