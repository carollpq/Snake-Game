package example.Controller;

import example.GameUtil;
import example.HighScoreManager;
import example.ImageUtil;
import example.Model.StartFrameMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import example.MusicPlayer;

import java.io.IOException;

public class StartFrameController {

    @FXML //Button images
    private ImageView startBtnImg, highScoreBtnImg, instructionsBtnImg, easyBtnImg, mediumBtnImg, hardBtnImg;

    @FXML
    public void onStart() {
        startBtnImg.setImage(ImageUtil.images.get("start-btn"));
    }
    @FXML //Set hover image
    public void onStartHover() {
        startBtnImg.setImage(ImageUtil.images.get("start-btn-hover"));
    }
    @FXML
    public void onHighScore() {
        highScoreBtnImg.setImage(ImageUtil.images.get("highscore-btn"));
    }

    @FXML
    public void onHighScoreHover() {
        highScoreBtnImg.setImage(ImageUtil.images.get("highscore-btn-hover"));
    }

    @FXML
    public void onInstructions() {
        instructionsBtnImg.setImage(ImageUtil.images.get("instructions-btn"));
    }

    @FXML
    public void onInstructionsHover() {
        instructionsBtnImg.setImage(ImageUtil.images.get("instructions-btn-hover"));
    }

    @FXML
    public void onEasy() {
        easyBtnImg.setImage(ImageUtil.images.get("easy-btn"));
    }

    @FXML
    public void onEasyHover() {
        easyBtnImg.setImage(ImageUtil.images.get("easy-btn-hover"));
    }

    @FXML
    public void onMedium() {
        mediumBtnImg.setImage(ImageUtil.images.get("medium-btn"));
    }

    @FXML
    public void onMediumHover() {
        mediumBtnImg.setImage(ImageUtil.images.get("medium-btn-hover"));
    }

    @FXML
    public void onHard() {
        hardBtnImg.setImage(ImageUtil.images.get("hard-btn"));
    }

    @FXML
    public void onHardHover() {
        hardBtnImg.setImage(ImageUtil.images.get("hard-btn-hover"));
    }

    @FXML
    public void switchToGameMode() throws IOException {
        StartFrameMain.setRoot("/cw1setup/GameMode");
        new AudioClip(
                getClass()
                        .getResource("/cw1setup/Sounds/decidemp3-14575.mp3")
                        .toExternalForm())
                .play();
    }

    @FXML
    public void switchToInstructions() throws IOException {
        StartFrameMain.setRoot("/cw1setup/InstructionFrame");
        new AudioClip(
                getClass()
                        .getResource("/cw1setup/Sounds/decidemp3-14575.mp3")
                        .toExternalForm())
                .play();
    }

    @FXML
    public void switchToHighScore() throws IOException {
        StartFrameMain.setRoot("/cw1setup/HighScoreFrame");
        new AudioClip(
                getClass()
                        .getResource("/cw1setup/Sounds/decidemp3-14575.mp3")
                        .toExternalForm())
                .play();
    }

    @FXML
    public void backToMainMenu() throws IOException {
        StartFrameMain.setRoot("/cw1setup/StartFrame");
        new AudioClip(
                getClass()
                        .getResource("/cw1setup/Sounds/Button Press Sound Effect.wav")
                        .toExternalForm())
                .play();
    }

    @FXML
    public void playEasyMode() throws IOException, InterruptedException {
        StartFrameMain.getCurrenMusic().pause();
        StartFrameMain.setRoot("/cw1setup/MyFrame");
        StartFrameMain.setCurrentMode("easy");
    }

    @FXML
    public void playMediumMode() throws IOException {
        StartFrameMain.getCurrenMusic().pause();
        StartFrameMain.setRoot("/cw1setup/MediumFrame");
        StartFrameMain.setCurrentMode("medium");
    }

    @FXML
    public void playHardMode() throws IOException {
        StartFrameMain.getCurrenMusic().pause();
        StartFrameMain.setRoot("/cw1setup/HardFrame");
        StartFrameMain.setCurrentMode("hard");
    }

}
