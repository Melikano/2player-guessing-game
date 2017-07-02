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
    private static boolean stage3;
    private static boolean isHost;
    private static boolean isGuest;
    private static boolean nameSent;


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

        if (isGuest && stage2) {
            MessageManager messageManager = new MessageManager(selectConnectionMode.getmIp(), selectConnectionMode.getmPort());
            PleaseWait pleaseWait = new PleaseWait();
            pleaseWait.setVisible(true);

            while (stage2) {
                if (messageManager.isStarted() && !nameSent) {
                    messageManager.sendName(selectConnectionMode.getmName());
                    System.out.println("name was sent");
                    nameSent = true;
                }
                System.out.println("h");
                if (messageManager.isHostAccept()) {
                    System.out.println("ow host accepted");
                    pleaseWait.setVisible(false);
                    System.out.println("stage 2 finished stage 3 started");
                    stage2 = false;
                    stage3 = true;
                }
            }
            while (stage3){
                PlayFiled p = new PlayFiled();
                // attach the play fiel
            }

        } else if (isHost && stage2) {
            MessageManager messageManager = new MessageManager(selectConnectionMode.getmPort());
            WaitingForConnection waitingForConnection = new WaitingForConnection();
            waitingForConnection.setVisible(true);
            waitingForConnection.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            while (stage2) {
                System.out.println("k");
                if (messageManager.isGotName()) {
                    System.out.println("got name");
                    waitingForConnection.addConnectionToGUI(messageManager.getName(), messageManager.getIp());
                    messageManager.setGotName(false);
                    waitingForConnection.setVisible(true);
                }

                if (waitingForConnection.isAccepted()) {
                    System.out.println("accepted");
                    messageManager.sendAcceptMessage("127.0.0.1", waitingForConnection.isAccepted());
                    waitingForConnection.setAccepted(false);
                } else if (waitingForConnection.isRejected()) {
                    System.out.println("rejected");
                    messageManager.onSocketClosed(waitingForConnection.getRejectedIp());
                }

                stage2 = waitingForConnection.isCurrStage();
            }

        }

    }

}
