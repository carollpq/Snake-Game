package example.Model;

import javafx.scene.canvas.Canvas;

public class DiamondPowerUp extends PowerUp {
    public DiamondPowerUp(int r) {
        super(r);
        setScoreImplement(5);
    }

    @Override
    public void eaten(MySnake mySnake, Canvas gameCanvas) {
        boolean status = handleSnakeTouch(mySnake,gameCanvas);
        if (status) displayBonusMessage("+5 points! Good Job!", this.xPosition, this.yPosition, gameCanvas);
    }

}
