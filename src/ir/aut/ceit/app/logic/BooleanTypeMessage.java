package ir.aut.ceit.app.logic;

import java.nio.ByteBuffer;

public class BooleanTypeMessage extends BaseMessage {

    private boolean mValue;

    public BooleanTypeMessage(boolean accept) {
        mValue = accept;
    }

    public BooleanTypeMessage(byte[] serialized) {
        mSerialized = serialized;
        deserialize();
    }

    @Override
    protected void serialize() {
        int messageLength = 4 + 1 + 1 + 4 + 1;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERSION);
        byteBuffer.put(MessageTypes.BOOLEAN_TYPE);
        byteBuffer.putInt(1);
        if (mValue) {
            byteBuffer.put((byte) 1);

        } else {
            byteBuffer.put((byte) 0);
        }
        mSerialized = byteBuffer.array();
    }

    @Override
    protected void deserialize() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(mSerialized);
        int messageLength = byteBuffer.getInt();
        byte protocolVersion = byteBuffer.get();
        byte messageType = byteBuffer.get();
        int mAcceptLength = byteBuffer.getInt();
        byte accept = byteBuffer.get();
        if (accept == 1) {
            mValue = true;
        } else if (accept == 0) {
            mValue = false;
        }
    }

    @Override
    public byte getMessageType() {
        return MessageTypes.BOOLEAN_TYPE;
    }

    public boolean ismValue() {
        return mValue;
    }
}