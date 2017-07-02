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
    private boolean started;
    private boolean gotName;
    private boolean hostAccept;
    private String guestName;

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
            started = true;

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

    /*public void sendCoordinationMessage(String to, String coordinationX1, String coordinationY1) {
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
    }*/

    public void sendName(String name) {
        NameMessage nameMessage = new NameMessage(name);
        nameMessage.serialize();
        mNetworkHandlerList.get(0).sendMessage(nameMessage);
    }

    public void sendAcceptMessage(String to, boolean isAccept) {
        AcceptMessage acceptMessage = new AcceptMessage(isAccept);
        for (NetworkHandler networkHandler : mNetworkHandlerList) {
            if (networkHandler.getmTcpChannel().getIp().equals(to)) {
                networkHandler.sendMessage(acceptMessage);
                break;
            }
        }
    }

    private void consumeCoordinationMessage(CoordinationPlayMessage message) {
        message.deserialize();
        message.getCoordinationX();
        message.getCoordinationY();
    }


    private void consumeTextMessage(TextMessage message) {
        message.deserialize();
        System.out.println(message.getText());
    }

    private void consumeNameMessage(NameMessage message) {
        message.deserialize();
        guestName = message.getmName();
        gotName = true;
    }

    private void consumeAcceptMessage(AcceptMessage message) {
        message.deserialize();
        hostAccept = message.ismAccept();

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
        started = true;
    }

    @Override
    public void onMessageReceived(BaseMessage baseMessage) {
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

        }
    }

    @Override
    public void onSocketClosed(String closedIp) {
        for(NetworkHandler networkHandler : mNetworkHandlerList){
            if (networkHandler.getmTcpChannel().getIp().substring(1, 10).equals(closedIp)) {
                networkHandler.stopSelf();
                break;
            }
        }
    }

    public boolean isStarted() {
        return started;
    }

    public String getName() {
        return guestName;
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
}
