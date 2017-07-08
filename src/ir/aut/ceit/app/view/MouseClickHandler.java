package ir.aut.ceit.app.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by shsh9692 on 6/25/2017.
 */
public class MouseClickHandler extends MouseAdapter {
    private boolean isClicked;
    private int x;
    private int y;

    public void mouseClicked(MouseEvent event) {
        if (event.getX() < 400 && event.getY() < 440) {
            isClicked = true;
            x = event.getX();
            y = event.getY() - 40;
            System.out.println("mousX : " + x + "mousY : " + y);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }
}