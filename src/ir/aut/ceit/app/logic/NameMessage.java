package ir.aut.ceit.app.logic;

import java.nio.ByteBuffer;

public class NameMessage extends BaseMessage{
    private String mName;

    public NameMessage(String name) {
        mName = name;
        serialize();
    }

    public NameMessage(byte[] serialized) {
        mSerialized = serialized;
        deserialize();
    }

    @Override
    protected void serialize() {
        int mNameLength = mName.getBytes().length;
        int messageLength = 4 + 1 + 1 + 4 + mNameLength;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERSION);
        byteBuffer.put(MessageTypes.NAME_MESSAGE);
        byteBuffer.putInt(mNameLength);
        byteBuffer.put(mName.getBytes());
        mSerialized = byteBuffer.array();
    }

    protected void deserialize() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(mSerialized);
        byteBuffer.getInt();
        byteBuffer.get();
        byteBuffer.get();
        int mTextLength = byteBuffer.getInt();
        byte[] mTextBytes = new byte[mTextLength];
        byteBuffer.get(mTextBytes);
        mName = new String(mTextBytes);
    }


    public byte getMessageType() {
        return MessageTypes.NAME_MESSAGE;
    }

    public String getmName() {
        return mName;
    }
}
