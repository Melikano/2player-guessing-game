package ir.aut.ceit.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by shsh9692 on 6/10/2017.
 */
public class PlayFrame extends JFrame {
private ArrayList<Rectangle> rects;
    private PlayFiled play;
    private PlayFiled enemyPlay;
    private GraphicDragController rectTwo1GD;
    private GraphicDragController rectTwo2GD;
    private GraphicDragController rectTwo3GD;
    private GraphicDragController rectOne1GD;
    private GraphicDragController rectOne2GD;
    private GraphicDragController rectOne3GD;
    private GraphicDragController rectOne4GD;
    private GraphicDragController rectthree1GD;
    private GraphicDragController rectthree2GD;
    private MouseClickHandler mouseClickHandler;
    private ForRotate rotating;
    private int x;
    private int y;

    private JButton rotate = new JButton("rotate");

    public PlayFrame() {
        super("kk");
        setSize(1000, 1000);
rects = new ArrayList<Rectangle>();
        JPanel j = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        enemyPlay = new PlayFiled(1);
        j.setSize(1000, 800);
        j.add(enemyPlay);
        enemyPlay.paint2();
        play = new PlayFiled(0);

        j.add(play);
        play.paint2();
        rectTwo1GD = new GraphicDragController(play, play.rectTwo1, true);
        rects.add(play.rectTwo1);
        rectTwo2GD = new GraphicDragController(play, play.rectTwo2, true);
        rects.add(play.rectTwo2);
        rectTwo3GD = new GraphicDragController(play, play.rectTwo3, true);
        rects.add(play.rectTwo3);
        rectOne1GD = new GraphicDragController(play, play.rectOne1, true);
        rects.add(play.rectOne1);
        rectOne2GD = new GraphicDragController(play, play.rectOne2, true);
        rects.add(play.rectOne2);
        rectOne3GD = new GraphicDragController(play, play.rectOne3, true);
        rects.add(play.rectOne3);
        rectOne4GD = new GraphicDragController(play, play.rectOne4, true);
        rects.add(play.rectOne4);
        rectthree1GD = new GraphicDragController(play, play.rectthree1, true);
        rects.add(play.rectthree1);
        rectthree2GD = new GraphicDragController(play, play.rectthree2, true);
        rects.add(play.rectthree2);

        mouseClickHandler = new MouseClickHandler();
        j.setLayout(new GridLayout());
        j.setVisible(true);
        add(j);
        rotate.setMnemonic(KeyEvent.VK_R);
        rotate.addActionListener(new Handler());
        rotate.setSize(10, 10);
        add(rotate, BorderLayout.SOUTH);

        setVisible(true);
        addMouseListener(mouseClickHandler);
        System.out.println(mouseClickHandler.getX());
        System.out.println(mouseClickHandler.getY());
        x = mouseClickHandler.getX();
        y = mouseClickHandler.getY();
    }

    public void gameStart() {
        rectTwo1GD.setChangable(false);
        rectTwo2GD.setChangable(false);
        rectTwo3GD.setChangable(false);
        rectOne1GD.setChangable(false);
        rectOne2GD.setChangable(false);
        rectOne3GD.setChangable(false);
        rectOne4GD.setChangable(false);
        rectthree1GD.setChangable(false);
        rectthree2GD.setChangable(false);
        rotate.setEnabled(false);
    }

    public Boolean myHitandLose(int x, int y) {

        play.hittedFromenemy(x, y);
        return true;

    }


    public void enemyHitandLose(int x, int y, boolean hit) {
        enemyPlay.hittedFromYou(x, y, hit);

    }

    public MouseClickHandler getMouseClickHandler() {
        return mouseClickHandler;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private class Handler implements ActionListener {
        int counter;
        int counter1;
        int counter2;

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == rotate) {
                rotating = new ForRotate(play,rects, true);
                System.out.println("you can rotate");


            }
        }
    }

}



