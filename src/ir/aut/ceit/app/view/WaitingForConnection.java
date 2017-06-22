package ir.aut.ceit.app.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WaitingForConnection extends JFrame{

    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> ips = new ArrayList<>();
    private ArrayList<JButton> rejButtons = new ArrayList<>();
    private ArrayList<JButton> accButtons = new ArrayList<>();


    public WaitingForConnection(){
        super("Waiting for connection...");
        setLayout(new BorderLayout(10,20));
        setSize(500, 900);
        setLocation(700, 50);
        JLabel title = new JLabel("Received Connections : ");
        //title.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        add(title,BorderLayout.NORTH);
        System.out.println(title.getWidth() + "  " + title.getHeight());
    }

    public void createWindow(){

        JPanel connections = new JPanel(new GridLayout(20,1));
        connections.setSize(500, 900);

        for(int i = 0; i < names.size(); i++) {
            JPanel connectionPanel = new JPanel(new GridLayout(3, 1));
            JLabel nameLabel = new JLabel(names.get(i));
            JLabel ipLabel = new JLabel(ips.get(i));

            JPanel rejAcc = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton reject = new JButton("Reject");
            JButton accept = new JButton("Accept");
            rejAcc.add(reject);
            rejAcc.add(accept);

            rejButtons.add(reject);
            accButtons.add(accept);

            ConnectionEventHandler handler = new ConnectionEventHandler();

            reject.addActionListener(handler);
            accept.addActionListener(handler);

            connectionPanel.add(nameLabel);
            connectionPanel.add(ipLabel);
            connectionPanel.add(rejAcc);
            connectionPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.black));

            connections.add(connectionPanel);
        }

        JScrollPane scrollPane = new JScrollPane(connections);

        add(scrollPane);

    }

    public void addConnectionToGUI(String name, String ip){
        names.add(name);
        ips.add(ip);
    }

    public class ConnectionEventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            for (int i = 0; i < rejButtons.size(); i++){
                if(event.getSource() == rejButtons.get(i)){
                    System.out.println(names.get(i) + "  rejected");
                    break;
                }
            }

            for (int i = 0; i < accButtons.size(); i++){
                if(event.getSource() == accButtons.get(i)){
                    System.out.println(names.get(i) + "  accepted");
                    break;
                }
            }
        }
    }


}
