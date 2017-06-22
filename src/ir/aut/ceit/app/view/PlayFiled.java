package ir.aut.ceit.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by shsh9692 on 6/4/2017.
 */
public class PlayFiled extends JPanel {
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d =(Graphics2D)g;
        BufferedImage bufferedImage = new BufferedImage(20,20,BufferedImage.TYPE_INT_RGB);
        Graphics2D gg=bufferedImage.createGraphics();
        gg.setColor(Color.blue);
        gg.drawRect(0,0,20,20);
        g2d.setPaint(new TexturePaint(bufferedImage,new Rectangle(20,20)));
        g2d.fill(new Rectangle(0,0,200,200));
        g2d.setColor(Color.RED);
    }
    public void paintComponent2 (int h,Graphics2D g) {
        super.paintComponent(g);
        Graphics2D g2d =(Graphics2D)g;
        g2d.fillRect(0,0,20,40);
        g2d.fillRect(60,40,20,20);
        g2d.fillRect(180,80,20,60);

    }
}

