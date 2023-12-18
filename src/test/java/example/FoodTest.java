package example;

import example.Model.GameObjects.Food;
import example.Model.Utilities.ImageUtil;
import example.Model.GameObjects.Snake;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {
    // Initialize JavaFX toolkit
    JFXPanel jfxPanel = new JFXPanel();

    @Test
    void foodEatenIncreasesSnakeLengthAndScore() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            Snake snake = new Snake(initialX, initialY, 5);
            Food food = new Food();

            // Manually set the position of the snake and food for testing
            food.xPosition = 100;
            food.yPosition = 100;

            int initialLength = snake.getLength();
            int initialScore = snake.score;

            // Simulate food being eaten
            food.eaten(snake);

            // Check if the food is marked as not live
            assertFalse(food.liveOfObject);

            // Check if the snake length and score are increased
            assertEquals(initialLength + 1, snake.getLength());
            assertEquals(initialScore + 1, snake.score);

        });
    }

    @Test
    void foodNotEatenWhenSnakeNotIntersecting() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            Snake snake = new Snake(initialX, initialY, 5);
            Food food = new Food();

            // Manually set the position of the snake and food for testing
            snake.xPosition = 200;
            snake.yPosition = 200;
            food.xPosition = 300;
            food.yPosition = 300;

            int initialLength = snake.getLength();
            int initialScore = snake.score;

            // Simulate food not being eaten
            food.eaten(snake);

            // Check if the food is still live
            assertTrue(food.liveOfObject);

            // Check if the snake length and score are not changed
            assertEquals(initialLength, snake.getLength());
            assertEquals(initialScore, snake.score);

        });
    }
}