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


    public void draw(Graphics g){
        g.setColor(new Color(0xFFFFFF));
        g.fillOval(x,y,diameter,diameter);
    }
}
