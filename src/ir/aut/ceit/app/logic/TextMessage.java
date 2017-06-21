package ir.aut.ceit.app.logic;

import java.nio.ByteBuffer;

public class TextMessage extends BaseMessage {
    private String mText;

    public TextMessage(String text) {
        mText = text;
        serialize();
    }

    public TextMessage(byte[] serialized) {
        mSerialized = serialized;
        deserialize();
    }

    @Override
    protected void serialize() {
        int mTextLength = mText.getBytes().length;
        int messageLength = 4 + 1 + 1 + 4 + mTextLength;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERSION);
        byteBuffer.put(MessageTypes.TEXT_MESSAGE);
        byteBuffer.putInt(mTextLength);
        byteBuffer.put(mText.getBytes());
        mSerialized = byteBuffer.array();
    }

    @Override
    protected void deserialize() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(mSerialized);
        int messageLength = byteBuffer.getInt();
        byte protocolVersion = byteBuffer.get();
        byte messageType = byteBuffer.get();
        int mTextLength = byteBuffer.getInt();
        byte[] mTextBytes = new byte[mTextLength];
        byteBuffer.get(mTextBytes);
        mText = new String(mTextBytes);
    }


    @Override
    public byte getMessageType() {
        return MessageTypes.TEXT_MESSAGE;
    }

    public String getText() {
        return mText;
    }

}
