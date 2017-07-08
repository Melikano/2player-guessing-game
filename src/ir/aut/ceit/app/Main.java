package ir.aut.ceit.app;

import ir.aut.ceit.app.logic.MessageManager;
import ir.aut.ceit.app.view.*;

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

    public static void main(String[] args) throws IOException, InterruptedException {


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
                    messageManager.sendName("", selectConnectionMode.getmName());
                    System.out.println("name was sent");
                    nameSent = true;
                }
                System.out.print("");

                if (messageManager.isHostAccept()) {
                    System.out.println("ow host accepted");
                    pleaseWait.setVisible(false);
                    System.out.println("stage 2 finished stage 3 started");
                    stage2 = false;
                    stage3 = true;
                }
                if (messageManager.isHostReject()) {
                    System.out.println("ow host rejected me");
                    pleaseWait.setVisible(false);
                    ConnectionClosed connectionClosed = new ConnectionClosed();
                    connectionClosed.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    connectionClosed.setVisible(true);
                    messageManager.onSocketClosed("");
                    stage2 = false;
                    stage3 = false;
                }
                if (pleaseWait.isConnectionCanceled()) {
                    System.out.println("i canceled");
                    messageManager.sendCancelMessage(pleaseWait.isConnectionCanceled());
                    pleaseWait.setVisible(false);
                    ConnectionClosed connectionClosed = new ConnectionClosed();
                    connectionClosed.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    connectionClosed.setVisible(true);
                    messageManager.onSocketClosed("");
                    stage2 = false;
                    stage3 = false;
                }
            }
            if (stage3) {
                PlayFrame playFrame = new PlayFrame();
                playFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                playFrame.setVisible(true);
                Chat chat = new Chat(messageManager.getName());
                chat.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                chat.setVisible(true);

                while (stage3) {

                    if (chat.isaMessageSent()) {
                        messageManager.sendTextMessage("", chat.getMyMessage());
                        chat.setaMessageSent(false);
                    }

                    if (playFrame.getMouseClickHandler().isClicked()) {
                        while (true) {
                            if (playFrame.getMouseClickHandler().isCoordinationUpdated()) {
                                int x = playFrame.getMouseClickHandler().getX();
                                int y = playFrame.getMouseClickHandler().getY();
                                System.out.println("x : " + x + "y : " + y);
                                messageManager.sendCoordinationMessage("", x + "", y + "");
                                break;
                            }
                        }
                        playFrame.getMouseClickHandler().setClicked(false);
                    }

                    if (messageManager.isGotCoordination()) {
                        String str = playFrame.myHitandLose(messageManager.getX(), messageManager.getY());
                        System.out.println("sent : " + str);
                        messageManager.sendIsHitCoordinationMessage("", str);
                        messageManager.setGotCoordination(false);
                    }

                    System.out.print("");

                    if (messageManager.isGotTextMessage()) {
                        chat.displayMessage(messageManager.getName(), messageManager.getTextMessage());
                        messageManager.setGotTextMessage(false);
                    }

                    if (messageManager.isGotIsHitCoordinationMessage()) {
                        playFrame.enemyHitandLose(messageManager.getHitCoordination());
                        messageManager.setGotIsHitCoordinationMessage(false);
                    }
                }


            }

        } else if (isHost && stage2) {
            MessageManager messageManager = new MessageManager(selectConnectionMode.getmPort());
            WaitingForConnection waitingForConnection = new WaitingForConnection();
            waitingForConnection.setVisible(true);
            waitingForConnection.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            while (stage2) {

                System.out.print("");

                if (messageManager.isGotName()) {
                    System.out.println("got name");
                    waitingForConnection.addConnectionToGUI(messageManager.getName(), messageManager.getIp());
                    messageManager.setGotName(false);
                    waitingForConnection.setVisible(true);
                }

                if (waitingForConnection.isAccepted()) {
                    System.out.println("accepted");
                    messageManager.sendAcceptMessage(waitingForConnection.getAcceptedIp(), waitingForConnection.isAccepted());
                    messageManager.sendName(waitingForConnection.getAcceptedIp(), selectConnectionMode.getmName());
                    waitingForConnection.setAccepted(false);
                    stage3 = true;
                    stage2 = false;
                }
                if (waitingForConnection.isRejected()) {
                    System.out.println("rejected");
                    messageManager.sendRejectMessage(waitingForConnection.getRejectedIp(), waitingForConnection.isRejected());
                    while (true) {
                        if (messageManager.isMessageSent(waitingForConnection.getRejectedIp())) {
                            messageManager.onSocketClosed(waitingForConnection.getRejectedIp());
                            break;
                        }
                    }
                    waitingForConnection.setRejected(false);
                }
                if (messageManager.isGuestCancel()) {
                    System.out.println("guest canceled");
                    waitingForConnection.cancelAConnection(messageManager.getCanceledIp());
                    messageManager.onSocketClosed(messageManager.getCanceledIp());
                    messageManager.setGuestCancel(false);
                }
                stage2 = waitingForConnection.isCurrStage() && stage2;
            }
            if (stage3) {

                PlayFrame playFrame = new PlayFrame();
                playFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                playFrame.setVisible(true);

                Chat chat = new Chat(messageManager.getName());
                chat.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                chat.setVisible(true);

                while (stage3) {

                    if (chat.isaMessageSent()) {
                        messageManager.sendTextMessage(waitingForConnection.getAcceptedIp(), chat.getMyMessage());
                        chat.setaMessageSent(false);
                    }

                    if (playFrame.getMouseClickHandler().isClicked()) {
                        while (true) {
                            if (playFrame.getMouseClickHandler().isCoordinationUpdated()) {
                                int x = playFrame.getMouseClickHandler().getX();
                                int y = playFrame.getMouseClickHandler().getY();
                                System.out.println("x : " + x + "y : " + y);
                                messageManager.sendCoordinationMessage(waitingForConnection.getAcceptedIp(), x + "", y + "");
                                break;
                            }
                        }
                        playFrame.getMouseClickHandler().setClicked(false);
                    }

                    if (messageManager.isGotCoordination()) {
                        String str = playFrame.myHitandLose(messageManager.getX(), messageManager.getY());
                        System.out.println("sent : " + str);
                        messageManager.sendIsHitCoordinationMessage(waitingForConnection.getAcceptedIp(), str);
                        messageManager.setGotCoordination(false);
                    }

                    System.out.print("");

                    if (messageManager.isGotTextMessage()) {
                        chat.displayMessage(waitingForConnection.getAcceptedName(), messageManager.getTextMessage());
                        messageManager.setGotTextMessage(false);
                    }

                    if (messageManager.isGotIsHitCoordinationMessage()) {
                        playFrame.enemyHitandLose(messageManager.getHitCoordination());
                        messageManager.setGotIsHitCoordinationMessage(false);
                    }

                }


            }

        }

    }

}
