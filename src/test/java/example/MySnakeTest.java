package example;

import example.Model.Utilities.ImageUtil;
import example.Model.SnakeObjects.MySnake;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MySnakeTest {

    // Initialize JavaFX toolkit
    JFXPanel jfxPanel = new JFXPanel();
    @Test
    void snakeMovesRightForRightControls() {
        // Use Platform.runLater to ensure the test method runs on the JavaFX Application Thread
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            MySnake mySnake = new MySnake(initialX, initialY, 5);
            // Create a KeyEvent to simulate a key press
            KeyEvent rightPress = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false, false, false, false);
            mySnake.handleKeyPressRight(rightPress);
            mySnake.move();
            assertEquals(105, mySnake.xPosition);
        });
    }

    @Test
    void snakeMovesUpForRightControls() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            MySnake mySnake = new MySnake(initialX, initialY, 5);
            KeyEvent upPress = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.UP, false, false, false, false);
            mySnake.handleKeyPressRight(upPress);
            mySnake.move();
            assertEquals(95, mySnake.yPosition);
        });
    }

    @Test
    void snakeMovesDownForRightControls() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            MySnake mySnake = new MySnake(initialX, initialY, 5);
            KeyEvent downPress = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.DOWN, false, false, false, false);
            mySnake.handleKeyPressRight(downPress);
            mySnake.move();
            assertEquals(105, mySnake.yPosition);
        });
    }

    @Test
    void snakeMovesLeftForRightControls() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            MySnake mySnake = new MySnake(initialX, initialY, 5);
            KeyEvent leftPress = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.LEFT, false, false, false, false);
            mySnake.handleKeyPressRight(leftPress);
            mySnake.move();
            assertEquals(95, mySnake.xPosition);
        });
    }

    @Test
    void snakeMovesRightForLeftControls() {
        // Use Platform.runLater to ensure the test method runs on the JavaFX Application Thread
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            MySnake mySnake = new MySnake(initialX, initialY, 5);
            // Create a KeyEvent to simulate a key press
            KeyEvent rightPress = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.D, false, false, false, false);
            mySnake.handleKeyPressRight(rightPress);
            mySnake.move();
            assertEquals(105, mySnake.xPosition);
        });
    }

    @Test
    void snakeMovesUpForLeftControls() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            MySnake mySnake = new MySnake(initialX, initialY, 5);
            KeyEvent upPress = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.W, false, false, false, false);
            mySnake.handleKeyPressRight(upPress);
            mySnake.move();
            assertEquals(95, mySnake.yPosition);
        });
    }

    @Test
    void snakeMovesDownForLeftControls() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            MySnake mySnake = new MySnake(initialX, initialY, 5);
            KeyEvent downPress = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.S, false, false, false, false);
            mySnake.handleKeyPressRight(downPress);
            mySnake.move();
            assertEquals(105, mySnake.yPosition);
        });
    }

    @Test
    void snakeMovesLeftForLeftControls() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            MySnake mySnake = new MySnake(initialX, initialY, 5);
            KeyEvent leftPress = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.A, false, false, false, false);
            mySnake.handleKeyPressRight(leftPress);
            mySnake.move();
            assertEquals(95, mySnake.xPosition);
        });
    }

    @Test
    void snakeEatsBody() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 100;
            int initialX = 100;
            MySnake mySnake = new MySnake(initialX, initialY, 5);
            mySnake.length = 3; // Set snake length to 3 for testing
            MySnake.bodyPoints.add(new Point(100, 110));
            MySnake.bodyPoints.add(new Point(100, 120));
            MySnake.bodyPoints.add(new Point(100, 130));
            mySnake.eatBody();
            assertFalse(mySnake.liveOfObject); // Snake should die as it eats itself
        });
    }

    @Test
    void snakeDiesWhenOutOfBounds() {
        Platform.runLater(() -> {
            ImageUtil.initializeImages();
            int initialY = 0;
            int initialX = 0;
            MySnake mySnake = new MySnake(initialX, initialY, 5);
            mySnake.move();
            assertFalse(mySnake.liveOfObject);
        });
    }
}