package example.Controller;

import example.Model.ImageUtil;
import example.StartFrameMain;
import example.Model.MusicPlayer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

/**
 * Controller class for the ending frame of the Snake Game. Extends the StartFrameController.
 * Handles user interactions and events on the ending frame, such as displaying the final score,
 * managing button hover effects, and providing functionality to restart the game or return to the main menu.
 *
 * @author Carolina Lee Pei Qian
 * @version 1.0
 */
public class EndingFrameController extends StartFrameController {
    @FXML
    //Images in the 'Play Again' and 'Main Menu' buttons
    private ImageView playAgainBtnImg, mainMenuBtnImg;

    @FXML
    private Label scoreLabel;

    /**
     * Sets the text of the score label to display the final score.
     *
     * @param score The final score to be displayed.
     */
    public void setScoreLabel(int score) {
        scoreLabel.setText(String.valueOf(score));
    }

    @FXML
    /**
     * Set the image of 'Play Again' button when it is not hovered
     */
    public void onPlayAgain() {
        playAgainBtnImg.setImage(ImageUtil.images.get("play-again-btn"));
    }

    @FXML
    /**
     * Set the image of 'Play Again' button when it is hovered
     */
    public void onPlayAgainHover() {
        playAgainBtnImg.setImage(ImageUtil.images.get("play-again-hover"));
    }

    @FXML
    /**
     * Set the image of 'Main Menu' button when it is not hovered
     */
    public void onMainMenu() {
        mainMenuBtnImg.setImage(ImageUtil.images.get("main-menu-btn"));
    }

    @FXML
    /**
     * Set the image of 'Main Menu' button when it is hovered
     */
    public void onMainMenuHover() {
        mainMenuBtnImg.setImage(ImageUtil.images.get("main-menu-hover"));
    }

    /**
     * Restarts the game when the 'Play Again' button is clicked.
     *
     * @throws IOException            If an I/O error occurs during the loading of the game.
     * @throws InterruptedException   If the execution of the game is interrupted.
     */
    @FXML
    public void playAgain() throws IOException, InterruptedException {
        scoreLabel.setVisible(false); //Removes the label displaying high score
        if (Objects.equals(StartFrameMain.getCurrentMode(), "easy")) {
            playEasyMode();
        } else if (Objects.equals(StartFrameMain.getCurrentMode(), "medium")) {
            playMediumMode();
        } else if (Objects.equals(StartFrameMain.getCurrentMode(), "hard")) {
            playHardMode();
        }
    }

    /**
     * Initializes the ending frame, setting the text for the label displaying the final score
     * when the FXML file is loaded.
     */
    @FXML
    public void initialize() {
        //Sets the text for label displaying final score when FXML file is loaded
        setScoreLabel(MyFrameController.getFinalScore());
    }

    @Override
    /**
     * Loads the Home page/Main Menu page after the 'Main Menu' button is clicked.
     *
     * @throws IOException If an I/O error occurs during the loading of the main menu.
     */
    public void backToMainMenu() throws IOException {
        scoreLabel.setVisible(false); //Removes the label displaying high score
        super.backToMainMenu();
        StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/Sounds/main-menu-music.mp3"));
    }
}
