package ir.aut.ceit.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
    private GraphicDragController rectFourGD;
    private JPanel jpanelBottons;
    private MouseClickHandler mouseClickHandler;
    private ForRotate rotating;
    private JButton rotate = new JButton("rotate");
    private JButton leave = new JButton("leave");
    private JButton start = new JButton("Start");
    private JButton chatHistory = new JButton(" chatHistory");
    private boolean showChatHistory;
    private int x;
    private int y;
    private boolean leaved = false;
    private boolean started = false;


    public PlayFrame() {
        super("playFrame");
        setSize(1000, 1000);
        rects = new ArrayList<Rectangle>();
        jpanelBottons = new JPanel();
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
        rectTwo1GD = new GraphicDragController(play, play.rectTwo1, true, rects);
        rects.add(play.rectTwo1);
        rectTwo2GD = new GraphicDragController(play, play.rectTwo2, true, rects);
        rects.add(play.rectTwo2);
        rectTwo3GD = new GraphicDragController(play, play.rectTwo3, true, rects);
        rects.add(play.rectTwo3);
        rectOne1GD = new GraphicDragController(play, play.rectOne1, true, rects);
        rects.add(play.rectOne1);
        rectOne2GD = new GraphicDragController(play, play.rectOne2, true, rects);
        rects.add(play.rectOne2);
        rectOne3GD = new GraphicDragController(play, play.rectOne3, true, rects);
        rects.add(play.rectOne3);
        rectOne4GD = new GraphicDragController(play, play.rectOne4, true, rects);
        rects.add(play.rectOne4);
        rectthree1GD = new GraphicDragController(play, play.rectthree1, true, rects);
        rects.add(play.rectthree1);
        rectthree2GD = new GraphicDragController(play, play.rectthree2, true, rects);
        rects.add(play.rectthree2);
        rectFourGD = new GraphicDragController(play, play.rectFour, true, rects);
        rects.add(play.rectFour);

        mouseClickHandler = new MouseClickHandler();
        j.setLayout(new GridLayout());
        j.setVisible(true);
        add(j);
        rotate.setMnemonic(KeyEvent.VK_R);
        rotate.addActionListener(new Handler());
        leave.addActionListener(new Handler());
        start.addActionListener(new Handler());
        chatHistory.addActionListener(new Handler());
        rotate.setSize(10, 10);
        leave.setSize(10, 10);
        jpanelBottons.add(rotate);
        jpanelBottons.add(leave);
        jpanelBottons.add(start);
        jpanelBottons.add(chatHistory);

        add(jpanelBottons, BorderLayout.SOUTH);


        setVisible(true);
        addMouseListener(mouseClickHandler);
        System.out.println(mouseClickHandler.getX());
        System.out.println(mouseClickHandler.getY());
        x = mouseClickHandler.getX();
        y = mouseClickHandler.getY();
    }


    public MouseClickHandler getMouseClickHandler() {

        return mouseClickHandler;

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
        rectFourGD.setChangable(false);
        rotate.setEnabled(false);
    }

    public String myHitandLose(int x, int y) throws InterruptedException {

        String result = play.hittedFromenemy(x, y);
        myChangingFields(result);
        return result;
    }


    // public void enemyHitandLose(int x, int y, boolean hit, int kindOfRec) {
    public void enemyHitandLose(String messege) throws InterruptedException {
        String[] array;
        array = messege.split("&");
//        enemyPlay.hittedFromYou(120, 120,1, 3);
        if (array[0].equals("1")) {
            enemyPlay.hittedFromYou(Integer.parseInt(array[array.length - 2]), Integer.parseInt(array[array.length - 1]), Integer.parseInt(array[1]), Integer.parseInt(array[0]), Integer.parseInt(array[2]));
            if(enemyPlay.isDone()) {
                changingFields(messege);
            }
            // enemyPlay.hittedFromYou(120, 120,1, 3);
            // enemyPlay. hittedFromYou(x,y,kindOfRec,hit);
        } else {
            enemyPlay.hittedFromYou(Integer.parseInt(array[array.length - 2]), Integer.parseInt(array[array.length - 1]), 0, Integer.parseInt(array[0]), 0);
            if(enemyPlay.isDone()) {
                changingFields(messege);
            }
        }
        enemyPlay.setDone(false);
    }

    public void changingFields(String messege) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        if (messege.charAt(0) == '0') {
            enemyPlay.setVisible(false);
            play.setVisible(true);
            setVisible(true);
        }
        if (messege.charAt(0) == '1') {
            play.setVisible(false);
            enemyPlay.setVisible(true);
            setVisible(true);
        }
    }

    public void myChangingFields(String messege) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        if (messege.charAt(0) == '0') {
            enemyPlay.setVisible(true);
            play.setVisible(false);
            setVisible(true);
        }
        if (messege.charAt(0) == '1') {
            play.setVisible(true);
            enemyPlay.setVisible(false);
            setVisible(true);
        }
    }

    public void firstLevelShow() {
        enemyPlay.setVisible(false);
        play.setVisible(true);
    }

    public boolean isLeaved() {
        return leaved;
    }

    public void setLeaved(boolean leaved) {
        this.leaved = leaved;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isShowChatHistory() {
        return showChatHistory;
    }

    public void setShowChatHistory(boolean showChatHistory) {
        this.showChatHistory = showChatHistory;
    }

    public PlayFiled getPlay() {
        return play;
    }

    public PlayFiled getEnemyPlay() {
        return enemyPlay;
    }

    private class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == rotate) {
                rotating = new ForRotate(play, rects, true);
                System.out.println("you can rotate");


            }
            if (event.getSource() == start) {
                gameStart();
                started = true;

            }
            if (event.getSource() == leave) {
                leaved = true;

            }if (event.getSource() == chatHistory) {

                showChatHistory = true;

            }
        }
    }

    //PlayFrame k = new PlayFrame();
    //k.gameStart();
    // System.out.println("blue");

    //  k.myHitandLose(0,0);
    //  k.myHitandLose(1,1 );
    //   System.out.println("black");
    //  k.myHitandLose(15,15);
    //   k.myHitandLose(15,45);
    //    k.myHitandLose(15, 45);
    //   k.myHitandLose(330, 60);
    //   k.myHitandLose(15, 45);
//        k.myHitandLose(120, 120);

    //k.enemyHitandLose("1&3&1&160&360");
    //  k.enemyHitandLose("1&2&1&0&0");
    // k.enemyHitandLose("1&7&23&120&120");
    //  k.enemyHitandLose("1&7&31&120&120");
    // k.enemyHitandLose(120, 120,true, 3);


}