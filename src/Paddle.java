import java.awt.*;

public class Paddle extends Rectangle {
    public int x ;
    public int y = 400;
    private int speed = 5;
    boolean isItWinner = false;//we declare mode that use when the player wins by this boolean and
    //we check and manipulate in another class so we declare it public
    public char position  ;//'R' , 'L'
    public int gap = 8 ;
    public int panelWidth;
    public int panelHeight;
    //we declare them as public becuase I use them in drawWinnerInfo in Score class
    public Color colorOfPaddle;


    /**
     * because we can not move all paddles at the same time we use boolean
     * at the time we can only receive one key signal in keyboard
     * but we can manipulate lots of boolean at the same time
     * so we move paddle  indirectly by key change boolean and boolean execute method
     * and input not execute method directly it changes boolean by releasing and pressing
     * 120 fbs is excute in loop
     * */
    public boolean goUp ;
    public boolean goDown ;
    public final int PADDLE_WIDTH = 25;
    public final int PADDLE_HEIGHT = PADDLE_WIDTH * 4;
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
            colorOfPaddle = new Color(0xB60015);

        }
        else if (position == 'L'){
            this.x = 8 ;
            this.setLocation(new Point(gap,this.y));
            this.setBounds(new Rectangle(PADDLE_WIDTH,PADDLE_HEIGHT));
            colorOfPaddle = new Color(0x1976D3);
        }

    }
    public void move(){
        if (goUp){
            goUp();
        }
        if (goDown){
            goDown();
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
        g.setColor(colorOfPaddle);
        g.fillRect(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
    }





}
