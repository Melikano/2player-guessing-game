package ir.aut.ceit.app.view;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by shsh9692 on 6/12/2017.
 */
class GraphicDragController extends MouseInputAdapter {
    private PlayFiled component;
    Point offset = new Point();
    private boolean dragging = false;
    private Rectangle rect;
    private int x1, y1;
    private boolean changable;
    private ArrayList<Rectangle> rects;


    public GraphicDragController(PlayFiled gdad, Rectangle rect, boolean changable,   ArrayList<Rectangle> rects1)
     {
        component = gdad;
        component.addMouseListener(this);
        component.addMouseMotionListener(this);
        this.changable = changable;
        this.rect = rect;
       rects=rects1;

        x1 = (int) rect.getX();
        y1 = (int) rect.getY();
    }

    public void mousePressed(MouseEvent e) {
        if (changable) {
            Point p = e.getPoint();
            if (rect.contains(p)) {
                offset.x = p.x - rect.x;
                offset.y = p.y - rect.y;
                dragging = true;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (changable) dragging = false;
    }

    public void mouseDragged(MouseEvent e) {
        if (changable) {
            if (dragging) {
                int x = e.getX() - offset.x;
                int y = e.getY() - offset.y;
                int y2 = y1;
                int x2 = x1;
                if (40 * (x / 40) < 0 || 40 * (y / 40) < 0 || 400 < 40 * (x / 40) || 400 < 40 * (y / 40) || 400 < 40 * (y / 40) + rect.getHeight() || 400 < 40 * (x / 40) + rect.getWidth()) {
                    component.setRect(rect, (int) x1, (int) y1);
                } else {


                    component.setRect(rect, 40 * (x / 40), 40 * (y / 40));

                    x1 = 40 * (x / 40);
                    y1 = 40 * (y / 40);
                    if (notNearRed(x1, y1)) {
                        System.out.println("sotty you can not put in here");
                        component.setRect(rect, x2, y2);
                        System.out.println(y1);
                        System.out.println(y2);
                        y1 = y2;
                        x1 = x2;
                    } else {
                        y2 = y1;
                        x2 = x1;
                    }

                }
            }
        }
    }

    private boolean notNearRed(int x, int y) {
        boolean red;
        int tedadDarArz = (int) rect.getWidth() / 41;
        int tedadDarErtefa = (int) rect.getHeight() / 41;
        if (tedadDarArz <= 1) {
            for (int i = -1; i <= tedadDarErtefa; i++) {
                System.out.println("amoodi");
                red = hamsaye(x - 20, y + 20 * i);

                if (red) {
                    return true;
                }
            }
            for (int i = -1; i <= tedadDarErtefa; i++) {
                red = hamsaye(x + 60, y + 20 * i);

                if (red) {
                    return true;
                }
            }
            //  if(hamsaye( x+20 ,y-20)){
            //      return true;
            //     }

        }
        return false;
    }

    private boolean hamsaye(int x, int y) {
        if (x > 0 && y > 0 && x <= 400 && y <= 400) {
            int p = createImage(component).getRGB(x, y);
            int r = (p >> 16) & 0xff;
            if (r == 255) {
                return true;

            }
        }

        return false;
    }


    public void setChangable(boolean changable) {
        this.changable = changable;
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
