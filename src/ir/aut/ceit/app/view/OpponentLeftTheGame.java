package ir.aut.ceit.app.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ASUS UX303UB on 7/8/2017.
 */
public class OpponentLeftTheGame extends JFrame {

    public OpponentLeftTheGame() {
        super("Game ended...");
        setLayout(new GridLayout(2, 1));
        setSize(400, 200);
        setLocation(700, 400);
        JLabel text = new JLabel("Your opponent left the game");
        add(text);
    }
}
