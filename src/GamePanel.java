import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    boolean gameRunning;
    public final int GAME_PANEL_WIDTH = 900;
    public final int GAME_PANEL_HEIGHT =  550;//(int) GAME_PANEL_WIDTH * (5/9)
    Color panelColor = new Color(0x14213D);
    Input inputController;
    public Paddle paddleR ;
    public Paddle paddleL;
    private Graphics g;
    public Score scoreR ;
    public Score scoreL ;
    private Ball ball;
    public Physics physicsRules;

    public char paddleTouched = 'N';//because feature when ball hit the red paddle repaint to red and when
    //hit blue return to blue color
    //and in beginning because it doesn't have any hit instead of null we have 'N' it means : no hit yet
    //we declare it for that and for using in ball draw() parameter

    int middleCircleDiameter = 240;

    public int borderGapFrom = 8;

    public GamePanel() {
        newGame();

        this.setPreferredSize(new Dimension(GAME_PANEL_WIDTH,GAME_PANEL_HEIGHT));
        this.setBackground(panelColor);
        this.setLayout(null);
        //this.add(paddleL); we don't use add fo rectangle subclass we use draw
        //this.add(paddleR);

        //setFocusable(true); is essential to have key listener
        this.setFocusable(true);/*****************************/
        this.addKeyListener(inputController);

    }
    public void newGame(){
        gameRunning = true;
        objectCreation();
    }

    private void objectCreation() {
        paddleL = new Paddle('L',GAME_PANEL_WIDTH,GAME_PANEL_HEIGHT);
        paddleR = new Paddle('R',GAME_PANEL_WIDTH,GAME_PANEL_HEIGHT);
        scoreL = new Score(paddleL);
        scoreR = new Score(paddleR);
        ball = new Ball();
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
        if(scoreL.score == 2){
            scoreL.score = 0;
            scoreL.set++;
            scoreR.score = 0;
            //newRound();
        }
        if(scoreR.score == 2){
            scoreR.score = 0;
            scoreR.set++;
            scoreL.score = 0;
            //newRound();
        }

        //for winner detecting these ifs do them
        if (scoreL.set == 2 ){
            paddleL.isItWinner =true;
            gameRunning = false;
        }else if (scoreR.set == 2 ){
            paddleR.isItWinner =true;
            gameRunning = false;
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
