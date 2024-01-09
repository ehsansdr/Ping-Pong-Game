import java.awt.*;

public class Score extends Rectangle {
    public char id ;//'L' or 'R'
    public int score ;
    public int set ;
    private int xOfScore;
    private int yOfScore = 50 ;
    private int xOfSet;
    private int yOfSet = 38 ;
    private int widthOfScore = 70;
    private int heigthOfScore = 30 ;

    private int widthOfSet = 30;
    private int heigthOfSet = 40 ;
    private int gapFromMiddle = 20;
    private int gapBetwenScoreAndSet = 20;
    Color colorOfScore;
    Color colorOfSet;
    Font fontOfScore = new Font("Consolas",Font.BOLD,60);
    Font fontOfSet = new Font("Consolas",Font.BOLD,25);

    public Score(Paddle paddle) {
        this.id = paddle.position;
        if (id == 'R'){
            colorOfScore = paddle.colorOfPaddle;
            colorOfSet = new Color(0x91021B);
            xOfScore = (900 / 2) + gapFromMiddle;
            xOfSet = xOfScore + fontOfScore.getSize() + gapBetwenScoreAndSet;
        }else if (id == 'L'){
            colorOfScore = paddle.colorOfPaddle;
            colorOfSet = new Color(0x2B4B83);
            xOfScore = (900 / 2) - fontOfScore.getSize() - gapFromMiddle;//we use fontOfScore.getSize() becuase we can define size
            //or get sze of our string
            xOfSet = (900 / 2) - gapFromMiddle - fontOfScore.getSize() - gapBetwenScoreAndSet - 10;
        }
    }

    public void scoreUp(){
        score++;
        System.out.println(this + " gain score");
    }




    public void draw(Graphics g) {
        if (id == 'R'){
            g.setColor(colorOfScore);
            g.setFont(fontOfScore);
            g.drawString(score / 10 +""+ score +"" ,xOfScore,yOfScore);

            g.setColor(colorOfSet);
            g.setFont(fontOfSet);
            g.drawString( set +"" ,xOfSet,yOfSet);
        }else if (id == 'L'){
            g.setColor(colorOfScore);
            g.setFont(fontOfScore);
            g.drawString(score / 10 +""+ score +"" ,xOfScore,yOfScore);

            g.setColor(colorOfSet);
            g.setFont(fontOfSet);
            g.drawString( set +"" ,xOfSet,yOfSet);
        }

    }

    public void drawWinnerInfo(Graphics g){
        g.setColor(this.colorOfScore);//we get color from object not its parameter
        g.setFont(new Font("Agency FB",Font.BOLD,60));
        if (this.id == 'R'){
            g.drawString("RED  WINS", 350,120);//these numbers are checked by experimenting many times
        }else {
            g.drawString("BLUE WINS", 343,120);//these numbers are checked by experimenting many times
        }

        //showing press space to continue
        g.setColor(new Color(0xFCFCFC));
        g.setFont(new Font("Agency FB",Font.BOLD,20));
        g.drawString("Press Space To new game", 373,148);

    }
}
