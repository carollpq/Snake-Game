package com.SnakeGame.Controller;

import com.SnakeGame.StartFrameMain;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;


/**
 * Controller class for the Enter Name frame.
 * Extends StartFrameController to inherit common functionality.
 */
public class EnterNameFrameController extends StartFrameController {

    @FXML
    private TextField playerName;

    /**
     * Validates the entered player name and proceeds to the game controls if the name is valid.
     *
     * @throws IOException if an error occurs during the transition to the game controls.
     */
    @FXML
    public void checkPlayerName() throws IOException {
        // Validation logic for player name TextField
        String enteredName = playerName.getText();

        if (enteredName.isEmpty()) {
            // Display an error message
            showAlert();
            playerName.requestFocus(); // Set focus on the TextField
        } else {
            StartFrameMain.setPlayerName(enteredName);
            switchToGameControls();
        }
    }

    /**
     * Helper method to display an error alert when the player name is empty.
     */
    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Player name cannot be empty.");
        alert.showAndWait();
    }
}
