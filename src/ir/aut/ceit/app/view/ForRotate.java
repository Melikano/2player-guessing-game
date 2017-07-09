
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
class ForRotate extends MouseInputAdapter {
    private PlayFiled component;
    boolean finishRotate = true;
    private ArrayList<Rectangle> rect;
    private boolean changable;

    public ForRotate(PlayFiled gdad, ArrayList<Rectangle> rect, boolean changable) {
        component = gdad;
        component.addMouseListener(this);
        component.addMouseMotionListener(this);
        this.changable = changable;
        this.rect = rect;
    }

    public void mousePressed(MouseEvent e) {

        if (changable) {
            Point p = e.getPoint();
            for (Rectangle rectangle : rect) {


                if (rectangle.contains(p) && finishRotate) {
                    // if (rectangle.getX() + rectangle.getWidth() < 400 && rectangle.getY() + rectangle.getHeight() < 400) {
                    rectangle.setRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getHeight(), (int) rectangle.getWidth());

                    component.setRect(rectangle, (int) rectangle.getX(), (int) rectangle.getY());

                    finishRotate = false;


                    if (rectangle.getX() + rectangle.getWidth() < 0 || rectangle.getX() + rectangle.getWidth() > 400 || rectangle.getY() + rectangle.getHeight() > 400 || rectangle.getY() + rectangle.getHeight() < 0) {
                        rectangle.setRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getHeight(), (int) rectangle.getWidth());

                        component.setRect(rectangle, (int) rectangle.getX(), (int) rectangle.getY());
                        System.out.println("sorry can not rotate");
                    }
                    NotOverLap notOverLap = new NotOverLap();
                   if (notOverLap.cantRotate(rectangle,rect)) {

                       rectangle.setRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getHeight(), (int) rectangle.getWidth());

                        component.setRect(rectangle, (int) rectangle.getX(), (int) rectangle.getY());


                  }
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
    }

    public void setChangable(boolean changable) {
        this.changable = changable;
    }

    public boolean cantRotate(Rectangle rectangle){
        int h =(int)rectangle.getX()+10;
     //   System.out.println(h);
      //  System.out.println("kkkk2");
        while(h<rectangle.getWidth()+(int)rectangle.getX()){
         //   System.out.println("kkkk1");
        if(    hamsaye(h,(int)rectangle.getY()+10,rectangle)){

            return true;}
            h=h+40;

        }
        int h2 =(int)rectangle.getY()+10;
        while(h2<rectangle.getHeight()+(int)rectangle.getY()){

            if( hamsaye((int)rectangle.getX()+10,h2,rectangle)) {

                return true;
            }
            h2=h2+40;
        }
        return false;
    }
    private boolean hamsaye(int x, int y,Rectangle rectangle1) {

        if (x > 0 && y > 0 && x <= 400 && y <= 400) {
            for (Rectangle rectangle : rect) {
                if(rectangle.contains(x,y)&& !rectangle.equals(rectangle1)){
                  //  System.out.println("can not rotate2");
                    return true;
                }
            }

        }

        return false;
    }





}


