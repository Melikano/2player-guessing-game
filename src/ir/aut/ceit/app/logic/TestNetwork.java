package ir.aut.ceit.app.logic;


public class TestNetwork {

    public static void main(String[] args) {

        MessageManager messageManager = new MessageManager("127.0.0.1", 5000);

        while (!messageManager.isStarted()) {
            System.out.println("not yet");
        }

        messageManager.sendTextMessage("127.0.0.1", "hi dear server");

    }

}
