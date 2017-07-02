package ir.aut.ceit.app.view;

import javax.swing.*;
import java.awt.*;

public class RejectedByServer extends JFrame {

    public RejectedByServer() {
        super("Server Rejected...");
        setLayout(new GridLayout(2, 1));
        setSize(400, 200);
        setLocation(700, 400);
        JLabel text = new JLabel("The server rejected your connection, click OK to exit");
        add(text);
    }

}
