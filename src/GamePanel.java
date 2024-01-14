import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;

import static java.lang.Thread.sleep;

public class GamePanel extends JPanel {
    boolean gameRunning;
    public final int GAME_PANEL_WIDTH = 900;
    public final int GAME_PANEL_HEIGHT =  550;//(int) GAME_PANEL_WIDTH * (5/9)
    Color panelColor = new Color(0x14213D);
    Input inputController;

    //it because we want to prevent the bug that execute winner sound again because thread execute again
    int howManyTimeWinnerPlayed = 0;
    public Paddle paddleR ;
    public Paddle paddleL;
    private Graphics g;
    public Score scoreR ;
    public Score scoreL ;
    public Ball ball;
    public Physics physicsRules;

    public char paddleTouched = 'N';//because feature when ball hit the red paddle repaint to red and when
    //hit blue return to blue color
    //and in beginning because it doesn't have any hit instead of null we have 'N' it means : no hit yet
    //we declare it for that and for using in ball draw() parameter

    int middleCircleDiameter = 240;

    public int borderGapFrom = 8;
//******** for paddle touch
    File songHitPaddleNameFile;
    AudioInputStream songHitPaddleNameAIS;
    Clip songHitPaddleNameClip;
//******** for border touch
    File songHitBorderNameFile;
    AudioInputStream songHitBorderNameAIS;
    Clip songHitBorderNameClip;
//******** for score sound
    File songScoreNameFile;
    AudioInputStream songScoreNameAIS;
    Clip songScoreNameClip;
//******** for winner sound
    File songWinnerNameFile;
    AudioInputStream songWinnerNameAIS;
    Clip songWinnerNameClip;
//******** for winner sound
    File startNewGameSongFile;
    AudioInputStream startNewGameSongAIS;
    Clip startNewGameSongClip;

    public GamePanel()  {

        this.setPreferredSize(new Dimension(GAME_PANEL_WIDTH,GAME_PANEL_HEIGHT));
        this.setBackground(panelColor);
        this.setLayout(null);
        //this.add(paddleL); we don't use add fo rectangle subclass we use draw
        //this.add(paddleR);
        newGame();

        //setFocusable(true); is essential to have key listener
        this.setFocusable(true);/*****************************/
        this.addKeyListener(inputController);


    }
    public void newGame() {
        playStartNewGameSound();
        gameRunning = true;
        objectCreation();
        ball.newBall();


        /** this try catch commented because I want to say s.t
         * because we have running thread as game loop this Thread.sleep(5000);
         * stop game loop if you uncomment the sout in run method in PingPong.class
         * */
//        try{
//            new Thread().sleep(5000);
//        or
//        Thread.sleep(5000);
//        }catch (Exception e){
//            System.out.println("Exception in Thread.sleep(5000");
//        }
        //it because we want tp prevent the bug that execute again bucause thread execute again
        howManyTimeWinnerPlayed = 0;


    }

    private void objectCreation() {
        paddleL = new Paddle('L',GAME_PANEL_WIDTH,GAME_PANEL_HEIGHT);
        paddleR = new Paddle('R',GAME_PANEL_WIDTH,GAME_PANEL_HEIGHT);
        scoreL = new Score(paddleL);
        scoreR = new Score(paddleR);



        ball = new Ball();

        //because we have fbs drop in firs second of excution we found out we should declare it
        //in fisrt just once


        physicsRules = new Physics(this,paddleR,paddleL,ball);
        inputController = new Input(this);
    }

    public void update(){
        if (gameRunning) {
            paddleL.move();
            paddleR.move();
            ball.move();
        }//we don't need else because if we want to have new game we press space
    }
    public void rulesChecker(){
        //each ball hitting the left wall and right wall these check their work
        if(scoreL.score == Score.scoreToWinSet){
            scoreL.score = 0;
            scoreL.set++;
            scoreR.score = 0;
            //newRound();
        }
        if(scoreR.score == Score.scoreToWinSet){
            scoreR.score = 0;
            scoreR.set++;
            scoreL.score = 0;
            //newRound();
        }

        //for winner detecting these ifs do them
        if (scoreL.set == Score.setToWinGame ){
            paddleL.isItWinner =true;
            playWinnerSound();
            gameRunning = false;
        }else if (scoreR.set == Score.setToWinGame ){
            paddleR.isItWinner =true;

            playWinnerSound();

            gameRunning = false;
        }
    }

    public void playHitPaddleSound(){
        //when ball hit the paddles  this method will use and play
        /** we have fbs drop in first playing sound it because of File and Audio and playHitPaddleSound()
         and playing sound method
         FIX THIS
         */

        try {

            this.songHitPaddleNameFile = new File("sounds/ball hit the paddle.wav");
            this.songHitPaddleNameAIS = AudioSystem.getAudioInputStream(this.songHitPaddleNameFile.toURI().toURL());
            this.songHitPaddleNameClip = AudioSystem.getClip();
            this.songHitPaddleNameClip.open(songHitPaddleNameAIS);
            this.songHitPaddleNameClip.start();

        }catch (FileNotFoundException e){
            System.out.println("FileNotFoundException in playHitPaddleSound()");
        }catch (Exception e){
            System.out.println("Exception in playHitPaddleSound()");
        }
        System.out.println("\n\nplayHitPaddleSound() ");

    }

