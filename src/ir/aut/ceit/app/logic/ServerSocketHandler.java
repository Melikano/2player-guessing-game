package ir.aut.ceit.app.logic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketHandler extends Thread {

    private ServerSocket mServerSocket;
    private NetworkHandler.INetworkHandlerCallback iNetworkHandlerCallback;
    private IServerSocketHandlerCallback iServerSocketHandlerCallback;
    private boolean isStopped;
    private boolean receivedConnection;

    public ServerSocketHandler(int port, NetworkHandler.INetworkHandlerCallback iNetworkHandlerCallback, IServerSocketHandlerCallback iServerSocketHandlerCallback) {

        try {
            this.mServerSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.iNetworkHandlerCallback = iNetworkHandlerCallback;
        this.iServerSocketHandlerCallback = iServerSocketHandlerCallback;
    }

    /**
     * While server socket is connected and stop is not called:
     * if a connection receives, then create a network handler and pass it through onNewConnectionReceived
     * else sleep for 100 ms.
     */
    @Override
    public void run() {

        while (!mServerSocket.isClosed() && !isStopped) {
            Socket socket = receiveConnection();

            if (receivedConnection) {
                NetworkHandler networkHandler = new NetworkHandler(socket, iNetworkHandlerCallback);
                iServerSocketHandlerCallback.onNewConnectionReceived(networkHandler);
            } else {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Socket receiveConnection() {
        Socket socket = null;
        try {
            socket = mServerSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        receivedConnection = true;
        return socket;
    }


    /**
     * Kill the thread and close the server socket.
     */
    public void stopSelf() {

        try {
            mServerSocket.close();
        } catch (IOException e) {
            System.out.println("unable to close the server socket");
        }
        currentThread().stop();
        isStopped = true;

    }

    public interface IServerSocketHandlerCallback {
        void onNewConnectionReceived(NetworkHandler networkHandler);
    }
}
