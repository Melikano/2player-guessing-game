package ir.aut.ceit.app.view;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by shsh9692 on 7/9/2017.
 */
public class NotOverLap {
    public boolean cantRotate(Rectangle rectangle,ArrayList<Rectangle> rect){
        int h =(int)rectangle.getX()+10;
        //   System.out.println(h);
        //  System.out.println("kkkk2");
        while(h<rectangle.getWidth()+(int)rectangle.getX()){
            //   System.out.println("kkkk1");
            if(    hamsaye(h,(int)rectangle.getY()+10,rectangle,rect)){

                return true;}
            h=h+40;

        }
        int h2 =(int)rectangle.getY()+10;
        while(h2<rectangle.getHeight()+(int)rectangle.getY()){

            if( hamsaye((int)rectangle.getX()+10,h2,rectangle,rect)) {

                return true;
            }
            h2=h2+40;
        }
        return false;
    }
    private boolean hamsaye(int x, int y,Rectangle rectangle1,ArrayList<Rectangle> rect) {

        if (x > 0 && y > 0 && x <= 400 && y <= 400) {
            for (Rectangle rectangle :  rect) {
                if(rectangle.contains(x,y)&& !rectangle.equals(rectangle1)){
                    //  System.out.println("can not rotate2");
                    return true;
                }
            }

        }

        return false;
    }



}
