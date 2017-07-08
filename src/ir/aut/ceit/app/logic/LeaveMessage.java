package ir.aut.ceit.app.logic;

import java.nio.ByteBuffer;

public class LeaveMessage extends BooleanTypeMessage{

    private boolean mLeave;

    public LeaveMessage(boolean leave){
        super(leave);
        mLeave = super.ismValue();
    }

    public LeaveMessage(byte[] serialized) {
        super(serialized);
    }

    @Override
    protected void serialize() {
        int messageLength = 4 + 1 + 1 + 4 + 1;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERSION);
        byteBuffer.put(MessageTypes.LEAVE_MESSAGE);
        byteBuffer.putInt(1);
        if (mLeave) {
            byteBuffer.put((byte) 1);

        } else {
            byteBuffer.put((byte) 0);
        }
        mSerialized = byteBuffer.array();
    }


    @Override
    public byte getMessageType() {
        return MessageTypes.LEAVE_MESSAGE;
    }

    public boolean ismLeave() {
        return super.ismValue();
    }
}


