import java.awt.*;

public class Paddle extends Rectangle {
    private int x ;
    private int y = 400;
    private int speed = 10;

    char position  ;//'R' , 'L'
    public int gap = 8 ;
    private int panelWidth  ;
    private int panelHeight  ;
    private final int PADDLE_WIDTH = 25;
    private final int PADDLE_HEIGHT = PADDLE_WIDTH * 4;
    //Graphics g;
    public Paddle(char position,int panelWidth,int panelHeight) {
        //this.y = (panelHeight / 2) - (panelHeight/ 2);
        this.y = (panelHeight / 2) - (PADDLE_HEIGHT / 2);
        this.position = position;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;

        if(position == 'R'){
            //this.x = panelWidth - panelWidth- gap  ;
            this.x = panelWidth - PADDLE_WIDTH - gap  ;
            this.setLocation(new Point(this.x ,this.y));
            this.setBounds(new Rectangle(PADDLE_WIDTH,PADDLE_HEIGHT));

        }
        else if (position == 'L'){
            this.x = 8 ;
            this.setLocation(new Point(gap,this.y));
            this.setBounds(new Rectangle(PADDLE_WIDTH,PADDLE_HEIGHT));
        }

    }
    public void goUp(){
        if (y <= 0){
            y = 0;
        }else
            y -= speed;
    }
    public void goDown(){
        if (y >= panelHeight - PADDLE_HEIGHT){
            y = panelHeight - PADDLE_HEIGHT;
        }else
            y += speed;
    }


    public void draw(Graphics g){
        if (position == 'L'){
            g.setColor(new Color(0x1976D3));
        }else{
            g.setColor(new Color(0xD90429));

        }
        g.fillRect(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
    }





}
