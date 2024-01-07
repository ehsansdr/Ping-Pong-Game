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
                gamePanel.paddleL.goUp();
                break;
            case KeyEvent.VK_S:
                gamePanel.paddleL.goDown();
                break;
            case KeyEvent.VK_UP :
                gamePanel.paddleR.goUp();
                break;

            case KeyEvent.VK_DOWN :
                gamePanel.paddleR.goDown();
                break;
        }
    }
}
