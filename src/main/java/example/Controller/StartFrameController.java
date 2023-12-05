package example.Controller;

import example.MusicPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartFrameController {
    @FXML
    private Button startBtn, highScoreBtn, settingsBtn, easyBtn, mediumBtn, hardBtn;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToGameMode(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/cw1setup/GameMode.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        //Fetch CSS file
        String css = this.getClass().getResource("/main/java/example/application.css").toExternalForm();
        //Sets the  CSS file to this scene
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
        MusicPlayer.getMusicPlay("src/main/resources/cw1setup/Sounds/decidemp3-14575.mp3");
    }

    public void switchToSettings(ActionEvent event) {}

    public void switchToHighScore(ActionEvent event) {}

    public void backToMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/main/resources/cw1setup/StartFrame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        //Fetch CSS file
        String css = this.getClass().getResource("/main/java/example/application.css").toExternalForm();
        //Sets the  CSS file to this scene
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
        MusicPlayer.getMusicPlay("src/main/resources/cw1setup/Sounds/Button Press Sound Effect.wav");
    }

    public void playEasyMode(ActionEvent event) throws IOException {
        MusicPlayer.getMusicPlay("src/main/resources/cw1setup/Sounds/easy-mode-music.mp3");
    }

    public void playMediumMode(ActionEvent event) throws IOException {}

    public void playHardMode(ActionEvent event) throws IOException {}
}
