package ir.aut.ceit.app.logic;

import java.nio.ByteBuffer;

public class FirstTurnSpecifier extends BooleanTypeMessage {

    private boolean mIsYourTurn;

    public FirstTurnSpecifier(boolean isYourTurn) {
        super(isYourTurn);
        mIsYourTurn = super.ismValue();
    }

    public FirstTurnSpecifier(byte[] serialized) {
        super(serialized);
    }

    @Override
    protected void serialize() {
        int messageLength = 4 + 1 + 1 + 4 + 1;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERSION);
        byteBuffer.put(MessageTypes.FIRSTTURN_MESSAGE);
        byteBuffer.putInt(1);
        if (mIsYourTurn) {
            byteBuffer.put((byte) 1);

        } else {
            byteBuffer.put((byte) 0);
        }
        mSerialized = byteBuffer.array();
    }


    @Override
    public byte getMessageType() {
        return MessageTypes.FIRSTTURN_MESSAGE;
    }

    public boolean ismIsYourTurn() {
        return super.ismValue();
    }
}

