package ir.aut.ceit.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by shsh9692 on 7/7/2017.
 */
public class Vaziat {
    private JPanel jpanel;

    public Vaziat(JPanel jPanel1) {
        jpanel = jPanel1;
    }

    public String vaziatRect(int x, int y) {

        ColorIdentifiing color =new ColorIdentifiing(createImage(jpanel).getRGB(x , y ));

        if (y + 50 < 400) {
            color = new ColorIdentifiing(createImage(jpanel).getRGB(x + 10, y + 50));
            //amoodi balaii rozade
            if (color.findingGreen() == 176) {
                return "21";
            }
        }
        //amoodi paieni rozade
        if (y - 50 > 0) {
            color.setP(createImage(jpanel).getRGB(x + 10, y - 30));
            if (color.findingGreen() == 176) {
                return "22";
            }
        }
        if (x + 50 < 400) {
            color.setP(createImage(jpanel).getRGB(x + 50, y + 10));
            if (color.findingGreen() == 176) {
                return "23";
            }
        }
        if (x - 50 > 0) {
            color.setP(createImage(jpanel).getRGB(x - 30, y + 10));
            if (color.findingGreen() == 176) {
                return "24";
            }
        }
        return "1";
    }

    public String vaziatRect3(int x, int y) {

        ColorIdentifiing color =new ColorIdentifiing(createImage(jpanel).getRGB(x , y ));

        if (y + 50 < 400) {
            color = new ColorIdentifiing(createImage(jpanel).getRGB(x + 10, y + 50));
            //amoodi balaii rozade
            if (color.findingGreen() == 176) {
                return "21";
            }
        }
        //amoodi paieni rozade
        if (y - 50 > 0) {
            color.setP(createImage(jpanel).getRGB(x + 10, y - 30));
            if (color.findingGreen() == 176) {
                return "22";
            }
        }
        if (x + 50 < 400) {
            color.setP(createImage(jpanel).getRGB(x + 50, y + 10));
            if (color.findingGreen() == 176) {
                return "23";
            }
        }
        if (x - 50 > 0) {
            color.setP(createImage(jpanel).getRGB(x - 30, y + 10));
            if (color.findingGreen() == 176) {
                return "24";
            }
        }
        return "1";
    }


    public BufferedImage createImage(JPanel panel) {

        int w = panel.getWidth();
        int h = panel.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.paint(g);
        return bi;

    }

    public String vaziatRect2(int x, int y) {
        String vaziat = vaziatRect3(x, y);
        ColorIdentifiing color = new ColorIdentifiing(createImage(jpanel).getRGB(x , y));

        if (vaziat.equals("21")) {
            if (y + 90 < 400) {
                //amoodi balabalie
                color.setP(createImage(jpanel).getRGB(x + 10, y + 90));
                if (color.findingGreen() == 176) {
                    
                    return "31";
                }
            }
//amoodi vasati
            if (y - 10 > 0) {
                color.setP(createImage(jpanel).getRGB(x + 10, y - 10));
                if (color.findingGreen() == 176) {
                    

                    return "32";
                }
            }
        }
        if (vaziat.equals("22")) {
            if (y - 50 > 0) {
                //amoodi balabalie
                color.setP(createImage(jpanel).getRGB(x + 10, y - 50));
                if (color.findingGreen() == 176) {
                    

                    return "33";
                }
            }
//amoodi vasati
            if (y + 50 < 400) {
                color.setP(createImage(jpanel).getRGB(x + 10, y + 50));
                if (color.findingGreen() == 176) {
                    return "34";
                }


            }
        }
        if (vaziat.equals("23")) {
            if (x + 90 < 400) {
                //e
                color.setP(createImage(jpanel).getRGB(x + 90, y + 10));
                if (color.findingGreen() == 176) {
                    return "35";
                }
            }

            if (x - 10 > 0) {
                color.setP(createImage(jpanel).getRGB(x - 10, y + 10));
                if (color.findingGreen() == 176) {
                    return "36";
                }


            }
        }
        if (vaziat.equals("24")) {
            if (x - 50 > 0) {

                color.setP(createImage(jpanel).getRGB(x - 50, y + 10));
                if (color.findingGreen() == 176) {
                    return "37";
                }
            }

            if (x + 10 < 0) {
                color.setP(createImage(jpanel).getRGB(x + 10, y + 10));
                if (color.findingGreen() == 176) {
                    return "38";
                }


            }
        }
        return "0";
    }
}