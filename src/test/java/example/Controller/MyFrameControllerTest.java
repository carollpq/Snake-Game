package example.Controller;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MyFrameControllerTest {

    // Initialize JavaFX toolkit
    JFXPanel jfxPanel = new JFXPanel();

    @Test
    void checkWhetherGamePausedAfterPressingP_ResumesAfterPressingAgain() {
        Platform.runLater(() -> {
            MyFrameController controller = new MyFrameController();
            KeyEvent pPress = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.P, false, false, false, false);
            //Simulating pausing the game
            try {
                controller.handleKeyPress(pPress);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // Verify that pause is set to true
            assertTrue(MyFrameController.pause);

            //Simulating resuming the game
            try {
                controller.handleKeyPress(pPress);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Verify that pause is set to false
            assertFalse(MyFrameController.pause);
        });
    }
}