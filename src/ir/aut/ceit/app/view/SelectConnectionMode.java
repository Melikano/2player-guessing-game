package ir.aut.ceit.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectConnectionMode extends JFrame {

    private JRadioButton host = new JRadioButton("Host");
    private JTextField port = new JTextField(21);
    private JRadioButton guest = new JRadioButton("Guest");
    JTextField name = new JTextField(20);
    private JTextField ip = new JTextField(17);
    private JTextField hostPort = new JTextField(17);
    private JButton exit = new JButton("Exit");
    private JButton start = new JButton("Start");
    private boolean nextStage;
    private boolean currStage;
    private boolean isHost;
    private boolean isGuest;
    private String mName;
    private int mPort;
    private String mIp;


    public SelectConnectionMode() {
        super("Select connection mode...");
        setLayout(new GridLayout(6, 1));
        setSize(500, 300);
        setLocation(700, 200);
        createWindow();

    }

    public void createWindow() {

        EventHandler handler = new EventHandler();

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel enterName = new JLabel("Name : ");
        namePanel.add(enterName);

        namePanel.add(name);

        JPanel hostPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        hostPanel.add(host);

        JPanel portPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel enterPort = new JLabel("Port : ");
        portPanel.add(enterPort);
        portPanel.add(port);

        JPanel guestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        guestPanel.add(guest);

        JPanel ipPortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel enterIp = new JLabel("Ip : ");
        ipPortPanel.add(enterIp);

        ipPortPanel.add(ip);
        JLabel enterHostPort = new JLabel("Port : ");
        ipPortPanel.add(enterHostPort);
        ipPortPanel.add(hostPort);

        JPanel exitStart = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitStart.add(exit);
        exitStart.add(start);

        host.addActionListener(handler);
        guest.addActionListener(handler);
        exit.addActionListener(handler);
        start.addActionListener(handler);

        add(namePanel);
        add(hostPanel);
        add(portPanel);
        add(guestPanel);
        add(ipPortPanel);
        add(exitStart);

    }

    public boolean getNextStage() {
        return nextStage;
    }

    public boolean getCurrStage() {
        return currStage;
    }

    public boolean isHost() {
        return isHost;
    }

    public boolean isGuest() {
        return isGuest;
    }

    public String getmName() {
        return mName;
    }

    public int getmPort() {
        return mPort;
    }

    public String getmIp() {
        return mIp;
    }

    public class EventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == host) {
                if (host.isSelected()) {
                    guest.setEnabled(false);
                    ip.setEnabled(false);
                    hostPort.setEnabled(false);
                    isGuest = false;
                    isHost = true;
                } else {
                    guest.setEnabled(true);
                    ip.setEnabled(true);
                    hostPort.setEnabled(true);
                    isGuest = false;
                    isHost = false;
                }
            } else if (event.getSource() == guest) {
                if (guest.isSelected()) {
                    host.setEnabled(false);
                    port.setEnabled(false);
                    isGuest = true;
                    isHost = false;
                } else {
                    host.setEnabled(true);
                    port.setEnabled(true);
                    isGuest = false;
                    isHost = false;
                }
            } else if (event.getSource() == exit) {
                currStage = false;
                nextStage = false;
                dispose();

            } else if (event.getSource() == start) {
                currStage = false;
                nextStage = true;
                mName = name.getText();
                mIp = ip.getText();
                if (isGuest) {
                    mPort = Integer.parseInt(hostPort.getText());
                }else if(isHost){
                    mPort = Integer.parseInt(port.getText());
                }
                dispose();
            }
        }
    }


}
