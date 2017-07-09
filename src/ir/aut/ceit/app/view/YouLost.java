package ir.aut.ceit.app.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ASUS UX303UB on 7/10/2017.
 */
public class YouLost extends JFrame{

    public YouLost() {
        super("You win...");
        setLayout(new GridLayout(2, 1));
        setSize(400, 200);
        setLocation(700, 400);
        JLabel text = new JLabel("You Lost the game");
        add(text);
    }
}
