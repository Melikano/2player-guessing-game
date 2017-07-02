package ir.aut.ceit.app.logic;

import java.nio.ByteBuffer;

public class AcceptMessage extends BooleanTypeMessage {

    private boolean mAccept;

    public AcceptMessage(boolean accept) {
        super(accept);
        mAccept = super.ismValue();
    }

    public AcceptMessage(byte[] serialized) {
        super(serialized);
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
    public byte getMessageType() {
        return MessageTypes.ACCEPT_MESSAGE;
    }

    public boolean ismAccept() {
        return super.ismValue();
    }
}
