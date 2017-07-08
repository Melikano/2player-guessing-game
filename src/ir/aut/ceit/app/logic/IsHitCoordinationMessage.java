package ir.aut.ceit.app.logic;

import java.nio.ByteBuffer;

public class IsHitCoordinationMessage extends TextMessage {

    private String mHitCoordination;

    public IsHitCoordinationMessage(String hitCoordination) {
        super(hitCoordination);
        mHitCoordination = super.getText();
    }

    public IsHitCoordinationMessage(byte[] serialized) {
        super(serialized);
    }

    @Override
    protected void serialize() {
        int mNameLength = mHitCoordination.getBytes().length;
        int messageLength = 4 + 1 + 1 + 4 + mNameLength;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERSION);
        byteBuffer.put(MessageTypes.ISHITCOORDINATION_MESSAGE);
        byteBuffer.putInt(mNameLength);
        byteBuffer.put(mHitCoordination.getBytes());
        mSerialized = byteBuffer.array();
    }

    public byte getMessageType() {
        return MessageTypes.ISHITCOORDINATION_MESSAGE;
    }

    public String getmHitCoordination() {
        return super.getText();
    }
}


