import javax.swing.*;
import java.awt.*;
import java.util.jar.JarFile;

public class GameFrame extends JFrame {
    GamePanel gamePanel;



    public GameFrame(){

        gamePanel = new GamePanel();

        this.setTitle("ping pong game");
        this.add(gamePanel);
        this.setResizable(true);
        this.pack();
        this.setLocationRelativeTo(null);//it is important to have it in last it is essential
        this.setVisible(true);
    }
}
