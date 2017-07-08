package ir.aut.ceit.app.logic;

import java.nio.ByteBuffer;

public class StartMessage extends BooleanTypeMessage {

    private boolean mStart;

    public StartMessage(boolean leave){
        super(leave);
        mStart = super.ismValue();
    }

    public StartMessage(byte[] serialized) {
        super(serialized);
    }

    @Override
    protected void serialize() {
        int messageLength = 4 + 1 + 1 + 4 + 1;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERSION);
        byteBuffer.put(MessageTypes.START_MESSAGE);
        byteBuffer.putInt(1);
        if (mStart) {
            byteBuffer.put((byte) 1);

        } else {
            byteBuffer.put((byte) 0);
        }
        mSerialized = byteBuffer.array();
    }


    @Override
    public byte getMessageType() {
        return MessageTypes.START_MESSAGE;
    }

    public boolean ismStart() {
        return super.ismValue();
    }
}
