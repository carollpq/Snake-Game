package example.Controller;

import example.Model.StartFrameMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.AudioClip;
import example.MusicPlayer;

import java.io.IOException;

public class StartFrameController {

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
    public void switchToHighScore(ActionEvent event) {}

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
