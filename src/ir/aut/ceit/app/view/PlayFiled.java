package ir.aut.ceit.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by shsh9692 on 6/4/2017.
 */
public class PlayFiled extends JPanel {
    public ArrayList<FirstCordination> placeshit = new ArrayList<FirstCordination>();
    public ArrayList<FirstCordination> placesmiss = new ArrayList<FirstCordination>();
    int hostOrGeust;
    Rectangle rectTwo1;
    Rectangle rectTwo2;
    Rectangle rectTwo3;
    Rectangle rectOne1;
    Rectangle rectOne2;
    Rectangle rectOne3;
    Rectangle rectOne4;
    Rectangle rectthree1;
    Rectangle rectthree2;

    public PlayFiled(int type) {
        setBackground(Color.white);
        setSize(500, 500);
        hostOrGeust = type;
        rectOne1 = new Rectangle(0, 0, 40, 40);
        rectOne2 = new Rectangle(360, 360, 40, 40);
        rectOne3 = new Rectangle(200, 40, 40, 40);
        rectOne4 = new Rectangle(360, 80, 40, 40);
        rectTwo1 = new Rectangle(80, 0, 40, 80);
        rectTwo2 = new Rectangle(0, 200, 40, 80);
        rectTwo3 = new Rectangle(0, 320, 40, 80);
        rectthree1 = new Rectangle(40, 80, 40, 120);
        rectthree2 = new Rectangle(120, 120, 40, 120);
    }

    public void paint2() {

        repaint();
    }

    @Override

    public void paint(Graphics g) {
        super.paint(g);
        draw(g);
    }


    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage bufferedImage = new BufferedImage(40, 40, BufferedImage.TYPE_INT_RGB);
        Graphics2D gg = bufferedImage.createGraphics();
        gg.setColor(new Color(0.32941177f, 1.0f, 0.27450982f));
        gg.drawRect(0, 0, 40, 40);
        g2d.setPaint(new TexturePaint(bufferedImage, new Rectangle(40, 40)));
        g2d.fill(new Rectangle(0, 0, 400, 400));

        g.setColor(Color.GRAY);
        g.fillRect(0, 470, 10, 10);
        g.fillRect(15, 470, 10, 10);
        g.fillRect(30, 470, 10, 10);
        g.fillRect(45, 470, 10, 10);
        g.fillRect(0, 450, 20, 10);
        g.fillRect(25, 450, 20, 10);
        g.fillRect(50, 450, 20, 10);
        g.fillRect(0, 430, 30, 10);
        g.fillRect(35, 430, 30, 10);
        g.fillRect(0, 410, 40, 10);
         if (hostOrGeust == 0) {
        g.setColor(new Color(1.0f, 0.101960786f, 0.16078432f));
        g.fillRect((int) rectTwo1.getX(), (int) rectTwo1.getY(), (int) rectTwo1.getWidth(), (int) rectTwo1.getHeight());
        g.fillRect((int) rectTwo2.getX(), (int) rectTwo2.getY(), (int) rectTwo2.getWidth(), (int) rectTwo2.getHeight());
        g.fillRect((int) rectTwo3.getX(), (int) rectTwo3.getY(), (int) rectTwo3.getWidth(), (int) rectTwo3.getHeight());
        g.fillRect((int) rectOne1.getX(), (int) rectOne1.getY(), (int) rectOne1.getWidth(), (int) rectOne1.getHeight());
        g.fillRect((int) rectOne2.getX(), (int) rectOne2.getY(), (int) rectOne2.getWidth(), (int) rectOne2.getHeight());
        g.fillRect((int) rectOne3.getX(), (int) rectOne3.getY(), (int) rectOne3.getWidth(), (int) rectOne3.getHeight());
        g.fillRect((int) rectOne4.getX(), (int) rectOne4.getY(), (int) rectOne4.getWidth(), (int) rectOne4.getHeight());
        g.fillRect((int) rectthree1.getX(), (int) rectthree1.getY(), (int) rectthree1.getWidth(), (int) rectthree1.getHeight());
        g.fillRect((int) rectthree2.getX(), (int) rectthree2.getY(), (int) rectthree2.getWidth(), (int) rectthree2.getHeight());

        }

       for (FirstCordination place : placeshit) {

         Color c = new Color(1.0F, 0.6901961f, 0.59607846f);
          g.setColor(c);
          g.fillRect(place.getX(), place.getY(), 40, 40);
        }
        for (FirstCordination place : placesmiss) {

            Color c = new Color(0.7137255f, 0.69803923f, 1.0f);
            g.setColor(c);
            g.fillRect(place.getX(), place.getY(), 40, 40);
        }
    }

    public void setRect(Rectangle recty, int x, int y) {
        recty.setLocation(x, y);
        repaint();
    }

    public boolean hittedFromenemy(double cordinationx,double cordinationy) {
        int x=(int)cordinationx;
        int y=(int)cordinationy;
        int p = createImage(this).getRGB(x, y);
        int a = (p >> 24) & 0xff;
        int r = (p >> 16) & 0xff;
        int g = (p >> 8) & 0xff;
        int b = p & 0xff;
        x = x / 40;
        x=x*40;
        y = y / 40;
        y=y*40;
        if (r == 255) {
            System.out.println("hit");
            FirstCordination f = new FirstCordination(x, y);
            placeshit.add(f);
            repaint();
            return true;
        }
        if (r == 0 && g == 0 && b == 0) {
            System.out.println("miss");
            FirstCordination f = new FirstCordination(x, y);
            placesmiss.add(f);
            repaint();
            return false;
        }
        return false;
    }
    public void hittedFromYou(int cordinationx,int cordinationy,boolean hit) {

        if (hit) {
            FirstCordination f = new FirstCordination(cordinationx, cordinationy);
            placeshit.add(f);
            repaint();

        }
        if (!hit) {
            FirstCordination f = new FirstCordination(cordinationx, cordinationy);
            placesmiss.add(f);
            repaint();

        }

    }

    public BufferedImage createImage(JPanel panel) {

        int w = panel.getWidth();
        int h = panel.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.paint(g);
        return bi;

    }



}

