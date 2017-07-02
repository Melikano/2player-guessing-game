package ir.aut.ceit.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RejectedByServer extends JFrame {

    private boolean ok;

    public RejectedByServer() {
        super("Server Rejected...");
        setLayout(new GridLayout(2, 1));
        setSize(400, 200);
        setLocation(700, 400);
        createWindow();
    }

    public void createWindow() {
        JLabel text = new JLabel("The server rejected your connection, click OK to exit");

        JPanel cancelPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton cancel = new JButton("OK");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ok = true;
                dispose();
            }
        });
        add(text);
        cancelPanel.add(cancel);
        add(cancelPanel);
    }


    public boolean isOk() {
        return ok;
    }
}
