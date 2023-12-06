package example.Controller;

import example.Model.MyFrameMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import example.MusicPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartFrameController implements Initializable{

    @FXML
    private Button startBtn, highScoreBtn, settingsBtn, easyBtn, mediumBtn, hardBtn;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;


    public MusicPlayer getCurrentMusic() {
        return currentMusic;
    }

    private MusicPlayer currentMusic, buttonClickedSound, backBtnSound;

    public void initializeSound() {
        if (this.currentMusic == null) {
            this.currentMusic = new MusicPlayer("src/main/resources/cw1setup/Sounds/main-menu-sound.mp3");
            //currentMusic.play();
        }

        if (this.buttonClickedSound == null) {
            this.buttonClickedSound = new MusicPlayer("src/main/resources/cw1setup/Sounds/decidemp3-14575.mp3");
        }

        if (this.backBtnSound == null) {
            this.backBtnSound = new MusicPlayer("src/main/resources/cw1setup/Sounds/Button Press Sound Effect.wav");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeSound();
    }

    @FXML
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
        buttonClickedSound.play();
    }

    @FXML
    public void switchToSettings(ActionEvent event) {}

    @FXML
    public void switchToHighScore(ActionEvent event) {}

    @FXML
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
        backBtnSound.play();
    }

    @FXML
    public void playEasyMode(ActionEvent event) throws IOException {
        currentMusic.stopMusic();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        MyFrameMain myFrame = new MyFrameMain();
        myFrame.start(stage);
    }

    @FXML
    public void playMediumMode(ActionEvent event) throws IOException {}

    @FXML
    public void playHardMode(ActionEvent event) throws IOException {}

}
