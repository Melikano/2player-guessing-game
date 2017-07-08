package ir.aut.ceit.app.logic;

import java.nio.ByteBuffer;

public class NameMessage extends TextMessage{
    private String mName;

    public NameMessage(String name) {
        super(name);
        mName = super.getText();
    }

    public NameMessage(byte[] serialized) {
        super(serialized);
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

    public byte getMessageType() {
        return MessageTypes.NAME_MESSAGE;
    }

    public String getmName() {
        return super.getText();
    }
}
