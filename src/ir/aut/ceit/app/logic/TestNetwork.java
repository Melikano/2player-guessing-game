package ir.aut.ceit.app.logic;


public class TestNetwork {

    public static void main(String[] args) {

        MessageManager messageManager = new MessageManager(5000);
        messageManager.sendTextMessage("127.0.0.1", "hiiii");

    }

}
