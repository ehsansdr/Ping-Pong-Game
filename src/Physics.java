public class Physics {
    //this class spesaily used for have physics rules in game

    GamePanel gamePanel;
    Paddle paddleR;
    Paddle paddleL;
    Ball ball;

    public Physics(GamePanel gamePanel, Paddle paddleR, Paddle paddleL, Ball ball) {
        this.gamePanel = gamePanel;
        this.paddleR = paddleR;
        this.paddleL = paddleL;
        this.ball = ball;
    }

    public void collisionChecking(){


        ///we didn't need to check and score up when game in not running
        if (gamePanel.gameRunning) {
            int bottomOfPaddleR = paddleR.y + paddleR.PADDLE_HEIGHT;
            int bottomOfPaddleL = paddleL.y + paddleL.PADDLE_HEIGHT;

            if (ball.y + ball.diameter >= gamePanel.GAME_PANEL_HEIGHT) {
                ball.yDirect *= -1;
                System.out.println("ball hit the bottom of frame");
            }
            if (ball.y == 0) {
                ball.yDirect *= -1;
                System.out.println("ball hit the upper of frame");
            }
            if (ball.x + ball.diameter >= paddleR.x &&
                    ball.y + ball.diameter > paddleR.y && // it is necessary to NOT have = ,just < or >
                    ball.y < bottomOfPaddleR) {// it is necessary to NOT have = ,just < or >

                ball.xDirect *= -1;
                gamePanel.paddleTouched = 'R';//so when collision happened we should change the history of hitting
                //so paddleTouched store last hit to help us to have correct color
                System.out.println("Right paddle hit");
            }
            if (ball.x <= paddleL.x + paddleL.PADDLE_WIDTH &&
                    ball.y + ball.diameter > paddleL.y &&
                    ball.y < bottomOfPaddleL) {
                ball.xDirect *= -1;
                gamePanel.paddleTouched = 'L';//so when collision happened we should change the history of hitting
                //so paddleTouched store last hit to help us to have correct color
                System.out.println("Left paddle hit");
            }


            //for hitting top of paddle left
            /**         OK              */
            if (ball.x < paddleL.x + paddleL.PADDLE_WIDTH){
                //System.out.println("\n OUTER OF IF EX");

                /*               OK            */
                if (ball.y + ball.diameter == paddleL.y) {
                    //top edge of paddle left check in this if

                    ball.yDirect = -1;
                    System.out.println("\nTOP EDGE OF PaddleL ");
                    gamePanel.paddleTouched = 'L';//so when collision happened we should change the history of hitting
                    //so paddleTouched store last hit to help us to have correct color
                    }

                /**                  OK             */
                if (ball.y == paddleL.y + paddleL.PADDLE_HEIGHT) {

                    //down edge of paddle left check in this if
                    ball.yDirect = 1;
                    System.out.println("\nDOWN EDGE OF PaddleL " );
                    gamePanel.paddleTouched = 'L';//so when collision happened we should change the history of hitting
                    //so paddleTouched store last hit to help us to have correct color
                    }
                }
//
//            //for hitting top of paddle right
//            if (ball.x + ball.diameter >= paddleR.x){
//                //System.out.println("\n OUTER OF IF EX");
//
//                /*               OK            */
//                if (ball.y  + ball.diameter == paddleL.y) {
//                    //top edge of paddle left check in this if
//
//                    ball.yDirect = -1;
//                    System.out.println("TOP EDGE OF PaddleR ");
//                    gamePanel.paddleTouched = 'R';//so when collision happened we should change the history of hitting
//                    //so paddleTouched store last hit to help us to have correct color
//                    }
//
//                /**                  OK             */
//                if (ball.y  == paddleL.y + paddleL.PADDLE_HEIGHT) {
//
//                    //down edge of paddle left check in this if
//                    ball.yDirect = 1;
//                    System.out.println("DOWN EDGE OF PaddleR " );
//                    gamePanel.paddleTouched = 'R';//so when collision happened we should change the history of hitting
//                    //so paddleTouched store last hit to help us to have correct color
//                    }
//                }
            if (ball.x < 0) {
                ball.xDirect *= -1;
                gamePanel.scoreR.scoreUp();

                System.out.println("ball passed the left border");
            }
            if (ball.x > gamePanel.GAME_PANEL_WIDTH - ball.diameter) {
                ball.xDirect *= -1;
                gamePanel.scoreL.scoreUp();
                System.out.println("ball passed the right border");
            }
        }
    }


}
