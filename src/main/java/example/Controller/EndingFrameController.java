package example.Controller;

import example.MusicPlayer;
import javafx.fxml.FXML;


public class EndingFrameController extends StartFrameController {
    @FXML
    public void playAgain() {}

    @Override
    public void initializeSound() {
        super.initializeSound();
        //Change main menu music to ending scene music
        setCurrentMusic(new MusicPlayer("src/main/resources/cw1setup/Sounds/ending-scene-music.mp3"));
    }
}
