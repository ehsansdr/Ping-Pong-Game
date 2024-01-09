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
    private Score scoreR ;
    private Score scoreL ;
    private Ball ball;

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
        paddleL.move();
        paddleR.move();
        ball.move();
    }

    public void collisionChecking(){

        int bottomOfPaddleR = paddleR.y + paddleR.PADDLE_HEIGHT;
        int bottomOfPaddleL = paddleL.y + paddleL.PADDLE_HEIGHT;

        if (ball.y + ball.diameter >= GAME_PANEL_HEIGHT){
            ball.yDirect *= -1;
            System.out.println("1 if" );
        }
        if (ball.y == 0){
            ball.yDirect *= -1;
            System.out.println("2 if");
        }
        if (ball.x + ball.diameter >= paddleR.x &&
            ball.y + ball.diameter >= paddleR.y &&
            ball.y <= bottomOfPaddleR){

            ball.xDirect *= -1;
            System.out.println("3 if");
        }
        if (ball.x <= paddleL.x + paddleL.PADDLE_WIDTH &&
            ball.y + ball.diameter >= paddleL.y        &&
            ball.y <= bottomOfPaddleL){// NOT OK
            ball.xDirect *= -1;
            System.out.println("4 if");
        }
        if (ball.x < 0){
            ball.xDirect *= -1;
            scoreR.scoreUp();
            if (scoreR.score == 5){
                scoreR.set++;
                scoreR.score = 0;
                scoreL.score = 0;
            }
            System.out.println("5 if");
        }
        if (ball.x > GAME_PANEL_WIDTH - ball.diameter){
            ball.xDirect *= -1;
            scoreL.scoreUp();
            if (scoreL.score == 5){
                scoreL.set++;
                scoreL.score = 0;
                scoreR.score = 0;
            }

            System.out.println("6 if");
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
        ball.draw(g);
        if (scoreR.score == 1){
            scoreR.drawWinnerInfo(g);
        }else if(scoreL.score == 1){
            scoreL.drawWinnerInfo(g);
        }

    }
}
