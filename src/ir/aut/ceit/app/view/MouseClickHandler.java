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
    private boolean coordinationUpdated;

    public void mouseClicked(MouseEvent event) {
        if (event.getX() < 400 && event.getY() < 440) {
            isClicked = true;
            coordinationUpdated = false;
            x = event.getX();
            y = event.getY() - 40;
            System.out.println("mousX : " + x + "mousY : " + y);
            coordinationUpdated = true;
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

    public boolean isCoordinationUpdated() {
        return coordinationUpdated;
    }

    public void setCoordinationUpdated(boolean coordinationUpdated) {
        this.coordinationUpdated = coordinationUpdated;
    }
}