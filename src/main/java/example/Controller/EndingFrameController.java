package example.Controller;

import example.Model.StartFrameMain;
import example.MusicPlayer;
import javafx.fxml.FXML;

import java.io.IOException;


public class EndingFrameController extends StartFrameController {
    @FXML
    public void playAgain() {}

    @Override
    public void backToMainMenu() throws IOException {
        super.backToMainMenu();
        StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/cw1setup/Sounds/main-menu-music.mp3"));
    }
}
