package com.SnakeGame;

import com.SnakeGame.Model.GameObjects.Food;
import com.SnakeGame.Model.Utilities.ImageUtil;
import com.SnakeGame.Model.GameObjects.Snake;
import javafx.application.Platform;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    @Test
    void foodEatenIncreasesSnakeLengthAndScore() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            Snake snake = new Snake(initialX, initialY, 5);
            Food food = new Food();

            // Manually set the position of the snake and food for testing
            food.setXPosition(100);
            food.setYPosition(100);

            int initialLength = snake.getLength();
            int initialScore = snake.score;

            // Simulate food being eaten
            food.eaten(snake);

            // Check if the food is marked as not live
            assertFalse(food.isLiveOfObject());

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
            snake.setXPosition(200);
            snake.setYPosition(200);
            food.setXPosition(300);
            food.setYPosition(300);

            int initialLength = snake.getLength();
            int initialScore = snake.score;

            // Simulate food not being eaten
            food.eaten(snake);

            // Check if the food is still live
            assertTrue(food.isLiveOfObject());

            // Check if the snake length and score are not changed
            assertEquals(initialLength, snake.getLength());
            assertEquals(initialScore, snake.score);

        });
    }
}