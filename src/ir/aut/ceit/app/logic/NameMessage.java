package ir.aut.ceit.app.logic;

public class NameMessage extends TextMessage{


    public NameMessage(String name) {
        super(name);
    }

    public NameMessage(byte[] serialized) {
        super(serialized);
    }

    @Override
    public byte getMessageType() {
        return MessageTypes.NAME_MESSAGE;
    }

    public String getmName() {
        return super.getText();
    }
}
