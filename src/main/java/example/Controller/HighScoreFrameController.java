package example.Controller;

import example.Model.HighScoreEntry;
import example.Model.HighScoreManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Map;

public class HighScoreFrameController extends StartFrameController {
    @FXML //High score labels
    private Label easyHighScore, mediumHighScore, hardHighScore;

    @FXML
    public void initialize() {
        displayHighScores();
    }

    private void displayHighScores() {
        Map<String, HighScoreEntry> highScores = HighScoreManager.readHighScores();

        displayHighScore(easyHighScore, highScores.getOrDefault("easy", new HighScoreEntry("", 0, "easy")));
        displayHighScore(mediumHighScore, highScores.getOrDefault("medium", new HighScoreEntry("", 0, "medium")));
        displayHighScore(hardHighScore, highScores.getOrDefault("hard", new HighScoreEntry("", 0, "hard")));
    }

    private void displayHighScore(Label label, HighScoreEntry entry) {
        String playerName = entry.getPlayerName();
        int score = entry.getScore();
        label.setText(playerName + "   -   " + score);
    }

}
