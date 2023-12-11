package example.Controller;

import example.Model.StartFrameMain;
import example.MusicPlayer;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.Objects;


public class EndingFrameController extends StartFrameController {
    @FXML
    public void playAgain() throws IOException, InterruptedException {
        if (Objects.equals(StartFrameMain.getCurrentMode(), "easy")) {
            playEasyMode();
        } else if (Objects.equals(StartFrameMain.getCurrentMode(), "medium")) {
            playMediumMode();
        } else {
            playHardMode();
        }
    }

    @Override
    public void backToMainMenu() throws IOException {
        super.backToMainMenu();
        StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/cw1setup/Sounds/main-menu-music.mp3"));
    }
}
