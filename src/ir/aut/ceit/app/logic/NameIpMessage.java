package ir.aut.ceit.app.logic;


import java.nio.ByteBuffer;

public class NameIpMessage extends BaseMessage{

    private String mName;
    private String mIp;

    public NameIpMessage(String name, String ip) {
        mName = name;
        mIp = ip;
        serialize();
    }

    public NameIpMessage(byte[] serialized) {
        mSerialized = serialized;
        deserialize();
    }


    @Override
    protected void serialize() {
        int nameLength = mName.getBytes().length;
        int ipLength = mIp.getBytes().length;
        int messageLength = 4 + 1 + 1 + 4 + nameLength + 4 + ipLength;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERSION);
        byteBuffer.put(MessageTypes.NAMEIP_MESSAGE);
        byteBuffer.putInt(nameLength);
        byteBuffer.put(mName.getBytes());
        byteBuffer.putInt(ipLength );
        byteBuffer.put(mIp.getBytes());
        mSerialized = byteBuffer.array();
    }

    @Override
    protected void deserialize() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(mSerialized);
        int messageLength = byteBuffer.getInt();
        byte protocolVersion = byteBuffer.get();
        byte messageType = byteBuffer.get();
        int nameLength = byteBuffer.getInt();
        byte[] nameBytes = new byte[nameLength];
        byteBuffer.get(nameBytes);
        mName = new String(nameBytes);
        int ipLength = byteBuffer.getInt();
        byte[] ipBytes = new byte[ipLength];
        byteBuffer.get(ipBytes);
        mIp = new String(ipBytes);
    }

    @Override
    public byte getMessageType() {
        return MessageTypes.NAMEIP_MESSAGE;
    }

    public String getmName() {
        return mName;
    }

    public String getmIp() {
        return mIp;
    }
}
