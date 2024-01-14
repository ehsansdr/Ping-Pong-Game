import javax.swing.*;
import java.awt.*;
import java.util.jar.JarFile;

public class GameFrame extends JFrame {
    GamePanel gamePanel;
    public Paddle paddleR ;
    public Paddle paddleL;
    ImageIcon FrameGameIcon = new ImageIcon("FrameGameIcon.png");


    public GameFrame(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        this.setTitle("ping pong game");
        this.setIconImage(FrameGameIcon.getImage());
        this.add(this.gamePanel);

        this.setResizable(true);
        this.pack();
        this.setLocationRelativeTo(null);//it is important to have it in last it is essential
        this.setVisible(true);
    }
}
