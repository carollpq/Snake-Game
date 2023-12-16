package example.Controller;

import example.StartFrameMain;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EnterNameFrameController extends StartFrameController {

    @FXML
    private TextField playerName;

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

    // Helper method to show an alert
    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Player name cannot be empty.");
        alert.showAndWait();
    }
}
