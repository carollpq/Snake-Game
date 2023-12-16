package example;

import javafx.scene.canvas.Canvas;

public class GarnetPowerUp extends PowerUp {
    public GarnetPowerUp(int r) {
        super(r);
        setScoreImplement(8);
    }

    @Override
    public void eaten(MySnake mySnake, Canvas gameCanvas) {
        boolean status = handleSnakeTouch(mySnake,gameCanvas);
        if (status) displayBonusMessage("+8 points! Brilliant!", this.xPosition, this.yPosition, gameCanvas);
    }

}
