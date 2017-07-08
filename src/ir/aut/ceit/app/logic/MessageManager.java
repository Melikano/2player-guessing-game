package ir.aut.ceit.app.logic;


import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageManager implements ServerSocketHandler.IServerSocketHandlerCallback, NetworkHandler.INetworkHandlerCallback {

    private ServerSocketHandler mServerSocketHandler;
    private List<NetworkHandler> mNetworkHandlerList = new ArrayList<>();
    private static int count;
    private boolean isHost;
    private boolean isGuest;
    private boolean connectionStarted;
    private boolean gotName;
    private boolean hostAccept;
    private boolean hostReject;
    private boolean guestCancel;
    private String canceledIp;
    private String name;
    private int x;
    private int y;
    private boolean gotCoordination;
    private String textMessage;
    private boolean gotTextMessage;
    private String hitCoordination;
    private boolean gotIsHitCoordinationMessage;
    private boolean left;
    private boolean gameStart;
    private boolean isYourTurn;


    /**
     * Instantiate server socket handler and start it. (Call this constructor in host mode)
     */
    public MessageManager(int port) {
        mServerSocketHandler = new ServerSocketHandler(port, this, this);
        isHost = true;
        mServerSocketHandler.start();

    }

    /**
     * Instantiate a network handler and start it. (Call this constructor in guest mode)
     */
    public MessageManager(String ip, int port) {

        try {
            NetworkHandler networkHandler = new NetworkHandler(new Socket(ip, port), this);
            isGuest = true;
            mNetworkHandlerList.add(networkHandler);
            networkHandler.start();
            connectionStarted = true;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void sendTextMessage(String to, String text) {
        TextMessage textMessage = new TextMessage(text);
        textMessage.serialize();

        if (isHost) {
            for (NetworkHandler networkHandler : mNetworkHandlerList) {
                if (networkHandler.getmTcpChannel().getIp().equals(to)) {
                    networkHandler.sendMessage(textMessage);
                    break;
                }
            }
        } else if (isGuest) {
            mNetworkHandlerList.get(0).sendMessage(textMessage);
        }

    }

    public void sendCoordinationMessage(String to, String coordinationX1, String coordinationY1) {
        System.out.println("sending x = " + coordinationX1 + "sending y = " + coordinationY1);
        CoordinationPlayMessage coordinationPlayMessage = new CoordinationPlayMessage(coordinationX1, coordinationY1);
        if (isHost) {
            for (NetworkHandler networkHandler : mNetworkHandlerList) {
                if (networkHandler.getmTcpChannel().getIp().equals(to)) {
                    networkHandler.sendMessage(coordinationPlayMessage);
                    break;
                }
            }
        } else if (isGuest) {
            mNetworkHandlerList.get(0).sendMessage(coordinationPlayMessage);
        }
    }

    public void sendName(String to, String name) {
        NameMessage nameMessage = new NameMessage(name);
        nameMessage.serialize();
        if (isHost) {
            for (NetworkHandler networkHandler : mNetworkHandlerList) {
                if (networkHandler.getmTcpChannel().getIp().equals(to)) {
                    networkHandler.sendMessage(nameMessage);
                    break;
                }
            }
        } else if (isGuest) {
            mNetworkHandlerList.get(0).sendMessage(nameMessage);
        }
    }

    public void sendAcceptMessage(String to, boolean isAccept) {
        AcceptMessage acceptMessage = new AcceptMessage(isAccept);
        acceptMessage.serialize();
        for (NetworkHandler networkHandler : mNetworkHandlerList) {
            if (networkHandler.getmTcpChannel().getIp().equals(to)) {
                networkHandler.sendMessage(acceptMessage);
                break;
            }
        }
    }

    public void sendRejectMessage(String to, boolean isReject) {
        RejectMessage rejectMessage = new RejectMessage(isReject);
        rejectMessage.serialize();
        for (NetworkHandler networkHandler : mNetworkHandlerList) {
            if (networkHandler.getmTcpChannel().getIp().equals(to)) {
                networkHandler.sendMessage(rejectMessage);
                break;
            }
        }
    }

    public void sendCancelMessage(boolean isCanceled) {
        CancelMessage cancelMessage = new CancelMessage(isCanceled);
        cancelMessage.serialize();
        mNetworkHandlerList.get(0).sendMessage(cancelMessage);
    }

    public void sendLeaveMessage(String to, boolean isLeft) {
        LeaveMessage leaveMessage = new LeaveMessage(isLeft);
        leaveMessage.serialize();
        if (isHost) {
            for (NetworkHandler networkHandler : mNetworkHandlerList) {
                if (networkHandler.getmTcpChannel().getIp().equals(to)) {
                    networkHandler.sendMessage(leaveMessage);
                    break;
                }
            }
        } else if (isGuest) {
            mNetworkHandlerList.get(0).sendMessage(leaveMessage);
        }

    }

    public void sendStartMessage(String to, boolean isStart) {
        StartMessage startMessage = new StartMessage(isStart);
        startMessage.serialize();
        if (isHost) {
            for (NetworkHandler networkHandler : mNetworkHandlerList) {
                if (networkHandler.getmTcpChannel().getIp().equals(to)) {
                    networkHandler.sendMessage(startMessage);
                    break;
                }
            }
        } else if (isGuest) {
            mNetworkHandlerList.get(0).sendMessage(startMessage);
        }

    }

    public void sendIsHitCoordinationMessage(String to, String hitCoordination){
        IsHitCoordinationMessage isHitCoordinationMessage = new IsHitCoordinationMessage(hitCoordination);
        isHitCoordinationMessage.serialize();
        if (isHost) {
            for (NetworkHandler networkHandler : mNetworkHandlerList) {
                if (networkHandler.getmTcpChannel().getIp().equals(to)) {
                    networkHandler.sendMessage(isHitCoordinationMessage);
                    break;
                }
            }
        } else if (isGuest) {
            mNetworkHandlerList.get(0).sendMessage(isHitCoordinationMessage);
        }

    }

    public void sendFirstTurnMessage(String to, boolean isYourTurn) {
        FirstTurnSpecifier firstTurnSpecifier = new FirstTurnSpecifier(isYourTurn);
        firstTurnSpecifier.serialize();
        if (isHost) {
            for (NetworkHandler networkHandler : mNetworkHandlerList) {
                if (networkHandler.getmTcpChannel().getIp().equals(to)) {
                    networkHandler.sendMessage(firstTurnSpecifier);
                    break;
                }
            }
        }
    }

    private void consumeCoordinationMessage(CoordinationPlayMessage message) {
        message.deserialize();
        x = Integer.parseInt(message.getCoordinationX());
        y = Integer.parseInt(message.getCoordinationY());
        gotCoordination = true;
        System.out.println("x is " + x + " y is " + y);
    }


    private void consumeTextMessage(TextMessage message) {
        message.deserialize();
        textMessage = message.getText();
        gotTextMessage = true;
    }

    private void consumeNameMessage(NameMessage message) {
        message.deserialize();
        name = message.getmName();
        gotName = true;
    }

    private void consumeAcceptMessage(AcceptMessage message) {
        message.deserialize();
        hostAccept = message.ismAccept();

    }

    private void consumeRejectMessage(RejectMessage message) {
        message.deserialize();
        hostReject = message.ismReject();

    }

    private void consumeCancelMessage(CancelMessage message, String senderIp) {
        message.deserialize();
        guestCancel = message.ismCancel();
        canceledIp = senderIp;

    }

    private void consumeLeaveMessage(LeaveMessage message){
        message.deserialize();
        left = message.ismLeave();
    }

    private void consumeStartMessage(StartMessage message){
        message.deserialize();
        gameStart = message.ismStart();
    }

    private void consumeFirstTurnMessage(FirstTurnSpecifier message){
        message.deserialize();
        isYourTurn = message.ismIsYourTurn();
    }

    private void consumeIsHitCoordinationMessage(IsHitCoordinationMessage message){
        message.deserialize();
        hitCoordination = message.getmHitCoordination();
        gotIsHitCoordinationMessage = true;
        System.out.println("received : " + hitCoordination);
    }

    public boolean isMessageSent(String to) {
        for (NetworkHandler networkHandler : mNetworkHandlerList) {
            if (networkHandler.getmTcpChannel().getIp().equals(to)) {
                if (networkHandler.isMessageSent()) {
                    return true;
                }
                break;
            }
        }
        return false;
    }


    /**
     * Add network handler to the list.
     */
    @Override
    public void onNewConnectionReceived(NetworkHandler networkHandler) {

        mNetworkHandlerList.add(networkHandler);
        count++;
        networkHandler.start();
        System.out.println("new connection received");
        connectionStarted = true;
    }

    @Override
    public void onMessageReceived(BaseMessage baseMessage, String senderIp) {
        switch (baseMessage.getMessageType()) {
            case MessageTypes.TEXT_MESSAGE:
                consumeTextMessage((TextMessage) baseMessage);
                break;
            case MessageTypes.PLAYER_COORDINATION:
                consumeCoordinationMessage((CoordinationPlayMessage) baseMessage);
                break;
            case MessageTypes.NAME_MESSAGE:
                consumeNameMessage((NameMessage) baseMessage);
                break;
            case MessageTypes.ACCEPT_MESSAGE:
                consumeAcceptMessage((AcceptMessage) baseMessage);
                break;
            case MessageTypes.REJECT_MESSAGE:
                consumeRejectMessage((RejectMessage) baseMessage);
                break;
            case MessageTypes.CANCEL_MESSAGE:
                consumeCancelMessage((CancelMessage) baseMessage, senderIp);
                break;
            case MessageTypes.ISHITCOORDINATION_MESSAGE :
                consumeIsHitCoordinationMessage((IsHitCoordinationMessage) baseMessage);
                break;
            case MessageTypes.LEAVE_MESSAGE :
                consumeLeaveMessage((LeaveMessage) baseMessage);
                break;
            case MessageTypes.START_MESSAGE :
                consumeStartMessage((StartMessage) baseMessage);
                break;
            case MessageTypes.FIRSTTURN_MESSAGE :
                consumeFirstTurnMessage((FirstTurnSpecifier) baseMessage);
                break;
        }
    }

    @Override
    public void onSocketClosed(String closedIp) throws InterruptedException {
        if (isHost) {
            for (NetworkHandler networkHandler : mNetworkHandlerList) {
                if (networkHandler.getmTcpChannel().getIp().equals(closedIp)) {
                    networkHandler.stopSelf();
                    break;
                }
            }
        } else if (isGuest) {
            mNetworkHandlerList.get(0).stopSelf();
        }
    }

    public boolean isconnectionStarted() {
        return connectionStarted;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return mNetworkHandlerList.get(count - 1).getmTcpChannel().getIp();
    }

    public boolean isGotName() {
        return gotName;
    }

    public void setGotName(boolean value) {
        gotName = value;
    }

    public boolean isHostAccept() {
        return hostAccept;
    }

    public boolean isHostReject() {
        return hostReject;
    }

    public boolean isGuestCancel() {
        return guestCancel;
    }

    public void setGuestCancel(boolean guestCancel) {
        this.guestCancel = guestCancel;
    }

    public String getCanceledIp() {
        return canceledIp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public boolean isGotTextMessage() {
        return gotTextMessage;
    }

    public void setGotTextMessage(boolean gotTextMessage) {
        this.gotTextMessage = gotTextMessage;
    }

    public String getHitCoordination() {
        return hitCoordination;
    }

    public boolean isGotIsHitCoordinationMessage() {
        return gotIsHitCoordinationMessage;
    }

    public void setGotIsHitCoordinationMessage(boolean gotIsHitCoordinationMessage) {
        this.gotIsHitCoordinationMessage = gotIsHitCoordinationMessage;
    }

    public boolean isGotCoordination() {
        return gotCoordination;
    }

    public void setGotCoordination(boolean gotCoordination) {
        this.gotCoordination = gotCoordination;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isGameStart() {
        return gameStart;
    }

    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }

    public boolean isYourTurn() {
        return isYourTurn;
    }

    public void setYourTurn(boolean yourTurn) {
        isYourTurn = yourTurn;
    }
}
