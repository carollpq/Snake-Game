package example.Model;

import javafx.scene.canvas.Canvas;

/**
 * Represents a Diamond power-up in the game, providing a score bonus when consumed by the snake.
 */
public class DiamondPowerUp extends PowerUp {
    /**
     * Constructs a DiamondPowerUp object with a specific identifier.
     *
     * @param r The identifier for the DiamondPowerUp object.
     */
    public DiamondPowerUp(int r) {
        super(r);
        setScoreImplement(5);
    }

    /**
     * Handles the action when the Diamond power-up is eaten by the snake.
     *
     * @param mySnake    The snake object in the game.
     * @param gameCanvas The Canvas where the game is displayed.
     */
    @Override
    public void eaten(MySnake mySnake, Canvas gameCanvas) {
        boolean status = handleSnakeTouch(mySnake,gameCanvas);
        if (status) displayBonusMessage("+5 points! Good Job!", this.xPosition, this.yPosition, gameCanvas);
    }

}