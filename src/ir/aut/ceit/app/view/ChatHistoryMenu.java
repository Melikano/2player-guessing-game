package ir.aut.ceit.app.view;

import ir.aut.ceit.app.model.ReadFromJson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class ChatHistoryMenu extends JFrame {

    private JPanel chats = new JPanel(new GridLayout(20, 1));
    private ArrayList<String> fileNames = new ArrayList<>();
    private ArrayList<JButton> viewButtons = new ArrayList<>();
    private ArrayList<ReadFromJson> readers = new ArrayList<>();


    public ChatHistoryMenu() {
        super("Conversation History...");
        setLayout(new BorderLayout(10, 20));
        setSize(500, 900);
        setLocation(700, 50);
        chats.setSize(500, 900);
        JScrollPane scrollPane = new JScrollPane(chats);
        add(scrollPane);
        getAllFilesNames();
        addChat();
    }

    private void getAllFilesNames() {
        File folder = new File("src");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".json")) {
                fileNames.add("src/" + listOfFiles[i].getName());
            }
        }
    }

    public void addChat() {
        for (String fileName : fileNames) {
            ReadFromJson reader = new ReadFromJson();
            readers.add(reader);
            reader.readJSon(fileName);
            System.out.println("read");
            String name = reader.gethName();
            JLabel chatName = new JLabel(name);
            JLabel chatDate = new JLabel(reader.getDate());
            JPanel chatPanel = new JPanel(new GridLayout(3, 1));
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton view = new JButton("view");
            viewButtons.add(view);
            buttonPanel.add(view);
            chatPanel.add(chatName);
            chatPanel.add(chatDate);
            chatPanel.add(buttonPanel);
            chatPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
            chats.add(chatPanel);
            setVisible(true);
        }
        EventHandler handler = new EventHandler();
        for (JButton view : viewButtons){
            view.addActionListener(handler);
        }
    }

    public class EventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            for (int i = 0; i < viewButtons.size(); i++) {
                if (event.getSource() == viewButtons.get(i)) {

                    ChatHistory chatHistory = new ChatHistory(readers.get(i).gethName());
                    JSONArray msg = readers.get(i).getMsg();
                    Iterator<JSONObject> iterator = msg.iterator();
                    while (iterator.hasNext()) {
                        JSONObject j = iterator.next();
                        String sender = (String) j.get("sender");
                        String imesg = (String) j.get("mesg");
                        String time = (String) j.get("time");
                        chatHistory.displayMessageHistory(sender, imesg, time);
                    }
                    chatHistory.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    chatHistory.setLocation(1200, 50);
                    chatHistory.setVisible(true);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        ChatHistoryMenu chatHistoryMenu = new ChatHistoryMenu();
        chatHistoryMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        chatHistoryMenu.setVisible(true);

    }
}
