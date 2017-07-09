package ir.aut.ceit.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.util.ArrayList;

/**
 * Created by shsh9692 on 6/4/2017.
 */
public class PlayFiled extends JPanel {
    private ArrayList<FirstCordination> placeshit = new ArrayList<FirstCordination>();
    private ArrayList<FirstCordination> placesmiss = new ArrayList<FirstCordination>();
    private ArrayList<FirstCordination> hamsayee = new ArrayList<>();
    private ArrayList<FirstCordination> littleRects = new ArrayList<>();
    private int allMarked = 0;
    private int youWon = 0;
    private int opponentWon = 0;
    private boolean done;

    private int endOfTheGame = 0;
    int hostOrGeust;
    Rectangle rectTwo1;
    Rectangle rectTwo2;
    Rectangle rectTwo3;
    Rectangle rectOne1;
    Rectangle rectOne2;
    Rectangle rectOne3;
    Rectangle rectOne4;
    Rectangle rectthree1;
    Rectangle rectthree2;
    Rectangle rectFour;

    public PlayFiled(int type) {
        setBackground(Color.white);
        setSize(500, 500);
        hostOrGeust = type;
        rectOne1 = new Rectangle(0, 0, 40, 40);
        rectOne2 = new Rectangle(360, 360, 40, 40);
        rectOne3 = new Rectangle(200, 40, 40, 40);
        rectOne4 = new Rectangle(360, 80, 40, 40);
        rectTwo1 = new Rectangle(120, 0, 40, 80);
        rectTwo2 = new Rectangle(0, 200, 40, 80);
        rectTwo3 = new Rectangle(0, 320, 40, 80);
        rectthree1 = new Rectangle(200, 160, 40, 120);
        rectthree2 = new Rectangle(120, 120, 40, 120);
        rectFour = new Rectangle(280, 0, 40, 160);

    }

    public void paint2() {

        repaint();
    }

    @Override

    public void paint(Graphics g) {
        super.paint(g);
        draw(g);
    }


    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage bufferedImage = new BufferedImage(40, 40, BufferedImage.TYPE_INT_RGB);
        Graphics2D gg = bufferedImage.createGraphics();
        gg.setColor(new Color(0.32941177f, 1.0f, 0.27450982f));
        gg.drawRect(0, 0, 40, 40);
        g2d.setPaint(new TexturePaint(bufferedImage, new Rectangle(40, 40)));
        g2d.fill(new Rectangle(0, 0, 400, 400));

        g.setColor(Color.GRAY);
        g.fillRect(0, 470, 10, 10);
        g.fillRect(15, 470, 10, 10);
        g.fillRect(30, 470, 10, 10);
        g.fillRect(45, 470, 10, 10);

        g.fillRect(0, 450, 10, 10);
        g.fillRect(12, 450, 10, 10);

        g.fillRect(30, 450, 10, 10);
        g.fillRect(42, 450, 10, 10);

        g.fillRect(60, 450, 10, 10);
        g.fillRect(72, 450, 10, 10);

        g.fillRect(0, 430, 10, 10);
        g.fillRect(12, 430, 10, 10);
        g.fillRect(24, 430, 10, 10);

        g.fillRect(40, 430, 10, 10);
        g.fillRect(52, 430, 10, 10);
        g.fillRect(64, 430, 10, 10);

