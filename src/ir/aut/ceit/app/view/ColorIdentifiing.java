package ir.aut.ceit.app.view;

/**
 * Created by shsh9692 on 7/7/2017.
 */
public class ColorIdentifiing {
    private  int p;

    public void setP(int p) {
        this.p = p;
    }

    public  ColorIdentifiing (int place){
       p=place;
    }
    public int findingBlue() {
        return   p & 0xff;
    }

    public int findingGreen() {
        return (p >> 8) & 0xff;
    }

    public int findingRed() {
        return   (p >> 16) & 0xff;
    }

}
