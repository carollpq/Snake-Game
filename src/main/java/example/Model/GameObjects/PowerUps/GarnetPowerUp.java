package example.Model.GameObjects.PowerUps;

import example.Model.GameObjects.Snake;
import javafx.scene.canvas.Canvas;

/**
 * Represents a Garnet power-up in the game, providing a score bonus when consumed by the snake.
 */
public class GarnetPowerUp extends PowerUp {
    /**
     * Constructs a GarnetPowerUp object with a specific identifier.
     *
     * @param r The identifier for the GarnetPowerUp object.
     */
    public GarnetPowerUp(int r) {
        super(r);
        setScoreImplement(8);
    }

    /**
     * Handles the action when the Garnet power-up is eaten by the snake.
     *
     * @param snake    The snake object in the game.
     * @param gameCanvas The Canvas where the game is displayed.
     */
    @Override
    public void eaten(Snake snake, Canvas gameCanvas) {
        boolean status = handleSnakeTouch(snake,gameCanvas);
        if (status) displayBonusMessage("+8 points! Brilliant!", this.xPosition, this.yPosition, gameCanvas);
    }

}
