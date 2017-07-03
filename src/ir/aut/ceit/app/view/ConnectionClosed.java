package ir.aut.ceit.app.view;

import javax.swing.*;
import java.awt.*;

public class ConnectionClosed extends JFrame {

    public ConnectionClosed() {
        super("Connection closed...");
        setLayout(new GridLayout(3, 1));
        setSize(400, 200);
        setLocation(700, 400);
        JLabel text = new JLabel("your connection was closed, it may be because of :");
        JLabel reject = new JLabel(". server rejected your connection...");
        JLabel cancel = new JLabel(". you canceled your connection...");
        add(text);
        add(reject);
        add(cancel);
    }

}
