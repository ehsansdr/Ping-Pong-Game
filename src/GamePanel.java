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

    public void collisionChecking(){


        ///we dint need to check and score up when game in not running
        if (gameRunning) {
            int bottomOfPaddleR = paddleR.y + paddleR.PADDLE_HEIGHT;
            int bottomOfPaddleL = paddleL.y + paddleL.PADDLE_HEIGHT;

            if (ball.y + ball.diameter >= GAME_PANEL_HEIGHT) {
                ball.yDirect *= -1;
                System.out.println("ball hit the bottom of frame");
            }
            if (ball.y == 0) {
                ball.yDirect *= -1;
                System.out.println("ball hit the upper of frame");
            }
            if (ball.x + ball.diameter >= paddleR.x &&
                    ball.y + ball.diameter >= paddleR.y &&
                    ball.y <= bottomOfPaddleR) {

                ball.xDirect *= -1;
                paddleTouched = 'R';//so when collision happend we should change the history of hitting
                //so paddleTouched staore last hit to help us to have correct color
                System.out.println("ball hit the Right paddle");
            }
            if (ball.x <= paddleL.x + paddleL.PADDLE_WIDTH &&
                    ball.y + ball.diameter >= paddleL.y &&
                    ball.y <= bottomOfPaddleL) {// NOT OK
                ball.xDirect *= -1;
                paddleTouched = 'L';//so when collision happend we should change the history of hitting
                //so paddleTouched staore last hit to help us to have correct color
                System.out.println("ball hit the left paddle");
            }
            if (ball.x < 0) {
                ball.xDirect *= -1;
                scoreR.scoreUp();

                System.out.println("ball passed the left border");
            }
            if (ball.x > GAME_PANEL_WIDTH - ball.diameter) {
                ball.xDirect *= -1;
                scoreL.scoreUp();
                System.out.println("ball passed the right border");
            }
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
