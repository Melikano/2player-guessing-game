package ir.aut.ceit.app;

import ir.aut.ceit.app.logic.MessageManager;
import ir.aut.ceit.app.view.PlayFiled;
import ir.aut.ceit.app.view.PleaseWait;
import ir.aut.ceit.app.view.SelectConnectionMode;
import ir.aut.ceit.app.view.WaitingForConnection;

import javax.swing.*;
import java.io.*;


public class Main {

    private static boolean stage1 = true;
    private static boolean stage2;
    private static boolean isHost;
    private static boolean isGuest;


    public Main() throws IOException {
    }

    public static void main(String[] args) throws IOException {


        SelectConnectionMode selectConnectionMode = new SelectConnectionMode();
        selectConnectionMode.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        selectConnectionMode.setVisible(true);


        while (stage1) {
            selectConnectionMode.setVisible(true);
            stage2 = selectConnectionMode.getNextStage();
            stage1 = selectConnectionMode.getCurrStage();
            isGuest = selectConnectionMode.isGuest();
            isHost = selectConnectionMode.isHost();
        }

        if (isGuest) {

            PleaseWait pleaseWait = new PleaseWait();
            pleaseWait.setVisible(true);
            while (stage2) {

                MessageManager messageManager = new MessageManager(selectConnectionMode.getmIp(), selectConnectionMode.getmPort());
                if (messageManager.isStarted()) {
                    messageManager.sendNameAndIp(selectConnectionMode.getmIp(), selectConnectionMode.getmName(), "123.4.5.6");
                }
            }

        } else if (isHost) {

            MessageManager messageManager = new MessageManager(selectConnectionMode.getmPort());
            WaitingForConnection waitingForConnection = new WaitingForConnection();
            waitingForConnection.setVisible(true);
            waitingForConnection.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            while (stage2) {
                if (messageManager.isGotName()) {
                    waitingForConnection.addConnectionToGUI(messageManager.getName(), messageManager.getIp());
                    messageManager.setGotName(false);
                    waitingForConnection.setVisible(true);
                }

                stage2 = waitingForConnection.isCurrStage();
            }
        }

    }

}
