package example;

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

    public static void saveHighScore(int score, String gameMode) {
        Map<String, Integer> highScores = readHighScores();
        highScores.put(gameMode, Math.max(highScores.getOrDefault(gameMode, 0), score));
        writeHighScores(highScores);
    }

    public static int getHighScore(String gameMode) {
        Map<String, Integer> highScores = readHighScores();
        return highScores.getOrDefault(gameMode, 0);
    }

    private static Map<String, Integer> readHighScores() {
        Map<String, Integer> highScores = new HashMap<>();
        try (Reader reader = new FileReader(HIGH_SCORE_FILE);
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                highScores.put(nextRecord[0], Integer.parseInt(nextRecord[1]));
            }
        } catch (FileNotFoundException | CsvValidationException e) {
            // File doesn't exist yet
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highScores;
    }

    private static void writeHighScores(Map<String, Integer> highScores) {
        try (Writer writer = new FileWriter(HIGH_SCORE_FILE);
             CSVWriter csvWriter = (CSVWriter) new CSVWriterBuilder(writer).build()) {
            csvWriter.writeNext(new String[]{"Game Mode", "High Score"});
            for (Map.Entry<String, Integer> entry : highScores.entrySet()) {
                csvWriter.writeNext(new String[]{entry.getKey(), String.valueOf(entry.getValue())});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
