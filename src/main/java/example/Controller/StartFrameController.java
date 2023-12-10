package example.Controller;

import example.Model.StartFrameMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import example.MusicPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartFrameController {

    //@FXML
    //private Button startBtn, highScoreBtn, settingsBtn, easyBtn, mediumBtn, hardBtn;


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
        MyFrameController myFrame = new MyFrameController();
        //StartFrameMain.getScene().setOnKeyPressed(myFrame::handleKeyPress);
        myFrame.initialize();
    }

    @FXML
    public void playMediumMode(ActionEvent event) throws IOException {}

    @FXML
    public void playHardMode(ActionEvent event) throws IOException {}

}
