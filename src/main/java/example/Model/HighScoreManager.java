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
    private static final String HIGH_SCORE_FILE = "high_scores.csv";

    public static void saveHighScore(String playerName, int score, String gameMode) {
        Map<String, HighScoreEntry> highScores = readHighScores();
        HighScoreEntry newEntry = new HighScoreEntry(playerName, score, gameMode);
        highScores.put(gameMode, newEntry);
        writeHighScores(highScores);
    }
    public static HighScoreEntry getHighScore(String gameMode) {
        Map<String, HighScoreEntry> highScores = readHighScores();
        return highScores.getOrDefault(gameMode, new HighScoreEntry("", 0, gameMode));
    }

    public static Map<String, HighScoreEntry> readHighScores() {
        Map<String, HighScoreEntry> highScores = new HashMap<>();
        try (Reader reader = new FileReader(HIGH_SCORE_FILE);
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                String gameMode = nextRecord[0];
                int score = Integer.parseInt(nextRecord[1]);
                String playerName = nextRecord[2];
                HighScoreEntry entry = new HighScoreEntry(playerName, score, gameMode);
                highScores.put(gameMode, entry);
            }
        } catch (FileNotFoundException | CsvValidationException e) {
            // File doesn't exist yet
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highScores;
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
