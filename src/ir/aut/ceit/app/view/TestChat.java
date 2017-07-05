package ir.aut.ceit.app.view;

import javax.swing.*;

public class TestChat {

    public static void main(String[] args) {
        Chat c = new Chat("melika");
        c.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        c.setVisible(true);
        c.displayMessage("zahra", "hi back");
    }
}
