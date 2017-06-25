package ir.aut.ceit.app.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PleaseWait extends JFrame {

    private boolean connectionCanceled;

    public PleaseWait(){
        super("Please waite...");
        setLayout(new GridLayout(2,1));
        setSize(400, 200);
        setLocation(700,400);
        createWindow();
    }

    public void createWindow(){
        JLabel text = new JLabel("   Waiting for the host to join...");

        JPanel cancelPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton cancel =  new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectionCanceled = true;
                dispose();
            }
        });
        add(text);
        cancelPanel.add(cancel);
        add(cancelPanel);
    }


}
