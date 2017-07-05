
package ir.aut.ceit.app.view;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by shsh9692 on 6/12/2017.
 */
class ForRotate extends MouseInputAdapter {
    private PlayFiled component;
    boolean finishRotate = true;
    private ArrayList <Rectangle >rect;
    private boolean changable;

    public ForRotate(PlayFiled gdad, ArrayList <Rectangle >rect, boolean changable) {
        component = gdad;
        component.addMouseListener(this);
        component.addMouseMotionListener(this);
        this.changable = changable;
        this.rect = rect;
    }

    public void mousePressed(MouseEvent e) {

        if (changable ) {
            Point p = e.getPoint();
            for (Rectangle rectangle : rect) {


            if ( rectangle.contains(p)&& finishRotate ) {


                rectangle.setRect((int)  rectangle.getX(), (int)  rectangle.getY(), (int)  rectangle.getHeight(), (int)  rectangle.getWidth());
                component.setRect( rectangle, (int)  rectangle.getX(), (int)  rectangle.getY());

                 finishRotate = false;
                System.out.println(finishRotate);
            }}
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
    }

    public void setChangable(boolean changable) {
        this.changable = changable;
    }


}


