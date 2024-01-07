import java.awt.event.*;

public class Input extends KeyAdapter {

    private GamePanel gamePanel;

    public Input(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        System.out.println("Input");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //this class help us to move snake in panel

        switch (e.getKeyCode()){

            case KeyEvent.VK_W:
                System.out.println("www");
                break;
            case KeyEvent.VK_S:
                System.out.println("sss");

                ;
        }

        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT :

                break;
            case KeyEvent.VK_LEFT :

                break;
            case KeyEvent.VK_UP :

                break;
            case KeyEvent.VK_DOWN :

                break;
        }
    }
}
