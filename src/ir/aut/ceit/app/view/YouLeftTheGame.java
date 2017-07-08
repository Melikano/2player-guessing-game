package ir.aut.ceit.app.view;

import javax.swing.*;
import java.awt.*;

public class YouLeftTheGame extends JFrame {

    public YouLeftTheGame() {
        super("Game ended...");
        setLayout(new GridLayout(2, 1));
        setSize(400, 200);
        setLocation(700, 400);
        JLabel text = new JLabel("You left the game");
        add(text);
    }

}
