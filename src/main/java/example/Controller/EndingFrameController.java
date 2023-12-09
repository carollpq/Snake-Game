package example.Controller;

import example.MusicPlayer;
import javafx.fxml.FXML;

public class EndingFrameController extends StartFrameController {
    @FXML
    public void playAgain() {}

    @Override
    public void initializeSound() {
        super.initializeSound();
        //getCurrentMusic() = new MusicPlayer("src/main/resources/cw1setup/Sounds/main-menu-sound.mp3");
    }
}
