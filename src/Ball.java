import java.awt.*;

public class Ball extends Rectangle {
    int diameter = 25;
    int x = 400;
    int y = 300;
    int speed = 5 ;
    int xDirect = 1;
    int yDirect = -1;

    public Ball() {

    }


    public void move(){
        x += speed * xDirect;
        //y += speed * yDirect;
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
