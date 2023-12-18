package com.SnakeGame.Model.Utilities;

/**
 * Represents a high score entry in the Snake Game, including the player's name,
 * score, and the game mode in which the score was achieved.
 *
 * @author Carolina Lee Pei Qian
 * @version 1.0
 */
public class HighScoreEntry {
    private String playerName;
    private int score;
    private String gameMode;

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public String getGameMode() {
        return gameMode;
    }

    public HighScoreEntry(String playerName, int score, String gameMode) {
        this.playerName = playerName;
        this.score = score;
        this.gameMode = gameMode;
    }
}

