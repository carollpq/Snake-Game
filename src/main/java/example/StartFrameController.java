package example;

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
    private Button startBtn, highScoreBtn, settingsBtn;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToGameMode(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/main/java/example/GameMode.fxml"));
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


}