        g.fillRect(0, 410, 10, 10);
        g.fillRect(12, 410, 10, 10);
        g.fillRect(24, 410, 10, 10);
        g.fillRect(36, 410, 10, 10);
        if (hostOrGeust == 0) {
            g.setColor(new Color(1.0f, 0.101960786f, 0.16078432f));
            g.fillRect((int) rectTwo1.getX(), (int) rectTwo1.getY(), (int) rectTwo1.getWidth(), (int) rectTwo1.getHeight());
            g.fillRect((int) rectTwo2.getX(), (int) rectTwo2.getY(), (int) rectTwo2.getWidth(), (int) rectTwo2.getHeight());
            g.fillRect((int) rectTwo3.getX(), (int) rectTwo3.getY(), (int) rectTwo3.getWidth(), (int) rectTwo3.getHeight());
            g.fillRect((int) rectOne1.getX(), (int) rectOne1.getY(), (int) rectOne1.getWidth(), (int) rectOne1.getHeight());
            g.fillRect((int) rectOne2.getX(), (int) rectOne2.getY(), (int) rectOne2.getWidth(), (int) rectOne2.getHeight());
            g.fillRect((int) rectOne3.getX(), (int) rectOne3.getY(), (int) rectOne3.getWidth(), (int) rectOne3.getHeight());
            g.fillRect((int) rectOne4.getX(), (int) rectOne4.getY(), (int) rectOne4.getWidth(), (int) rectOne4.getHeight());
            g.fillRect((int) rectthree1.getX(), (int) rectthree1.getY(), (int) rectthree1.getWidth(), (int) rectthree1.getHeight());
            g.fillRect((int) rectthree2.getX(), (int) rectthree2.getY(), (int) rectthree2.getWidth(), (int) rectthree2.getHeight());
            g.fillRect((int) rectFour.getX(), (int) rectFour.getY(), (int) rectFour.getWidth(), (int) rectFour.getHeight());

        }

