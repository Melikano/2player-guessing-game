package ir.aut.ceit.app.logic;

import java.nio.ByteBuffer;

public class CancelMessage extends BooleanTypeMessage {

    private boolean mCancel;

    public CancelMessage(boolean accept) {
        super(accept);
        mCancel = super.ismValue();
    }

    public CancelMessage(byte[] serialized) {
        super(serialized);
    }

    @Override
    protected void serialize() {
        int messageLength = 4 + 1 + 1 + 4 + 1;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERSION);
        byteBuffer.put(MessageTypes.CANCEL_MESSAGE);
        byteBuffer.putInt(1);
        if (mCancel) {
            byteBuffer.put((byte) 1);

        } else {
            byteBuffer.put((byte) 0);
        }
        mSerialized = byteBuffer.array();
    }


    @Override
    public byte getMessageType() {
        return MessageTypes.ACCEPT_MESSAGE;
    }

    public boolean ismCancel() {
        return super.ismValue();
    }
}