    //maybe we have same codes in to methods ,but I think I will have better sound effect
    //and it may change and may file path name change
    public void playHitBorderSound() {
        //when ball hit the up border or down this method will use and play

        /** we have fbs drop in first playing sound it because of File and Audio and playHitPaddleSound()
         and playing sound method
         FIX THIS
         */

        try {

            this.songHitBorderNameFile = new File("sounds/ball hit the paddle.wav");
            this.songHitBorderNameAIS = AudioSystem.getAudioInputStream(this.songHitBorderNameFile.toURI().toURL());
            this.songHitBorderNameClip = AudioSystem.getClip();
            this.songHitBorderNameClip.open(songHitBorderNameAIS);
            this.songHitBorderNameClip.start();

        }catch (FileNotFoundException e){
            System.out.println("FileNotFoundException in playHitBorderSound()");
        }catch (Exception e){
            System.out.println("Exception in playHitBorderSound()");
        }
        System.out.println("\n\nplayHitBorderSound() ");
    }


    public void playHitScoreSound() {
        //when ball hit the left border or right and score up this method will use and play

        /** we have fbs drop in first playing sound it because of File and Audio and playHitPaddleSound()
         and playing sound method
         FIX THIS
         */

        try {

            this.songScoreNameFile = new File("sounds/score Sound.wav");
            this.songScoreNameAIS = AudioSystem.getAudioInputStream(this.songScoreNameFile.toURI().toURL());
            this.songScoreNameClip = AudioSystem.getClip();
            this.songScoreNameClip.open(songScoreNameAIS);
            this.songScoreNameClip.start();

        }catch (FileNotFoundException e){
            System.out.println("FileNotFoundException in playHitScoreSound()");
        }catch (Exception e){
            System.out.println("Exception in playHitScoreSound()");
        }
        System.out.println("\n\nplayHitScoreSound()");
    }
    public void playStartNewGameSound() {
        //when ball hit the left border or right and score up this method will use and play

        /** we have fbs drop in first playing sound it because of File and Audio and playHitPaddleSound()
         and playing sound method
         FIX THIS
         */

        try {

            this.startNewGameSongFile = new File("sounds/start game sound.wav");
            this.startNewGameSongAIS = AudioSystem.getAudioInputStream(this.startNewGameSongFile.toURI().toURL());
            this.startNewGameSongClip = AudioSystem.getClip();
            this.startNewGameSongClip.open(startNewGameSongAIS);
            this.startNewGameSongClip.start();

        }catch (FileNotFoundException e){
            System.out.println("FileNotFoundException in playHitScoreSound()");
        }catch (Exception e){
            System.out.println("Exception in playStartNewGameSound()");
        }
        System.out.println("\n\nplayStartNewGameSound()");
    }
    public void playWinnerSound() {
        //when somebody wins this method will use and play

        /** BUT PLAY ON AND ON BECAUSE EXECUTE ON ON ON SOMEWHERE
         * P;ACE IT SOMEWHERE CORRECTLY OR USE BOOLEAN OR VARIABLES*/


        if(howManyTimeWinnerPlayed == 0) {

            try {

                this.songWinnerNameFile = new File("sounds/winner sound.wav");
                this.songWinnerNameAIS = AudioSystem.getAudioInputStream(this.songWinnerNameFile.toURI().toURL());
                this.songWinnerNameClip = AudioSystem.getClip();
                this.songWinnerNameClip.open(songWinnerNameAIS);
                this.songWinnerNameClip.start();
                howManyTimeWinnerPlayed++;

            } catch (FileNotFoundException e) {
                System.out.println("FileNotFoundException in playHitScoreSound()");
            } catch (Exception e) {
                System.out.println("Exception in playWinnerSound()");
            }
            System.out.println("\n\nplayWinnerSound()");
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(0xBBBBBB));
        //middle line
        g.drawLine(GAME_PANEL_WIDTH/2,0,GAME_PANEL_WIDTH/2,GAME_PANEL_HEIGHT);
        //middle circle
        g.drawOval((GAME_PANEL_WIDTH/2 - (middleCircleDiameter / 2)),(GAME_PANEL_HEIGHT/2 - (middleCircleDiameter / 2)),middleCircleDiameter,middleCircleDiameter);

        g.setColor(new Color(0xD90429));
        //left bound
        g.drawLine(GAME_PANEL_WIDTH - borderGapFrom,0,GAME_PANEL_WIDTH - borderGapFrom ,GAME_PANEL_HEIGHT);
        //right bound
        g.setColor(new Color(0x3D65B2));
        g.drawLine(borderGapFrom,0,borderGapFrom ,GAME_PANEL_HEIGHT);

        draw(g);
    }

    public void draw(Graphics g){
        paddleL.draw(g);
        paddleR.draw(g);
        scoreL.draw(g);
        scoreR.draw(g);

        //because we don't want to have extra objects in ball class fields so we get it as parameter
        //to use its color
        ball.draw(g,paddleTouched,paddleR,paddleL);//this draw method because have change and we don't want
        //to have extra objects in ball class so we transfer that by parameter to ball class


        //when someone wins :
        //here draw the conclusion
        if (paddleR.isItWinner){
            scoreR.drawWinnerInfo(g);
        } else if (paddleL.isItWinner) {
            scoreL.drawWinnerInfo(g);
        }

    }
}
