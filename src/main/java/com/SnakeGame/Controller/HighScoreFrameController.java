package com.SnakeGame.Controller;

import com.SnakeGame.Model.Utilities.HighScoreEntry;
import com.SnakeGame.Model.Utilities.HighScoreManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Map;

/**
 * Controller class for the high score display frame in the Snake Game.
 * Extends the StartFrameController. Handles the initialization and display
 * of high scores for easy, medium, and hard game modes.
 *
 * @author Carolina Lee
 *
 */
public class HighScoreFrameController extends StartFrameController {
    @FXML //High score labels
    private Label easyHighScore, mediumHighScore, hardHighScore;

    /**
     * Initializes the high score display frame.
     * Calls the method to display high scores.
     */
    @FXML
    public void initialize() {
        displayHighScores();
    }

    /**
     * Displays the high scores for easy, medium, and hard game modes.
     * Retrieves high scores from the HighScoreManager and updates the
     * corresponding labels with player names and scores.
     */
    private void displayHighScores() {
        Map<String, HighScoreEntry> highScores = HighScoreManager.readHighScores();

        displayHighScore(easyHighScore, highScores.getOrDefault("easy", new HighScoreEntry("", 0, "easy")));
        displayHighScore(mediumHighScore, highScores.getOrDefault("medium", new HighScoreEntry("", 0, "medium")));
        displayHighScore(hardHighScore, highScores.getOrDefault("hard", new HighScoreEntry("", 0, "hard")));
    }

    /**
     * Updates a label with the player name and score from a HighScoreEntry.
     *
     * @param label The label to be updated.
     * @param entry The HighScoreEntry containing player name and score.
     */
    private void displayHighScore(Label label, HighScoreEntry entry) {
        String playerName = entry.getPlayerName();
        int score = entry.getScore();
        label.setText(playerName + "   -   " + score);
    }

}
