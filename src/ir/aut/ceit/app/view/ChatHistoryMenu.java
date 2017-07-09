package ir.aut.ceit.app.view;

import ir.aut.ceit.app.model.ReadFromJson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class ChatHistoryMenu extends JFrame {

    private JPanel chats = new JPanel(new GridLayout(20, 1));

    public ChatHistoryMenu() {
        super("Conversation History...");
        setLayout(new BorderLayout(10, 20));
        setSize(500, 900);
        setLocation(700, 50);
        chats.setSize(500, 900);
        JScrollPane scrollPane = new JScrollPane(chats);
        add(scrollPane);
        addChat();
    }

    public void addChat(){
        ReadFromJson reader = new ReadFromJson();
        reader.readJSon("sara.json");
        System.out.println("read");
        String name = reader.gethName();
        JLabel chatName = new JLabel(name);
        JLabel chatDate = new JLabel(reader.getDate());
        JPanel chatPanel = new JPanel(new GridLayout(3, 1));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton view = new JButton("view");
        buttonPanel.add(view);
        chatPanel.add(chatName);
        chatPanel.add(chatDate);
        chatPanel.add(buttonPanel);
        chatPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));

        ChatHistory chatHistory = new ChatHistory(name);

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == view){
                    JSONArray msg = reader.getMsg();
                    Iterator<JSONObject> iterator = msg.iterator();
                    while ( iterator.hasNext()) {
                        JSONObject j = iterator.next();
                        String sender = (String) j.get("sender");
                        String imesg = (String) j.get("mesg");
                        System.out.println(imesg);
                        String time = (String) j.get("time");
                        System.out.println(time);
                        chatHistory.displayMessageHistory(sender,imesg, time);
                    }
                    chatHistory.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    chatHistory.setLocation(1200, 50);
                    chatHistory.setVisible(true);
                }
            }
        });
        chats.add(chatPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        ChatHistoryMenu chatHistoryMenu = new ChatHistoryMenu();
        chatHistoryMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        chatHistoryMenu.setVisible(true);

    }
}
