package example.Model.GameObjects.PowerUps;

import example.Model.GameObjects.Snake;
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
     * @param snake    The snake object in the game.
     * @param gameCanvas The Canvas where the game is displayed.
     */
    @Override
    public void eaten(Snake snake, Canvas gameCanvas) {
        boolean status = handleSnakeTouch(snake,gameCanvas);
        if (status) displayBonusMessage("+3 points! Yippee!", getXPosition(), getYPosition(), gameCanvas);
    }
}
