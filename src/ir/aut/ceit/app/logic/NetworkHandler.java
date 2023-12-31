package ir.aut.ceit.app.logic;

import sun.misc.Queue;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Arrays;

public class NetworkHandler extends Thread {
    private TcpChannel mTcpChannel;
    private Queue<byte[]> mSendQueue;
    private Queue<byte[]> mReceivedQueue;
    private ReceivedMessageConsumer mConsumerThread;
    private boolean stopIsCalled = false;
    private INetworkHandlerCallback iNetworkHandlerCallback;
    private boolean messageSent;
    private boolean isFirstMessage;


    public NetworkHandler(SocketAddress socketAddress, INetworkHandlerCallback iNetworkHandlerCallback) {
        try {
            mTcpChannel = new TcpChannel(socketAddress, 1000000000);
            mSendQueue = new Queue<byte[]>();
            mReceivedQueue = new Queue<byte[]>();
            mConsumerThread = new ReceivedMessageConsumer();
            this.iNetworkHandlerCallback = iNetworkHandlerCallback;
            // TODO: 6/10/2017 time out cheghad bashe?iNetworkHandlerCallback doroste? field ezafe kardam

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public NetworkHandler(Socket socket, INetworkHandlerCallback iNetworkHandlerCallback) {
        try {
            mTcpChannel = new TcpChannel(socket, 1000000000);
            // TODO: 6/10/2017 time out cheghad bashe?iNetworkHandlerCallback doroste? field ezafe kardam
            mSendQueue = new Queue<byte[]>();
            mReceivedQueue = new Queue<byte[]>();
            mConsumerThread = new ReceivedMessageConsumer();
            this.iNetworkHandlerCallback = iNetworkHandlerCallback;


        } catch (SocketException e) {
            e.printStackTrace();
        }

    }


    /**
     * Add serialized bytes of message to the sendQueue.
     */
    public void sendMessage(BaseMessage baseMessage) {
        mSendQueue.enqueue(baseMessage.getSerialized());
        messageSent = false;

    }

    /**
     * While channel is connected and stop is not called:
     * * if sendQueue is not empty, then poll a message and send it
     * * else if readChannel() is returning bytes, then add it to receivedQueue.
     */
    @Override
    public void run() {

        while (mTcpChannel.isConnected() && !stopIsCalled) {

            byte[] message = readChannel();
            if (!mSendQueue.isEmpty()) {
                try {
                    byte[] sendMessage = mSendQueue.dequeue();
                    mTcpChannel.write(sendMessage);
                    messageSent = true;
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }

            } else if (message != null) {
                mReceivedQueue.enqueue(message);
                mConsumerThread.setSenderIp(this.getmTcpChannel().getIp());
                if (!isFirstMessage) {
                    mConsumerThread.start();
                    isFirstMessage = true;
                }
            }
        }
    }

    /**
     * Kill the thread and close the channel.
     */

    public void stopSelf() throws InterruptedException

    {
        currentThread().sleep(999999999);
        mConsumerThread.sleep(999999999);
        try {
            mTcpChannel.closeChannel();
        } catch (IOException e) {
            System.out.println("sorry not able to close the channel");
        }
        stopIsCalled = true;
    }

    /**
     * Try to read some bytes from the channel.
     */
    private byte[] readChannel() {

        try {
            mTcpChannel.getStream();
            byte[] length = mTcpChannel.read(4);
            if (length == null) {
                return null;
            }
            int x = java.nio.ByteBuffer.wrap(length).getInt();
            if (x == 0) {
                return null;
            }
            byte[] message = mTcpChannel.read(x - 4);
            byte[] completeMessage = new byte[x];
            int i = 0;
            for (byte b : length) {
                completeMessage[i] = b;
                i++;
            }
            i = 0;
            for (byte b : message) {
                completeMessage[i + 4] = b;
                i++;
            }

            return completeMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TcpChannel getmTcpChannel() {
        return mTcpChannel;
    }

    public ReceivedMessageConsumer getmConsumerThread() {
        return mConsumerThread;
    }

    public boolean isMessageSent() {
        return messageSent;
    }

    private class ReceivedMessageConsumer extends Thread {
        /**
         * While channel is connected and stop is not called:
         * * if there exists message in receivedQueue, then create a message object
         * *  from appropriate class based on message type byte and pass it through onMessageReceived
         * * else if receivedQueue is empty, then sleep 100 ms.
         */
        String senderIp;

        @Override
        public void run() {

            while (mTcpChannel.isConnected() && !stopIsCalled) {
                if (!mReceivedQueue.isEmpty()) {

                    try {
                        byte[] message = mReceivedQueue.dequeue();
                        byte type = message[5];
                        switch (type) {
                            case MessageTypes.TEXT_MESSAGE:
                                iNetworkHandlerCallback.onMessageReceived(new TextMessage(message), senderIp);
                                break;
                            case MessageTypes.PLAYER_COORDINATION:
                                iNetworkHandlerCallback.onMessageReceived(new CoordinationPlayMessage(message), senderIp);
                                break;
                            case MessageTypes.NAME_MESSAGE:
                                iNetworkHandlerCallback.onMessageReceived(new NameMessage(message), senderIp);
                                break;
                            case MessageTypes.ACCEPT_MESSAGE:
                                iNetworkHandlerCallback.onMessageReceived(new AcceptMessage(message), senderIp);
                                break;
                            case MessageTypes.REJECT_MESSAGE:
                                iNetworkHandlerCallback.onMessageReceived(new RejectMessage(message), senderIp);
                                break;
                            case MessageTypes.CANCEL_MESSAGE:
                                iNetworkHandlerCallback.onMessageReceived(new CancelMessage(message), senderIp);
                                break;
                            case MessageTypes.ISHITCOORDINATION_MESSAGE:
                                iNetworkHandlerCallback.onMessageReceived(new IsHitCoordinationMessage(message), senderIp);
                                break;
                            case MessageTypes.LEAVE_MESSAGE :
                                iNetworkHandlerCallback.onMessageReceived(new LeaveMessage(message), senderIp);
                                break;
                            case MessageTypes.START_MESSAGE :
                                iNetworkHandlerCallback.onMessageReceived(new StartMessage(message), senderIp);
                                break;
                            case MessageTypes.FIRSTTURN_MESSAGE :
                                iNetworkHandlerCallback.onMessageReceived(new FirstTurnSpecifier(message), senderIp);
                                break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                       /* if (type == MessageTypes.TEXT_MESSAGE) {
                            iNetworkHandlerCallback.onMessageReceived(new TextMessage(message));
                        } else if (type == MessageTypes.PLAYER_COORDINATION) {
                            iNetworkHandlerCallback.onMessageReceived(new CoordinationPlayMessage(message));
                        } else if (type == MessageTypes.NAME_MESSAGE) {
                            iNetworkHandlerCallback.onMessageReceived(new NameMessage(message));
                        } else if (type == MessageTypes.ACCEPT_MESSAGE) {
                            iNetworkHandlerCallback.onMessageReceived(new AcceptMessage(message));
                        } else if (type == MessageTypes.REJECT_MESSAGE) {
                            iNetworkHandlerCallback.onMessageReceived(new RejectMessage(message));
                        } else if (type == MessageTypes.CANCEL_MESSAGE) {
                            iNetworkHandlerCallback.onMessageReceived(new CancelMessage(message));
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/

                else if (mReceivedQueue.isEmpty()) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private void setSenderIp(String ip) {
            senderIp = ip;
        }
    }


    public interface INetworkHandlerCallback {
        void onMessageReceived(BaseMessage baseMessage, String ip);

        void onSocketClosed(String closedIp) throws InterruptedException;
    }
}

