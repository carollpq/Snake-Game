package example.Model;

import com.opencsv.CSVWriter;
import example.Model.Utilities.HighScoreEntry;
import example.Model.Utilities.HighScoreManager;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HighScoreManagerTest {
    //Initialise JFX Toolkit
    JFXPanel jfxpanel = new JFXPanel();

    @TempDir
    static Path tempDir;

    private static final String TEST_GAME_MODE = "testMode";
    private static final String TEST_PLAYER_NAME = "testPlayer";
    private static final int TEST_SCORE = 100;

    private String highScoreFilePath;

    @BeforeEach
    void setUp() {
        // Use a temporary file for testing
        highScoreFilePath = tempDir.resolve("high_scores_test.csv").toString();
        HighScoreManager.HIGH_SCORE_FILE = highScoreFilePath;
    }

    @Test
    void saveAndRetrieveHighScore_ShouldSaveAndRetrieveHighScore() {
        // Save a high score
        HighScoreManager.saveHighScore(TEST_PLAYER_NAME, TEST_SCORE, TEST_GAME_MODE);

        // Retrieve the high score
        HighScoreEntry retrievedEntry = HighScoreManager.getHighScore(TEST_GAME_MODE);

        // Verify the retrieved high score
        assertEquals(TEST_GAME_MODE, retrievedEntry.getGameMode());
        assertEquals(TEST_PLAYER_NAME, retrievedEntry.getPlayerName());
        assertEquals(TEST_SCORE, retrievedEntry.getScore());
    }

    @Test
    void readHighScores_ShouldReadExistingHighScoresFile() {
        // Prepare a sample CSV file with high scores
        prepareSampleHighScoresFile();

        // Read high scores from the sample file
        Map<String, HighScoreEntry> highScores = HighScoreManager.readHighScores();

        // Verify the content of the map
        assertEquals(2, highScores.size());
        assertEquals(150, highScores.get("easy").getScore());
        assertEquals("Player1", highScores.get("easy").getPlayerName());
        assertEquals(200, highScores.get("hard").getScore());
        assertEquals("Player2", highScores.get("hard").getPlayerName());
    }

    private void prepareSampleHighScoresFile() {
        File highScoresFile = new File(highScoreFilePath);
        try (CSVWriter csvWriter = new CSVWriter(new java.io.FileWriter(highScoresFile))) {
            csvWriter.writeNext(new String[]{"Game Mode", "High Score", "Player Name"});
            csvWriter.writeNext(new String[]{"easy", "150", "Player1"});
            csvWriter.writeNext(new String[]{"hard", "200", "Player2"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add a method to delete the specific file after the tests
    @AfterEach
    void cleanUp() {
        File fileToDelete = new File(highScoreFilePath);
        if (fileToDelete.exists()) {
            fileToDelete.delete();
        }
    }
}
