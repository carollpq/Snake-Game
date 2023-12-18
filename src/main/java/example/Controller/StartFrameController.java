package example.Controller;

import example.Model.ImageUtil;
import example.Model.MusicPlayer;
import example.StartFrameMain;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

import java.io.IOException;

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

    @FXML
    //Set the image of 'Start' button when it is not hovered
    public void onStart() {
        startBtnImg.setImage(ImageUtil.images.get("start-btn"));
    }
    @FXML
    //Set the image of 'Start' button when it is hovered
    public void onStartHover() {
        startBtnImg.setImage(ImageUtil.images.get("start-btn-hover"));
    }
    @FXML
    //Set the image of 'High Score' button when it is not hovered
    public void onHighScore() {
        highScoreBtnImg.setImage(ImageUtil.images.get("highscore-btn"));
    }

    @FXML
    //Set the image of 'High Score' button when it is hovered
    public void onHighScoreHover() {
        highScoreBtnImg.setImage(ImageUtil.images.get("highscore-btn-hover"));
    }

    @FXML
    //Set the image of 'Instructions' button when it is not hovered
    public void onInstructions() {
        instructionsBtnImg.setImage(ImageUtil.images.get("instructions-btn"));
    }

    @FXML
    //Set the image of 'Instructions' button when it is hovered
    public void onInstructionsHover() {
        instructionsBtnImg.setImage(ImageUtil.images.get("instructions-btn-hover"));
    }

    @FXML
    //Set the image of 'Easy' button when it is not hovered
    public void onEasy() {
        easyBtnImg.setImage(ImageUtil.images.get("easy-btn"));
    }

    @FXML
    //Set the image of 'Easy' button when it is hovered
    public void onEasyHover() {
        easyBtnImg.setImage(ImageUtil.images.get("easy-btn-hover"));
    }

    @FXML
    //Set the image of 'Medium' button when it is not hovered
    public void onMedium() {
        mediumBtnImg.setImage(ImageUtil.images.get("medium-btn"));
    }

    @FXML
    //Set the image of 'Medium' button when it is hovered
    public void onMediumHover() {
        mediumBtnImg.setImage(ImageUtil.images.get("medium-btn-hover"));
    }

    @FXML
    //Set the image of 'Hard' button when it is not hovered
    public void onHard() {
        hardBtnImg.setImage(ImageUtil.images.get("hard-btn"));
    }

    @FXML
    //Set the image of 'Hard' button when it is hovered
    public void onHardHover() {
        hardBtnImg.setImage(ImageUtil.images.get("hard-btn-hover"));
    }

    @FXML
    //Load the 'Select Your Mode' scene after the 'Next' button is clicked
    public void switchToGameMode() throws IOException {
        //Sets and loads the corresponding FXML file
        StartFrameMain.setRoot("/GameMode");
        MusicPlayer.playSoundEffect("Button Press Sound Effect.wav");
    }

    @FXML
    //Checks whether current user is previous user
    public void checkPlayerNameIsSet() throws IOException {
        //If it's still the same player, no need to prompt player to enter name again
        if (StartFrameMain.getPlayerName() != null) {
            switchToGameMode();
        } else {
            switchToEnterName();
        }
    }

    @FXML
    //Load the page to insert user's name after 'Start' button is clicked
    public void switchToEnterName() throws IOException {
        //Sets and loads the corresponding FXML file
        StartFrameMain.setRoot("/EnterNameFrame");
        //Loads and plays the button clicked sound effect
        MusicPlayer.playSoundEffect("decidemp3-14575.mp3");
    }

    @FXML
    //Load the Instructions page after 'Instructions' button is clicked
    public void switchToInstructions() throws IOException {
        //Sets and loads the corresponding FXML file
        StartFrameMain.setRoot("/InstructionFrame");
        //Loads and plays the button clicked sound effect
        MusicPlayer.playSoundEffect("decidemp3-14575.mp3");
    }

    @FXML
    public void switchToGameControls() throws IOException {
        //Sets and loads the corresponding FXML file
        StartFrameMain.setRoot("/GameControlsFrame");
        //Loads and plays the button clicked sound effect
        MusicPlayer.playSoundEffect("Button Press Sound Effect.wav");
    }

    @FXML
    //Loads the 'High Score' page after 'High Score' button is clicked
    public void switchToHighScore() throws IOException {
        //Sets and loads the corresponding FXML file
        StartFrameMain.setRoot("/HighScoreFrame");
        //Loads and plays the button clicked sound effect
        MusicPlayer.playSoundEffect("decidemp3-14575.mp3");
    }

    @FXML
    //Loads the 'Home' page/Main Menu page when 'Back' button is clicked
    public void backToMainMenu() throws IOException {
        //Sets and loads the corresponding FXML file
        StartFrameMain.setRoot("/StartFrame");
        //Loads and plays the button clicked sound effect
        MusicPlayer.playSoundEffect("Button Press Sound Effect.wav");
    }

    @FXML
    //Loads Easy mode after 'Easy' button is clicked
    public void playEasyMode() throws IOException {
        //Pauses current music playing to allow for countdown
        StartFrameMain.getCurrenMusic().pause();
        //Sets and loads the corresponding FXML file
        StartFrameMain.setRoot("/MyFrame");
        StartFrameMain.setCurrentMode("easy");
    }

    @FXML
    //Loads Medium mode after 'Medium' button is clicked
    public void playMediumMode() throws IOException {
        //Pauses current music playing to allow for countdown
        StartFrameMain.getCurrenMusic().pause();
        //Sets and loads the corresponding FXML file
        StartFrameMain.setRoot("/MediumFrame");
        StartFrameMain.setCurrentMode("medium");
    }

    @FXML
    //Loads Hard mode after 'Hard' button is clicked
    public void playHardMode() throws IOException {
        //Pauses current music playing to allow for countdown
        StartFrameMain.getCurrenMusic().pause();
        //Sets and loads the corresponding FXML file
        StartFrameMain.setRoot("/HardFrame");
        StartFrameMain.setCurrentMode("hard");
    }

}
