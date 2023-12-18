package com.SnakeGame.Model.GameObjects.PowerUps;

import com.SnakeGame.Model.GameObjects.Snake;
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
     * @param snake    The snake object in the game.
     * @param gameCanvas The Canvas where the game is displayed.
     */
    @Override
    public void eaten(Snake snake, Canvas gameCanvas) {
        boolean status = handleSnakeTouch(snake,gameCanvas);
        if (status) displayBonusMessage("+10 points! WOO!!", getXPosition(), getYPosition(), gameCanvas);
    }

}
