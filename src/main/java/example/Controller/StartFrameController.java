package example.Controller;

import example.Model.ImageUtil;
import example.Model.MusicPlayer;
import example.StartFrameMain;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

import java.io.IOException;


/**
 * Controller class for the start frame, handling user interactions and scene transitions.
 *
 * This class contains methods to set images for buttons, switch scenes, and play sound effects.
 *
 * @author [Author Name]
 * @version 1.0
 */
public class StartFrameController {

    @FXML //Button images
    ImageView startBtnImg;
    @FXML //Button images
    private ImageView highScoreBtnImg;
    @FXML //Button images
    private ImageView instructionsBtnImg;
    @FXML //Button images
    private ImageView easyBtnImg;
    @FXML //Button images
    private ImageView mediumBtnImg;
    @FXML //Button images
    private ImageView hardBtnImg;

    /**
     * Set the image of the 'Start' button when it is not hovered.
     */
    @FXML
    public void onStart() {
        startBtnImg.setImage(ImageUtil.images.get("start-btn"));
    }

    /**
     * Set the image of 'Start' button when it is hovered
     */
    @FXML
    public void onStartHover() {
        startBtnImg.setImage(ImageUtil.images.get("start-btn-hover"));
    }

    /**
     * Set the image of 'High Score' button when it is not hovered
     */
    @FXML
    public void onHighScore() {
        highScoreBtnImg.setImage(ImageUtil.images.get("highscore-btn"));
    }

    /**
     * Set the image of 'High Score' button when it is hovered
     */
    @FXML
    public void onHighScoreHover() {
        highScoreBtnImg.setImage(ImageUtil.images.get("highscore-btn-hover"));
    }

    /**
     * Set the image of 'Instructions' button when it is not hovered
     */
    @FXML
    public void onInstructions() {
        instructionsBtnImg.setImage(ImageUtil.images.get("instructions-btn"));
    }

    /**
     * Set the image of 'Instructions' button when it is hovered
     */
    @FXML
    public void onInstructionsHover() {
        instructionsBtnImg.setImage(ImageUtil.images.get("instructions-btn-hover"));
    }

    /**
     * Set the image of 'Easy' button when it is not hovered
     */
    @FXML
    public void onEasy() {
        easyBtnImg.setImage(ImageUtil.images.get("easy-btn"));
    }

    /**
     * Set the image of 'Easy' button when it is hovered
     */
    @FXML
    public void onEasyHover() {
        easyBtnImg.setImage(ImageUtil.images.get("easy-btn-hover"));
    }

    /**
     * Set the image of 'Medium' button when it is not hovered
     */
    @FXML
    public void onMedium() {
        mediumBtnImg.setImage(ImageUtil.images.get("medium-btn"));
    }

    /**
     * Set the image of 'Medium' button when it is hovered
     */
    @FXML
    public void onMediumHover() {
        mediumBtnImg.setImage(ImageUtil.images.get("medium-btn-hover"));
    }

    /**
     * Set the image of 'Hard' button when it is not hovered
     */
    @FXML
    public void onHard() {
        hardBtnImg.setImage(ImageUtil.images.get("hard-btn"));
    }

    /**
     * Set the image of 'Hard' button when it is hovered
     */
    @FXML
    public void onHardHover() {
        hardBtnImg.setImage(ImageUtil.images.get("hard-btn-hover"));
    }

    /**
     * Load the 'Select Your Mode' scene after the 'Next' button is clicked
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void switchToGameMode() throws IOException {
        //Sets and loads the corresponding FXML file
        StartFrameMain.setRoot("/GameMode");
        MusicPlayer.playSoundEffect("Button Press Sound Effect.wav");
    }

    /**
     * Checks whether current user is previous user
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void checkPlayerNameIsSet() throws IOException {
        //If it's still the same player, no need to prompt player to enter name again
        if (StartFrameMain.getPlayerName() != null) {
            switchToGameMode();
        } else {
            switchToEnterName();
        }
    }

    /**
     * Switches to the page to insert the user's name after the 'Start' button is clicked.
     *
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void switchToEnterName() throws IOException {
        //Sets and loads the corresponding FXML file
        StartFrameMain.setRoot("/EnterNameFrame");
        //Loads and plays the button clicked sound effect
        MusicPlayer.playSoundEffect("decidemp3-14575.mp3");
    }

    /**
     * Load the Instructions page after 'Instructions' button is clicked
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void switchToInstructions() throws IOException {
        //Sets and loads the corresponding FXML file
        StartFrameMain.setRoot("/InstructionFrame");
        //Loads and plays the button clicked sound effect
        MusicPlayer.playSoundEffect("decidemp3-14575.mp3");
    }

    /**
     * Loads the 'Game Controls' page after user enters their name
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void switchToGameControls() throws IOException {
        //Sets and loads the corresponding FXML file
        StartFrameMain.setRoot("/GameControlsFrame");
        //Loads and plays the button clicked sound effect
        MusicPlayer.playSoundEffect("Button Press Sound Effect.wav");
    }

    /**
     * Loads the 'High Score' page after 'High Score' button is clicked
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void switchToHighScore() throws IOException {
        //Sets and loads the corresponding FXML file
        StartFrameMain.setRoot("/HighScoreFrame");
        //Loads and plays the button clicked sound effect
        MusicPlayer.playSoundEffect("decidemp3-14575.mp3");
    }

    /**
     * Loads the 'Home' page/Main Menu page when 'Back' button is clicked
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void backToMainMenu() throws IOException {
        //Sets and loads the corresponding FXML file
        StartFrameMain.setRoot("/StartFrame");
        //Loads and plays the button clicked sound effect
        MusicPlayer.playSoundEffect("Button Press Sound Effect.wav");
    }

    /**
     * Loads Easy mode after 'Easy' button is clicked
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void playEasyMode() throws IOException {
        //Pauses current music playing to allow for countdown
        StartFrameMain.getCurrenMusic().pause();
        //Sets and loads the corresponding FXML file
        StartFrameMain.setRoot("/MyFrame");
        StartFrameMain.setCurrentMode("easy");
    }

    /**
     * Loads Medium mode after 'Medium' button is clicked
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void playMediumMode() throws IOException {
        //Pauses current music playing to allow for countdown
        StartFrameMain.getCurrenMusic().pause();
        //Sets and loads the corresponding FXML file
        StartFrameMain.setRoot("/MediumFrame");
        StartFrameMain.setCurrentMode("medium");
    }

    /**
     * Loads Hard mode after 'Hard' button is clicked
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void playHardMode() throws IOException {
        //Pauses current music playing to allow for countdown
        StartFrameMain.getCurrenMusic().pause();
        //Sets and loads the corresponding FXML file
        StartFrameMain.setRoot("/HardFrame");
        StartFrameMain.setCurrentMode("hard");
    }

}
