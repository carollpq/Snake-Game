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
    public void switchToSettings(ActionEvent event) {}

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
        StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/cw1setup/Sounds/easy-mode-music.mp3"));
        StartFrameMain.setRoot("/cw1setup/MyFrame");
        StartFrameMain.setCurrentMode("easy");
        MyFrameController myFrame = StartFrameMain.getLoader().getController();
        StartFrameMain.getScene().setOnKeyPressed(myFrame::handleKeyPress);
    }

    @FXML
    public void playMediumMode() throws IOException {}

    @FXML
    public void playHardMode() throws IOException {}

}
