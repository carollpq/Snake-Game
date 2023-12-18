package example.Model;

import example.Model.MySnake;
import example.Model.PowerUp;
import javafx.scene.canvas.Canvas;

/**
 * Represents a Sapphire power-up in the game, providing a score bonus when consumed by the snake.
 */
public class SapphirePowerUp extends PowerUp {
    /**
     * Constructs a SapphirePowerUp object with a specific identifier.
     *
     * @param r The identifier for the SapphirePowerUp object.
     */
    public SapphirePowerUp(int r) {
        super(r);
        setScoreImplement(10);
    }

    /**
     * Handles the action when the Sapphire power-up is eaten by the snake.
     *
     * @param mySnake    The snake object in the game.
     * @param gameCanvas The Canvas where the game is displayed.
     */
    @Override
    public void eaten(MySnake mySnake, Canvas gameCanvas) {
        boolean status = handleSnakeTouch(mySnake,gameCanvas);
        if (status) displayBonusMessage("+10 points! WOO!!", this.xPosition, this.yPosition, gameCanvas);
    }

}
