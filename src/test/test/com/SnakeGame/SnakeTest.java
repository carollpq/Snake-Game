package com.SnakeGame;

import com.SnakeGame.Model.GameObjects.Snake;
import com.SnakeGame.Model.Utilities.ImageUtil;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {
    //Initialise JFX Toolkit
    JFXPanel jfxpanel = new JFXPanel();
    @Test
    void snakeMovesRightForRightControls() {
        // Use Platform.runLater to ensure the test method runs on the JavaFX Application Thread
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            Snake snake = new Snake(initialX, initialY, 5);
            // Create a KeyEvent to simulate a key press
            KeyEvent rightPress = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false, false, false, false);
            snake.handleKeyPressRight(rightPress);
            snake.move();
            assertEquals(105, snake.getXPosition());
        });
    }

    @Test
    void snakeMovesUpForRightControls() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            Snake snake = new Snake(initialX, initialY, 5);
            KeyEvent upPress = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.UP, false, false, false, false);
            snake.handleKeyPressRight(upPress);
            snake.move();
            assertEquals(95, snake.getYPosition());
        });
    }

    @Test
    void snakeMovesDownForRightControls() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            Snake snake = new Snake(initialX, initialY, 5);
            KeyEvent downPress = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.DOWN, false, false, false, false);
            snake.handleKeyPressRight(downPress);
            snake.move();
            assertEquals(105, snake.getYPosition());
        });
    }

    @Test
    void snakeMovesLeftForRightControls() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            Snake snake = new Snake(initialX, initialY, 5);
            KeyEvent leftPress = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.LEFT, false, false, false, false);
            snake.handleKeyPressRight(leftPress);
            snake.move();
            assertEquals(95, snake.getXPosition());
        });
    }

    @Test
    void snakeMovesRightForLeftControls() {
        // Use Platform.runLater to ensure the test method runs on the JavaFX Application Thread
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            Snake snake = new Snake(initialX, initialY, 5);
            // Create a KeyEvent to simulate a key press
            KeyEvent rightPress = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.D, false, false, false, false);
            snake.handleKeyPressRight(rightPress);
            snake.move();
            assertEquals(105, snake.getXPosition());
        });
    }

    @Test
    void snakeMovesUpForLeftControls() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            Snake snake = new Snake(initialX, initialY, 5);
            KeyEvent upPress = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.W, false, false, false, false);
            snake.handleKeyPressRight(upPress);
            snake.move();
            assertEquals(95, snake.getYPosition());
        });
    }

    @Test
    void snakeMovesDownForLeftControls() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            Snake snake = new Snake(initialX, initialY, 5);
            KeyEvent downPress = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.S, false, false, false, false);
            snake.handleKeyPressRight(downPress);
            snake.move();
            assertEquals(105, snake.getYPosition());
        });
    }

    @Test
    void snakeMovesLeftForLeftControls() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            Snake snake = new Snake(initialX, initialY, 5);
            KeyEvent leftPress = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.A, false, false, false, false);
            snake.handleKeyPressRight(leftPress);
            snake.move();
            assertEquals(95, snake.getXPosition());
        });
    }

    @Test
    void snakeEatsBody() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            Snake snake = new Snake(initialX, initialY, 5);
            snake.changeLength(3); // Set snake length to 3 for testing
            Snake.bodyPoints.add(new Point(100, 110));
            Snake.bodyPoints.add(new Point(100, 120));
            Snake.bodyPoints.add(new Point(100, 130));
            snake.eatBody();
            assertFalse(snake.isLiveOfObject()); // Snake should die as it eats itself
        });
    }

    @Test
    void snakeDiesWhenOutOfBounds() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 0;
            int initialX = 0;
            Snake snake = new Snake(initialX, initialY, 5);
            snake.move();
            assertFalse(snake.isLiveOfObject());
        });
    }
}