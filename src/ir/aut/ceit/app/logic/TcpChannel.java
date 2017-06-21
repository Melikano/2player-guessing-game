package ir.aut.ceit.app.logic;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

class TcpChannel {
    private Socket mSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;

    TcpChannel(SocketAddress socketAddress, int timeout) throws IOException {
        mSocket = new Socket();
        mSocket.connect(socketAddress);
        mSocket.setSoTimeout(timeout);

    }

    TcpChannel(Socket socket, int timeout) throws SocketException {
        mSocket = socket;
        mSocket.setSoTimeout(timeout);
    }

    void getStream() throws IOException {
        try {
            mInputStream = mSocket.getInputStream();
        }catch(IOException e){
            System.out.println("unable to get input stream");
        }
    }

    /**
     * Try to read specific count from input stream.
     */
    byte[] read(final int count) throws IOException {
        byte[] data = new byte[count];
        try {
            mInputStream.read(data);
        }catch (IOException e){
            System.out.println("unable to read input");
        }
        return data;
    }

    /**
     * Write bytes on output stream.
     */
    void write(byte[] data) throws IOException {
        mOutputStream = mSocket.getOutputStream();
        mOutputStream.write(data);
        mOutputStream.flush();
    }

    /**
     * Check socket’s connectivity.
     */
    boolean isConnected(){
        return mSocket.isConnected();
    }

    /**
     * Try to close socket and input-output streams.
     */
    void closeChannel() throws IOException {

        mSocket.close();
        mOutputStream.close();
        mInputStream.close();
    }

    String getIp (){
        return mSocket.getRemoteSocketAddress().toString();
    }
}
