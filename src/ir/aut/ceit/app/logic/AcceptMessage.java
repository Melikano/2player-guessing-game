package ir.aut.ceit.app.logic;

import java.nio.ByteBuffer;

public class AcceptMessage extends BaseMessage {

    private boolean mAccept;

    public AcceptMessage(boolean accept) {
        mAccept = accept;
        serialize();

    }

    public AcceptMessage(byte[] serialized) {
        mSerialized = serialized;
        deserialize();
    }

    @Override
    protected void serialize() {
        int messageLength = 4 + 1 + 1 + 4 + 1;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERSION);
        byteBuffer.put(MessageTypes.ACCEPT_MESSAGE);
        byteBuffer.putInt(1);
        if (mAccept) {
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
            mAccept = true;
        } else if (accept == 0) {
            mAccept = false;
        }
    }

    @Override
    public byte getMessageType() {
        return MessageTypes.ACCEPT_MESSAGE;
    }

    public boolean ismAccept() {
        return mAccept;
    }
}
