package ir.aut.ceit.app.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WaitingForConnection extends JFrame {

    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> ips = new ArrayList<>();
    private ArrayList<JButton> rejButtons = new ArrayList<>();
    private ArrayList<JButton> accButtons = new ArrayList<>();
    private JPanel connections = new JPanel(new GridLayout(20, 1));
    private boolean currStage = true;
    private boolean nextStage;
    private boolean rejected;
    private boolean accepted;
    private String rejectedIp;
    private String acceptedIp;


    public WaitingForConnection() {
        super("Waiting for connection...");
        setLayout(new BorderLayout(10, 20));
        setSize(500, 900);
        setLocation(700, 50);
        JLabel title = new JLabel("Received Connections : ");
        //title.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        add(title, BorderLayout.NORTH);
        connections.setSize(500, 900);
        JScrollPane scrollPane = new JScrollPane(connections);
        add(scrollPane);
    }

    public void addConnectionToGUI(String name, String ip) {
        names.add(name);
        ips.add(ip);

        JPanel connectionPanel = new JPanel(new GridLayout(3, 1));
        JLabel nameLabel = new JLabel(name);
        JLabel ipLabel = new JLabel(ip);

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
        connectionPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));

        connections.add(connectionPanel);


    }
    public void cancelAConnection(String ip){
        for(int i = 0; i < ips.size(); i++){
            if(ips.get(i).equals(ip)){
                rejButtons.get(i).setEnabled(false);
                accButtons.get(i).setEnabled(false);
            }
        }

    }

    public boolean isCurrStage() {
        return currStage;
    }

    public boolean isNextStage() {
        return nextStage;
    }

    public boolean isRejected() {
        return rejected;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getRejectedIp() {
        return rejectedIp;
    }

    public String getAcceptedIp() {
        return acceptedIp;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public class ConnectionEventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            for (int i = 0; i < rejButtons.size(); i++) {
                if (event.getSource() == rejButtons.get(i)) {
                    rejButtons.get(i).setEnabled(false);
                    accButtons.get(i).setEnabled(false);
                    System.out.println(names.get(i) + "  rejected");
                    rejected = true;
                    rejectedIp = ips.get(i);
                    break;
                }
            }

            for (int i = 0; i < accButtons.size(); i++) {
                if (event.getSource() == accButtons.get(i)) {
                    rejButtons.get(i).setEnabled(false);
                    accButtons.get(i).setEnabled(false);
                    System.out.println(names.get(i) + "  accepted");
                    accepted = true;
                    acceptedIp = ips.get(i);
                    nextStage = true;
                    break;
                }
            }
        }
    }


}
