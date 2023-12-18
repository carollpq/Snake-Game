package example.Model.Utilities;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.exceptions.CsvValidationException;
import example.Model.Utilities.HighScoreEntry;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages high scores for different game modes, including reading, writing, and retrieving high scores.
 * The high scores are stored in a CSV file, and this class provides functionality to update and retrieve
 * the highest scores for each game mode.
 *
 * @author Your Name
 * @version 1.0
 */

public class HighScoreManager {
    static String HIGH_SCORE_FILE = "high_scores.csv";

    /**
     * Saves the player's high score for a specific game mode. If the player's score is higher than the
     * existing high score for the given mode, it updates the high score.
     *
     * @param playerName The name of the player achieving the high score.
     * @param score      The score achieved by the player.
     * @param gameMode   The game mode for which the high score is recorded.
     */
    public static void saveHighScore(String playerName, int score, String gameMode) {
        Map<String, HighScoreEntry> highScores = readHighScores();
        HighScoreEntry currentHighScore = highScores.getOrDefault(gameMode, new HighScoreEntry("", 0, gameMode));

        if (score > currentHighScore.getScore()) {
            HighScoreEntry newEntry = new HighScoreEntry(playerName, score, gameMode);
            highScores.put(gameMode, newEntry);
            writeHighScores(highScores);
        }
    }

    /**
     * Retrieves the highest score entry for a specific game mode.
     *
     * @param gameMode The game mode for which the high score is retrieved.
     * @return The HighScoreEntry representing the highest score for the specified game mode.
     */
    public static HighScoreEntry getHighScore(String gameMode) {
        Map<String, HighScoreEntry> highScores = readHighScores();

        // Check if there are no scores for the specified game mode
        if (highScores.isEmpty() || !highScores.containsKey(gameMode)) {
            return new HighScoreEntry("", 0, gameMode);
        }

        return highScores.get(gameMode);
    }

    /**
     * Reads high scores from the CSV file and returns a map containing the highest score entries for each game mode.
     *
     * @return A map where keys are game modes and values are corresponding HighScoreEntry objects.
     */
    public static Map<String, HighScoreEntry> readHighScores() {
        Map<String, HighScoreEntry> highScores = new HashMap<>();
        try (Reader reader = new FileReader(HIGH_SCORE_FILE);
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                if (nextRecord.length >= 3 && !isEmptyRow(nextRecord)) {
                    String gameMode = nextRecord[0];
                    int score = Integer.parseInt(nextRecord[1]);
                    String playerName = nextRecord[2];
                    HighScoreEntry entry = new HighScoreEntry(playerName, score, gameMode);

                    if (!highScores.containsKey(gameMode) || score > highScores.get(gameMode).getScore()) {
                        highScores.put(gameMode, entry);
                    }
                } else {
                    // Handle the case where the row doesn't have enough elements or is empty
                    System.out.println("Invalid row in CSV file: " + String.join(", ", nextRecord));
                }
            }
        } catch (FileNotFoundException | CsvValidationException e) {
            // File doesn't exist yet
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highScores;
    }

    // Helper method to check if a row is empty
    private static boolean isEmptyRow(String[] row) {
        for (String element : row) {
            if (!element.trim().isEmpty()) {
                return false; // The row is not empty
            }
        }
        return true; // The row is empty
    }


    /**
     * Writes the provided high scores to the CSV file.
     *
     * @param highScores A map where keys are game modes and values are corresponding HighScoreEntry objects.
     */
    private static void writeHighScores(Map<String, HighScoreEntry> highScores) {
        try (Writer writer = new FileWriter(HIGH_SCORE_FILE);
             CSVWriter csvWriter = (CSVWriter) new CSVWriterBuilder(writer).build()) {
            csvWriter.writeNext(new String[]{"Game Mode", "High Score", "Player Name"});
            for (Map.Entry<String, HighScoreEntry> entry : highScores.entrySet()) {
                HighScoreEntry highScoreEntry = entry.getValue();
                csvWriter.writeNext(new String[]{entry.getKey(), String.valueOf(highScoreEntry.getScore()), highScoreEntry.getPlayerName()});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