        for (FirstCordination place : placeshit) {

            Color c = new Color(0.0f, 0.6901961f, 0.0f);
            g.setColor(c);
            g.fillRect(place.getX(), place.getY(), 40, 40);
        }
        for (FirstCordination place : littleRects) {

            Color c = new Color(0.0f, 0.6901961f, 0.0f);
            g.setColor(c);
            g.fillRect(place.getX(), place.getY(), 10, 10);
        }
        for (FirstCordination place : hamsayee) {

            Color c = new Color(0.7137255f, 0.69803923f, 1.0f);
            g.setColor(c);
            g.fillRect(place.getX(), place.getY(), 40, 40);
        }
        for (FirstCordination place : placesmiss) {

            Color c = new Color(0.7137255f, 0.69803923f, 1.0f);
            g.setColor(c);
            g.fillRect(place.getX(), place.getY(), 40, 40);
        }
    }

    public void setRect(Rectangle recty, int x, int y) {
        recty.setLocation(x, y);
        repaint();
    }

    public String hittedFromenemy(double cordinationx, double cordinationy) {
        String tobeSent = "";
        int x = (int) cordinationx;
        int y = (int) cordinationy;
        ColorIdentifiing color = new ColorIdentifiing(createImage(this).getRGB(x, y));
        int r = color.findingRed();
        int g = color.findingGreen();
        int b = color.findingBlue();
        x = x / 40;
        x = x * 40;
        y = y / 40;
        y = y * 40;
        if (r == 255) {
            System.out.println("hit");
            FirstCordination f = new FirstCordination(x, y);
            placeshit.add(f);
            repaint();
            String hamsayeha = kindOfRect(x, y, 0);
            opponentWon++;
            return "1" + "&" + hamsayeha + "&" + x + "&" + y;

        }
        if (r == 0 && g == 0 && b == 0) {
            System.out.println("miss");
            FirstCordination f = new FirstCordination(x, y);
            placesmiss.add(f);
            repaint();
            return "0" + "&" + x + "&" + y;
        }
        return "0" + "&" + x + "&" + y;
    }


    private String kindOfRect(int x, int y, int noe) {
        Vaziat vaziat = new Vaziat(this);
        if ((rectOne1.contains(x + 10, y + 10)) || noe == 1) {
            contain(0, 470);
            //yedoonas
            return "1" + "&" + "1";

        }
        if (rectOne2.contains(x + 10, y + 10) || noe == 2) {
            contain(15, 470);
            //yedoonas
            youWon++;
            return "2" + "&" + "1";
        }
        if (rectOne3.contains(x + 10, y + 10) || noe == 3) {
            contain(30, 470);
            //yedoonas
            youWon++;
            return "3" + "&" + "1";

        }
        if (rectOne4.contains(x + 10, y + 10) || noe == 4) {
            contain(45, 470);
            //yedoonas
            allMarked++;
            return "4" + "&" + "1";
        }
        if (rectTwo1.contains(x + 10, y + 10) || noe == 5) {
            containForTwoRecs(rectTwo1, x, y, 1);

            return "5" + "&" + vaziat.vaziatRect(x, y);


        }
        if (rectTwo2.contains(x + 10, y + 10) || noe == 6) {
            containForTwoRecs(rectTwo2, x, y, 2);

            return "6" + "&" + vaziat.vaziatRect(x, y);

        }
        if (rectTwo3.contains(x + 10, y + 10) || noe == 7) {
            containForTwoRecs(rectTwo3, x, y, 3);

            return "7" + "&" + vaziat.vaziatRect(x, y);

        }
        if (rectthree1.contains(x + 10, y + 10) || noe == 8) {
            containForThreeRecs(rectthree1, x, y, 1);

            return "8" + "&" + vaziat.vaziatRect2(x, y);

        }
        if (rectthree2.contains(x + 10, y + 10) || noe == 9) {
            containForThreeRecs(rectthree2, x, y, 2);
            return "9" + "&" + vaziat.vaziatRect2(x, y);
        }
        if (rectFour.contains(x + 10, y + 10) || noe == 10) {
            containForFourRecs(rectFour, x, y, 1);
            return "10"+ "&"+"44";
        }
        return null;
    }

    private void containForThreeRecs(Rectangle rect, int a, int b, int num) {
        if ((int) rect.getX() == a && (int) rect.getY() == b) {
            allMarked++;

            contain((num - 1) * 40, 430);
        } else if (((int) rect.getX() + 40 == a && (int) rect.getY() == b) || ((int) rect.getX() == a && (int) rect.getY() + 40 == b)) {
            contain(((num - 1) * 40 + 12), 430);
            allMarked++;

        } else {
            contain(((num - 1) * 40 + 24), 430);
            allMarked++;

        }
    }

    private void containForFourRecs(Rectangle rect, int a, int b, int num) {
        if ((int) rect.getX() == a && (int) rect.getY() == b) {
            contain((num - 1) * 40, 410);
            allMarked++;
        } else if (((int) rect.getX() + 40 == a && (int) rect.getY() == b) || ((int) rect.getX() == a && (int) rect.getY() + 40 == b)) {
            contain(((num - 1) * 40 + 12), 410);
            allMarked++;
        } else if (((int) rect.getX() + 80 == a && (int) rect.getY() == b) || ((int) rect.getX() == a && (int) rect.getY() + 80 == b)) {
            {
                allMarked++;
                contain(((num - 1) * 40 + 24), 410);
            }
        } else {
            allMarked++;
            contain(((num - 1) * 40 + 36), 410);
        }
    }

    private void containForTwoRecs(Rectangle rect, int a, int b, int num) {
        if ((int) rect.getX() == a && (int) rect.getY() == b) {
            contain((num - 1) * 40, 450);
            allMarked++;

        } else {
            contain(((num - 1) * 40 + 12), 450);
            allMarked++;

        }
    }

    private void contain(int a, int b) {
        FirstCordination f = new FirstCordination(a, b);
        littleRects.add(f);
    }

    public void hittedFromYou(int cordinationx, int cordinationy, int noe, int hit, int kindOfHit) {
        if (hit == 1) {
            FirstCordination f = new FirstCordination(cordinationx, cordinationy);
            placeshit.add(f);
            hamsaye(cordinationx, cordinationy);

            youWon++;
            kindOfRect(cordinationx, cordinationy, noe);
            findingKindOfHit(kindOfHit, cordinationx, cordinationy);
            repaint();


        }
        if (hit == 0) {
            FirstCordination f = new FirstCordination(cordinationx, cordinationy);
            placesmiss.add(f);
            repaint();

        }
        done = true;

    }

    private void findingKindOfHit(int kindOfHit, int x, int y) {
        if (kindOfHit == 1) {
            if (y - 40 > 0) {
                FirstCordination f2 = new FirstCordination(x, y - 40);
                hamsayee.add(f2);
            }
            if (x + 40 < 400) {
                FirstCordination f2 = new FirstCordination(x + 40, y);
                hamsayee.add(f2);
            }
            if (x - 40 > 0) {
                FirstCordination f2 = new FirstCordination(x - 40, y);
                hamsayee.add(f2);
            }
            if (y + 40 < 400) {
                FirstCordination f2 = new FirstCordination(x, y + 40);
                hamsayee.add(f2);
            }
        }
        if (kindOfHit == 21) {
            if (y - 40 > 0) {
                FirstCordination f2 = new FirstCordination(x, y - 40);
                hamsayee.add(f2);
            }
            if (y + 80 < 400) {
                FirstCordination f3 = new FirstCordination(x, y + 80);
                hamsayee.add(f3);
            }
        }
        if (kindOfHit == 22) {
            if (y - 80 > 0) {
                FirstCordination f2 = new FirstCordination(x, y - 80);
                hamsayee.add(f2);
            }
            if (y + 40 < 400) {
                FirstCordination f3 = new FirstCordination(x, y + 40);
                hamsayee.add(f3);
            }
        }
        if (kindOfHit == 23) {
            if (x - 40 > 0) {
                FirstCordination f2 = new FirstCordination(x - 40, y);
                hamsayee.add(f2);
            }
            if (x + 80 < 400) {
                FirstCordination f3 = new FirstCordination(x + 80, y);
                hamsayee.add(f3);
            }
        }
        if (kindOfHit == 24) {
            if (x - 80 > 0) {
                FirstCordination f2 = new FirstCordination(x - 80, y);
                hamsayee.add(f2);
            }
            if (x + 40 < 400) {
                FirstCordination f3 = new FirstCordination(x + 40, y);
                hamsayee.add(f3);
            }
        }
        if (kindOfHit == 31) {
            if (y - 40 > 0) {
                FirstCordination f2 = new FirstCordination(x, y - 40);
                hamsayee.add(f2);
            }
            if (y + 120 < 400) {
                FirstCordination f3 = new FirstCordination(x, y + 120);
                hamsayee.add(f3);
            }
        }
        if (kindOfHit == 32 || kindOfHit == 34) {
            if (y - 80 > 0) {
                FirstCordination f2 = new FirstCordination(x, y - 80);
                hamsayee.add(f2);
            }
            if (y + 120 < 400) {
                FirstCordination f3 = new FirstCordination(x, y + 80);
                hamsayee.add(f3);
            }
        }
        if (kindOfHit == 33) {
            if (y + 40 < 400) {
                FirstCordination f2 = new FirstCordination(x, y + 40);
                hamsayee.add(f2);
            }
            if (y - 120 < 400) {
                FirstCordination f3 = new FirstCordination(x, y - 120);
                hamsayee.add(f3);
            }
        }
        if (kindOfHit == 35) {
            if (x - 40 > 0) {
                FirstCordination f2 = new FirstCordination(x - 40, y);
                hamsayee.add(f2);
            }
            if (x + 120 < 400) {
                FirstCordination f3 = new FirstCordination(x + 120, y);
                hamsayee.add(f3);
            }
        } if (kindOfHit == 36 || kindOfHit == 38) {
            if (x - 80 > 0) {
                FirstCordination f2 = new FirstCordination(x - 80, y);
                hamsayee.add(f2);
            }
            if (x + 80 < 400) {
                FirstCordination f3 = new FirstCordination(x + 80, y);
                hamsayee.add(f3);
            }
        }if (kindOfHit == 37) {
            if (x + 40 > 0) {
                FirstCordination f2 = new FirstCordination(x - 40, y);
                hamsayee.add(f2);
            }
            if (x -120>0) {
                FirstCordination f3 = new FirstCordination(x-120, y);
                hamsayee.add(f3);
            }
        }

    }


    public BufferedImage createImage(JPanel panel) {

        int w = panel.getWidth();
        int h = panel.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.paint(g);
        return bi;

    }

    public void hamsaye(int x, int y) {
        if (0 <= (x - 40) && 0 <= (y - 40)) {
            FirstCordination f = new FirstCordination(x - 40, y - 40);
            hamsayee.add(f);

        }
        if (0 <= (x - 40) && (y + 40) < 400) {
            FirstCordination f2 = new FirstCordination(x - 40, y + 40);
            hamsayee.add(f2);

        }
        if ((x + 40) < 400 && (y + 40) < 400) {
            FirstCordination f3 = new FirstCordination(x + 40, y + 40);
            hamsayee.add(f3);


        }
        if ((x + 40) < 400 && 0 <= (y - 40)) {
            FirstCordination f4 = new FirstCordination(x + 40, y - 40);
            hamsayee.add(f4);

        }


    }

    public int getAllMarked() {
        return allMarked;
    }

    public int getyouWon() {
        return youWon;
    }

    public int getOpponentWon() {
        return opponentWon;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}

