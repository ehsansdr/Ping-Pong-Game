import java.awt.*;

public class Ball extends Rectangle {
    int diameter = 20;

    public Ball() {

    }

    public void draw(Graphics g){
        g.setColor(new Color(0xFFFFFF));
        g.fillOval(x,y,diameter,diameter);
    }
}
