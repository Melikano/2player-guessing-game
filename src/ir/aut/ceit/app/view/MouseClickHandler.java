package ir.aut.ceit.app.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by shsh9692 on 6/25/2017.
 */
public class MouseClickHandler extends MouseAdapter {
    private int x;
    private int y;

    public void mouseClicked(MouseEvent event) {
        if (event.getX() < 400 && event.getY() < 400) {
            x = event.getX();
            y = event.getY();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}