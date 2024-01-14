import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {
    int diameter = 25;
    //this comments are added for checking
    // for checking  edges left paddle 180 50  -1 +1 ,,,, 130 450  -1 -1 it  for checking physics rules
    // for checking  edges right (830 to 812) 380  1 -1 ,,,, (802) 145  1 1 it  for checking physics rules
    int x = 830;
    int y = 380;
    int speed;/** BE CAREFUL SPEED OF BALL CHANGE IN newBall() method to*/
    int xDirect = 1;
    int yDirect = -1;

    char lastRoundWinner;//'R' or 'L' or null
    //this var is we want to set ball in fair part and fair direction and
    //control the ball spawn in new round

    private Random random;

    public Ball() {
        random = new Random();
    }

    public void newBall(){
        //because in physics hit we may speed up the ball after each paddle hit we should reset that
        this.speed = 5;

        /**we added feature the program check if right ball pass left border spawn left
         * and go to opposite direction to have more fair play*/
        if (lastRoundWinner == 'R') {
            this.x = random.nextInt(250) + 200;
            this.y = random.nextInt(350) + 150;
        }else if(lastRoundWinner == 'L'){
            this.x = random.nextInt(250) + 450;
            this.y = random.nextInt(350) + 150;
        }else {
            this.x = random.nextInt(500) + 250;
            this.y = random.nextInt(350) + 150;
        }

        //this if and else prevent to spawn and direction of ball to spawn and hit very close
        //to border it check for example if we want to spawn the ball in left field shout ball to the right
        if (this.x < 450){
            xDirect = 1;
        }else {
            xDirect = -1;
        }

    }


    public void move(){
        x += speed * xDirect;
        y += speed * yDirect;
    }


    public void draw(Graphics g,char paddleTouched,Paddle paddleR,Paddle paddleL){
        //because we don't want to have extra objects in ball class fields so we get it as parameter
        //to use its color


        //we don't get color as rgb or fields because we may change the color in future so we get paddle
        //and its color and don't abbreviation of color because it would change so we have it as paddle
        //name it is not change
        if (paddleTouched == 'N') {
            g.setColor(new Color(0xFFFFFF));
            g.fillOval(x, y, diameter, diameter);
        }else if (paddleTouched == 'R') {
            //so it seems ball hit the red paddle ,so we should turn it tp red
            g.setColor(paddleR.colorOfPaddle);
            g.fillOval(x, y, diameter, diameter);
        } else if (paddleTouched == 'L') {
            //so it seems ball hit the blue paddle ,so we should turn it to red
            g.setColor(paddleL.colorOfPaddle);
            g.fillOval(x, y, diameter, diameter);
        }
    }
}
