package example;

import example.Model.GameObjects.Food;
import example.Model.GameObjects.Snake;
import example.Model.Utilities.ImageUtil;
import example.Model.GameObjects.PowerUps.PowerUp;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.canvas.Canvas;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PowerUpTest {
    // Initialize JavaFX toolkit
    JFXPanel jfxPanel = new JFXPanel();

    private class TestPowerUp extends PowerUp {

        public TestPowerUp(int r) {
            super(r);
        }

        @Override
        public void eaten(Snake snake, Canvas gameCanvas) {
            // Provide an implementation for the abstract method for testing
        }
    }

    @Test
    void checkObjectPosition_RegeneratesPositionWhenOverlappingWithFood() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            Food food = new Food();
            TestPowerUp powerUp = new TestPowerUp(1);

            // Set the positions to be very close to each other
            powerUp.xPosition = food.xPosition;
            powerUp.yPosition = food.yPosition;

            // Check if positions are initially overlapping
            assertTrue(Math.abs(powerUp.xPosition - food.xPosition) < 10);
            assertTrue(Math.abs(powerUp.yPosition - food.yPosition) < 10);

            // Call the method to regenerate positions
            powerUp.checkObjectPosition(food);

            // Check if positions are no longer overlapping
            assertTrue(Math.abs(powerUp.xPosition - food.xPosition) >= 10);
            assertTrue(Math.abs(powerUp.yPosition - food.yPosition) >= 10);
        });
    }

    @Test
    void handleSnakeTouch_PowerUpIncreaseSnakeLengthAndScore() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            Snake snake = new Snake(100, 100, 5);
            TestPowerUp powerUp = new TestPowerUp(1);
            Canvas gameCanvas = new Canvas(800, 500);

            int initialLength = snake.getLength();
            int initialScore = snake.score;

            // Set the positions to overlap
            powerUp.xPosition = 100;
            powerUp.yPosition = 100;

            // Call the method to handle snake touch
            powerUp.handleSnakeTouch(snake, gameCanvas);

            // Check if the power-up is marked as not live
            assertFalse(powerUp.liveOfObject);

            // Check if the snake length and score are increased
            assertEquals(initialLength + 1, snake.getLength());
            assertEquals(initialScore + 1, snake.score);

        });
    }

}