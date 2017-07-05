package ir.aut.ceit.app.view;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by shsh9692 on 6/12/2017.
 */
class GraphicDragController extends MouseInputAdapter {
    private PlayFiled component;
    Point offset = new Point();
    private  boolean dragging = false;
    private  Rectangle rect;
    private boolean changable;
    public GraphicDragController(PlayFiled gdad,Rectangle rect,boolean changable ) {
        component = gdad;
        component.addMouseListener(this);
        component.addMouseMotionListener(this);
        this.changable=changable;
        this.rect=rect;
    }

    public void mousePressed(MouseEvent e) {
        if (changable){
        Point p = e.getPoint();
        if(rect.contains(p)) {
            offset.x = p.x - rect.x;
            offset.y = p.y - rect.y;
            dragging = true;
        }}
    }

    public void mouseReleased(MouseEvent e) {
        if (changable)dragging = false;
    }

    public void mouseDragged(MouseEvent e) {
        if (changable){
        if(dragging) {
            int x = e.getX() - offset.x;
            int y = e.getY() - offset.y;
            component.setRect(rect,x, y);
        }
    }}

    public void setChangable(boolean changable) {
        this.changable = changable;
    }
}
