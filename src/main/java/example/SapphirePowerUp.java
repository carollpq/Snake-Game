package example;

import javafx.scene.canvas.Canvas;

public class SapphirePowerUp extends PowerUp {
    public SapphirePowerUp(int r) {
        super(r);
        setScoreImplement(10);
    }

    @Override
    public void eaten(MySnake mySnake, Canvas gameCanvas) {
        boolean status = handleSnakeTouch(mySnake,gameCanvas);
        if (status) displayBonusMessage("+10 points! WOO!!", this.xPosition, this.yPosition, gameCanvas);
    }

}
