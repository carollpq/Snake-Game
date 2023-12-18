package example.Model;

import example.Model.MySnake;
import example.Model.PowerUp;
import javafx.scene.canvas.Canvas;

/**
 * Represents a Topaz power-up in the game, providing a score bonus when consumed by the snake.
 */
public class TopazPowerUp extends PowerUp {
    /**
     * Constructs a TopazPowerUp object with a specific identifier.
     *
     * @param r The identifier for the TopazPowerUp object.
     */
    public TopazPowerUp(int r) {
        super(r);
        setScoreImplement(3);
    }

    /**
     * Handles the action when the Topaz power-up is eaten by the snake.
     *
     * @param mySnake    The snake object in the game.
     * @param gameCanvas The Canvas where the game is displayed.
     */
    @Override
    public void eaten(MySnake mySnake, Canvas gameCanvas) {
        boolean status = handleSnakeTouch(mySnake,gameCanvas);
        if (status) displayBonusMessage("+3 points! Yippee!", this.xPosition, this.yPosition, gameCanvas);
    }

}
