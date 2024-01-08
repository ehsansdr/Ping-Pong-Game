import java.awt.*;

public class Score extends Rectangle {
    public char id ;//'L' or 'R'
    public int score = 8;
    private int xOfScore;
    private int yOfScore = 50 ;
    private int xOfSet;
    private int yOfSet = 60 ;
    private int widthOfScore = 70;
    private int heigthOfScore = 30 ;

    private int widthOfSet = 30;
    private int heigthOfSet = 40 ;
    private int gapFromMiddle = 20;
    Color color = new Color(0x2C4981);
    Font font = new Font("Consolas",Font.BOLD,60);

    public Score(char id) {
        this.id = id;
        if (id == 'R'){
            xOfScore = (900 / 2) + gapFromMiddle;
            xOfSet = xOfScore - widthOfScore;
        }else if (id == 'L'){
            xOfScore = (900 / 2) - font.getSize() - gapFromMiddle;//we use font.getSize() becuase we can define size
            //or get sze of our string
            xOfSet = xOfScore - widthOfScore;
        }
    }


    public void draw(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        if (id == 'R'){
            g.drawString(score / 10 +""+ score +"" ,xOfScore,yOfScore);
        }else if (id == 'L'){
            g.drawString(score / 10 +""+ score +"" ,xOfScore,yOfScore);
        }
    }
}
