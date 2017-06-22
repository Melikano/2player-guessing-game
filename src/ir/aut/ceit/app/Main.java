package ir.aut.ceit.app;

import ir.aut.ceit.app.logic.MessageManager;
import ir.aut.ceit.app.view.PlayFiled;
import ir.aut.ceit.app.view.SelectConnectionMode;
import ir.aut.ceit.app.view.WaitingForConnection;

import javax.swing.*;
import java.io.*;


public class Main {

    private static boolean stage1 = true;
    private static boolean stage2;
    private static boolean stage3;
    private static boolean isHost;
    private static boolean isGuest;

    public Main() throws IOException {
    }

    public static void main(String[] args) throws IOException {


        SelectConnectionMode selectConnectionMode = new SelectConnectionMode(stage1, stage2);
        selectConnectionMode.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        selectConnectionMode.setVisible(true);


        while (!stage2 && stage1) {
            selectConnectionMode.setVisible(true);
            stage2 = selectConnectionMode.getNextStage();
            stage1 = selectConnectionMode.getCurrStage();
            isGuest = selectConnectionMode.isGuest();
            isHost = selectConnectionMode.isHost();
        }

        if (stage2 && isGuest) {
            MessageManager messageManager = new MessageManager(selectConnectionMode.getmIp(), selectConnectionMode.getmPort());

            while (!messageManager.isStarted()) {
                System.out.println("wait for connection");
            }

            messageManager.sendNameAndIp(selectConnectionMode.getmIp(), selectConnectionMode.getmName(), "123.4.5.6");
            /*JFrame frame = new JFrame("Game");
            PlayFiled play = new PlayFiled();
            frame.setSize(400, 500);
            frame.add(play);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);*/

        }

        if (stage2 && isHost) {
            WaitingForConnection s = new WaitingForConnection();
            s.addConnectionToGUI("melika", "127.0.0.1");
            s.addConnectionToGUI("sara", "198.168.0.49");
            s.addConnectionToGUI("helen", "127.0.0.1");
            s.addConnectionToGUI("alex", "198.168.0.49");
            s.addConnectionToGUI("susi", "127.0.0.1");
            s.addConnectionToGUI("kate", "198.168.0.49");
            s.addConnectionToGUI("emma", "127.0.0.1");
            s.addConnectionToGUI("regina", "198.168.0.49");
            s.addConnectionToGUI("joe", "127.0.0.1");
            s.addConnectionToGUI("david", "198.168.0.49");
            s.createWindow();
            s.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            s.setVisible(true);
        }

    }


}
