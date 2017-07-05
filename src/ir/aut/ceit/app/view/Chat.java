package ir.aut.ceit.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Chat extends JFrame {

    private JPanel messages = new JPanel(new GridLayout(50, 1));
    private String myMessage;
    private boolean aMessageSent;

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
                    aMessageSent = true;
                    displayMessage("Me", myMessage);
                }
            }
        });
    }

    public void displayMessage(String from, String message){

        JPanel wholeMessage = new JPanel();
        JLabel displayMessage  = new JLabel(from + " : " + message);
        JPanel messagePanel = new JPanel(new GridLayout(2, 1));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);
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
        setVisible(true);

    }

    public boolean isaMessageSent() {
        return aMessageSent;
    }

    public void setaMessageSent(boolean aMessageSent) {
        this.aMessageSent = aMessageSent;
    }

    public String getMyMessage() {
        return myMessage;
    }
}
