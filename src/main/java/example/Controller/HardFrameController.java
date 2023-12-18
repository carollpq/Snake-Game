package example.Controller;

import example.StartFrameMain;
import example.Model.Utilities.MusicPlayer;

import java.io.IOException;

/**
 * Controller class for the hard game mode frame in the Snake Game.
 * Extends the MyFrameController class. Handles game initialization and starting
 * for the hard game mode, including adjustments to snake speed and bonus object duration.
 *
 * @author Carolina Lee Pei Qian
 * @version 1.0
 */
public class HardFrameController extends MyFrameController{
    /**
     * Initializes the hard game mode frame.
     * Overrides the superclass method to set the snake speed to 12 and
     * decrease the bonus object on-screen time to 1000 milliseconds.
     *
     * @throws IOException            If an I/O error occurs during initialization.
     * @throws InterruptedException   If the thread is interrupted during initialization.
     */
    @Override
    public void initialization() throws IOException, InterruptedException {
        super.initialization();
        //Increase the speed of snake
        setSnakeSpeed(12);
        //Decrease the amount of time that the bonusObj stays
        setOnScreenTime(1000);
    }

    /**
     * Starts the hard game mode.
     * Overrides the superclass method to pause the current music and change
     * the background music to the hard mode music.
     *
     * @throws IOException            If an I/O error occurs during game start.
     * @throws InterruptedException   If the thread is interrupted during game start.
     */
    @Override
    public void startGame() throws IOException, InterruptedException {
        super.startGame();
        StartFrameMain.getCurrenMusic().pause();
        StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/Sounds/hard-mode-music.mp3"));
    }
}
