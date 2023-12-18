package example.Model;

import example.Model.MySnake;
import example.Model.PowerUp;
import javafx.scene.canvas.Canvas;

public class TopazPowerUp extends PowerUp {
    public TopazPowerUp(int r) {
        super(r);
        setScoreImplement(3);
    }

    @Override
    public void eaten(MySnake mySnake, Canvas gameCanvas) {
        boolean status = handleSnakeTouch(mySnake,gameCanvas);
        if (status) displayBonusMessage("+3 points! Yippee!", this.xPosition, this.yPosition, gameCanvas);
    }

}
