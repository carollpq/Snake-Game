package example.Controller;

import example.Model.HighScoreManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HighScoreFrameController extends StartFrameController {
    @FXML //High score labels
    private Label easyHighScore, mediumHighScore, hardHighScore;
    @FXML
    public void initialize() {
        displayHighScores();
    }

    private void displayHighScores() {
        easyHighScore.setText(String.valueOf(HighScoreManager.getHighScore("easy")));
        mediumHighScore.setText(String.valueOf(HighScoreManager.getHighScore("medium")));
        hardHighScore.setText(String.valueOf(HighScoreManager.getHighScore("hard")));
    }


}
