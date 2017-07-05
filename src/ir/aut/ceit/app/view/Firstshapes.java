package ir.aut.ceit.app.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by shsh9692 on 6/12/2017.
 */
public class Firstshapes extends JPanel {
     public Firstshapes(){

     }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        this.setBackground(Color.RED);
       g.setColor(Color.red);
        g.fillRect(0, 0, 40, 40);
    }

}
