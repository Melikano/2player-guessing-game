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


    public GraphicDragController(PlayFiled gdad, Rectangle rect, boolean changable, ArrayList<Rectangle> rects1) {
        component = gdad;
        component.addMouseListener(this);
        component.addMouseMotionListener(this);
        this.changable = changable;
        this.rect = rect;
        rects = rects1;

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
                    NotOverLap notOverLap = new NotOverLap();
                    if (notNearRed(x1, y1) || notOverLap.cantRotate(rect,rects) ) {
                        System.out.println("sotty you can not put in here");
                        component.setRect(rect, x2, y2);
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
        int mabdaKeshidanx = -1;
        int mabdaKeshidany = -1;
        int width = -1;
        int height = -1;
        for (int i = 0; i < rects.size(); i++) {
            if (rects.get(i).contains(x, y)) {
                width = (int) rects.get(i).getWidth();
                height = (int) rects.get(i).getHeight();
                mabdaKeshidanx = (int) rects.get(i).getX();
                mabdaKeshidany = (int) rects.get(i).getY();
            }}
            // x-10 ta x+width+10
            //y-10 ta y+height+10
            if (mabdaKeshidanx == -1 || mabdaKeshidany == -1 || width == -1 || height == -1) {

                return false;
            } else {
                for (int p = 0; p <= (((width + 20) / 40) + 1); p++) {
                   if( hamsaye(x - 10 + p * 40, mabdaKeshidany - 10)){
                       System.out.println("4");

                       return true;
                   }
                }
                for (int p = 0; p <= (((width + 20) / 40) + 1); p++) {
                    if(  hamsaye(x - 10 + p * 40, mabdaKeshidany + height + 10)){
                        System.out.println("3");

                        return true;
                    }

                }
                for (int p = 0; p <= (((height + 20) / 40) + 1); p++) {
                if(    hamsaye(mabdaKeshidanx - 10, y - 10 + p * 40)){
                    System.out.println("2");

                    return true;
                }
                }
                for (int p = 0; p <= (((height + 20) / 40) + 1); p++) {
                   if( hamsaye(mabdaKeshidanx + width + 10, y - 10 + p * 40)){
                       System.out.println("1");

                       return true;}
                }

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
