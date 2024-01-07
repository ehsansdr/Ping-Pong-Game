import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final int GAME_PANEL_WIDTH = 900;
    private final int GAME_PANEL_HEIGHT =  550;//(int) GAME_PANEL_WIDTH * (5/9)
    Color panelColor = new Color(0x14213D);
    Input inputController;
    int middleCircleDiameter = 240;

    int borderGapFrom = 10;

    public GamePanel() {
        inputController = new Input(this);
        this.setPreferredSize(new Dimension(GAME_PANEL_WIDTH,GAME_PANEL_HEIGHT));
        this.setBackground(panelColor);

        //setFocusable(true); is essential to have key listener
        this.setFocusable(true);/*****************************/
        this.addKeyListener(inputController);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(0xBBBBBB));
        //middle line
        g.drawLine(GAME_PANEL_WIDTH/2,0,GAME_PANEL_WIDTH/2,GAME_PANEL_HEIGHT);
        //middle circle
        g.drawOval((GAME_PANEL_WIDTH/2 - (middleCircleDiameter / 2)),(GAME_PANEL_HEIGHT/2 - (middleCircleDiameter / 2)),middleCircleDiameter,middleCircleDiameter);

        g.setColor(new Color(0xD90429));
        //left bound
        g.drawLine(GAME_PANEL_WIDTH - borderGapFrom,0,GAME_PANEL_WIDTH - borderGapFrom ,GAME_PANEL_HEIGHT);
        //right bound
        g.drawLine(borderGapFrom,0,borderGapFrom ,GAME_PANEL_HEIGHT);
    }

}
