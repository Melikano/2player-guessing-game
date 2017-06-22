package ir.aut.ceit.app.logic;


import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MessageManager implements ServerSocketHandler.IServerSocketHandlerCallback, NetworkHandler.INetworkHandlerCallback {

    private ServerSocketHandler mServerSocketHandler;
    private List<NetworkHandler> mNetworkHandlerList = new ArrayList<>();
    private boolean isHost;
    private boolean isGuest;
    private boolean started;

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
        if(isHost) {
            for (NetworkHandler networkHandler : mNetworkHandlerList) {
                if (networkHandler.getmTcpChannel().getIp().equals(to)) {
                    System.out.println(networkHandler.getmTcpChannel().getIp());
                    System.out.println("found");
                    networkHandler.sendMessage(textMessage);
                    break;
                }
            }
        }else if(isGuest){
            mNetworkHandlerList.get(0).sendMessage(textMessage);
        }

    }

    public void sendCoordinationMessage(String to, String coordinationX1, String coordinationy1) {
        CoordinationPlayMessage coordinationPlayMessage = new CoordinationPlayMessage(coordinationX1, coordinationy1);
        for (NetworkHandler networkHandler : mNetworkHandlerList) {
            if (networkHandler.getmTcpChannel().getIp().equals(to)) {
                networkHandler.sendMessage(coordinationPlayMessage);
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

    /**
     * Add network handler to the list.
     */
    @Override
    public void onNewConnectionReceived(NetworkHandler networkHandler) {

        mNetworkHandlerList.add(networkHandler);
        networkHandler.start();
        System.out.println("new connection started");
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
        }
    }

    @Override
    public void onSocketClosed() {
    }

    public boolean isStart() {
        return started;
    }
}