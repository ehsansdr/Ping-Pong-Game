import java.awt.event.*;

public class Input extends KeyAdapter {

    private GamePanel gamePanel;

    public Input(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        //System.out.println("Input");
    }

    @Override
    public void keyPressed(KeyEvent e)  {
        //this class help us to move snake in panel

        switch (e.getKeyCode()){

            case KeyEvent.VK_W:
                gamePanel.paddleL.goUp = true;
                break;
            case KeyEvent.VK_S:
                gamePanel.paddleL.goDown = true;
                break;
            case KeyEvent.VK_UP :
                gamePanel.paddleR.goUp = true;
                break;

            case KeyEvent.VK_DOWN :
                gamePanel.paddleR.goDown = true;
                break;

            case KeyEvent.VK_SPACE:
                //we have this if because we want only use t when game is over
                //not in middle of game when it appears to press we allow to press
                if (!gamePanel.gameRunning)
                    try {
                        gamePanel.newGame();
                    }catch (Exception E){
                        System.out.println("input class get exception");
                    }
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        //this class help us to move snake in panel

        switch (e.getKeyCode()){

            case KeyEvent.VK_W:
                gamePanel.paddleL.goUp = false;
                break;
            case KeyEvent.VK_S:
                gamePanel.paddleL.goDown= false;
                break;
            case KeyEvent.VK_UP :
                gamePanel.paddleR.goUp = false;
                break;

            case KeyEvent.VK_DOWN :
                gamePanel.paddleR.goDown = false;
                break;
        }
    }

}
