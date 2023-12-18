package example.Controller;

import example.StartFrameMain;
import example.Model.Utilities.MusicPlayer;

import java.io.IOException;

/**
 * Controller class for the medium game mode frame in the Snake Game.
 * Extends the MyFrameController. Handles the initialization and music setting
 * for the medium difficulty level of the Snake game.
 *
 * @author Carolina Lee Pei Qian
 * @version 1.0
 */
public class MediumFrameController extends MyFrameController {
    /**
     * Initializes the medium game mode frame.
     * Calls the method to set up the snake speed and bonusObj on-screen time.
     *
     * @throws IOException If an I/O error occurs during the initialization.
     * @throws InterruptedException If the thread is interrupted during the initialization.
     */
    @Override
    public void initialization() throws IOException, InterruptedException {
        super.initialization();
        //Increase the speed of snake
        setSnakeSpeed(8);
        //Decrease the amount of time that the bonusObj stays
        setOnScreenTime(2000);
    }

    /**
     * Starts the medium difficulty level game.
     * Calls the method to start the game and changes the background music.
     *
     * @throws IOException If an I/O error occurs during the transition to the game.
     * @throws InterruptedException If the thread is interrupted during the game start.
     */
    @Override
    public void startGame() throws IOException, InterruptedException {
        super.startGame();
        StartFrameMain.getCurrenMusic().pause();
        StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/Sounds/medium-mode-music.mp3"));
    }
}
