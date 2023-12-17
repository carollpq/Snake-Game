package example.Model;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HighScoreManager {
    static String HIGH_SCORE_FILE = "high_scores.csv";

    public static void saveHighScore(String playerName, int score, String gameMode) {
        Map<String, HighScoreEntry> highScores = readHighScores();
        HighScoreEntry currentHighScore = highScores.getOrDefault(gameMode, new HighScoreEntry("", 0, gameMode));

        if (score > currentHighScore.getScore()) {
            HighScoreEntry newEntry = new HighScoreEntry(playerName, score, gameMode);
            highScores.put(gameMode, newEntry);
            writeHighScores(highScores);
        }
    }

    public static HighScoreEntry getHighScore(String gameMode) {
        Map<String, HighScoreEntry> highScores = readHighScores();

        // Check if there are no scores for the specified game mode
        if (highScores.isEmpty() || !highScores.containsKey(gameMode)) {
            return new HighScoreEntry("", 0, gameMode);
        }

        return highScores.get(gameMode);
    }

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
