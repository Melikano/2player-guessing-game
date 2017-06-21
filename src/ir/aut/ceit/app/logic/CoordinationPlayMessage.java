package ir.aut.ceit.app.logic;

import java.nio.ByteBuffer;

public class CoordinationPlayMessage extends BaseMessage {

    private String coordinationY;
    private String coordinationX;

    public CoordinationPlayMessage(String coordinationX1, String coordinationy1) {

        coordinationX = coordinationX1;
        coordinationY = coordinationy1;
        serialize();
    }

    public CoordinationPlayMessage(byte[] serialized) {
        mSerialized = serialized;
        deserialize();

    }

    protected void serialize() {
        int coordinationXLength = coordinationX.getBytes().length;
        int coordinationYLength = coordinationY.getBytes().length;
        int messageLength = 4 + 1 + 1 + 4 + coordinationXLength + 4 + coordinationYLength;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERSION);
        byteBuffer.put(MessageTypes.PLAYER_COORDINATION);
        byteBuffer.putInt(coordinationXLength);
        byteBuffer.put(coordinationX.getBytes());
        byteBuffer.putInt(coordinationYLength);
        byteBuffer.put(coordinationY.getBytes());
        mSerialized = byteBuffer.array();

    }

    protected void deserialize() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(mSerialized);
        int messageLength = byteBuffer.getInt();
        byte protocolVersion = byteBuffer.get();
        byte messageType = byteBuffer.get();
        int coordinationXLength = byteBuffer.getInt();
        byte[] coordinationXBytes = new byte[coordinationXLength];
        byteBuffer.get(coordinationXBytes);
        coordinationX = new String(coordinationXBytes);
        int coordinationYLength = byteBuffer.getInt();
        byte[] coordinationYBytes = new byte[coordinationYLength];
        byteBuffer.get(coordinationYBytes);
        coordinationY = new String(coordinationYBytes);

    }


    @Override
    public byte getMessageType() {
        return MessageTypes.PLAYER_COORDINATION;
    }

    public String getCoordinationY() {
        return coordinationY;
    }

    public String getCoordinationX() {
        return coordinationX;
    }

}
