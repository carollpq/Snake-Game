package example.Controller;

import example.Model.StartFrameMain;
import example.MusicPlayer;

import java.io.IOException;

public class HardFrameController extends MyFrameController{
    @Override
    public void initialization() throws IOException, InterruptedException {
        super.initialization();
        //Increase the speed of snake
        setSnakeSpeed(12);
        //Decrease the amount of time that the bonusObj stays
        setOnScreenTime(1000);
    }
    @Override
    public void startGame() throws IOException, InterruptedException {
        super.startGame();
        StartFrameMain.getCurrenMusic().pause();
        StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/cw1setup/Sounds/hard-mode-music.mp3"));
    }
}
