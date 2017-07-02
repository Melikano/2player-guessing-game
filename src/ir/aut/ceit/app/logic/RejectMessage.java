package ir.aut.ceit.app.logic;

import java.nio.ByteBuffer;

public class RejectMessage extends BooleanTypeMessage {

    private boolean mReject;

    public RejectMessage(boolean accept) {
        super(accept);
        mReject = super.ismValue();
    }

    public RejectMessage(byte[] serialized) {
        super(serialized);
    }

    @Override
    protected void serialize() {
        int messageLength = 4 + 1 + 1 + 4 + 1;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERSION);
        byteBuffer.put(MessageTypes.REJECT_MESSAGE);
        byteBuffer.putInt(1);
        if (mReject) {
            byteBuffer.put((byte) 1);

        } else {
            byteBuffer.put((byte) 0);
        }
        mSerialized = byteBuffer.array();
    }


    @Override
    public byte getMessageType() {
        return MessageTypes.REJECT_MESSAGE;
    }

    public boolean ismAccept() {
        return super.ismValue();
    }
}

