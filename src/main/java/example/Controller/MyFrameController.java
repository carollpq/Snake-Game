package example.Controller;

import example.Model.*;
import example.StartFrameMain;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * The controller class for the Easy game mode, responsible for managing the game logic,
 * user input, and interactions with the graphical user interface.
 *
 * This class implements the Initializable interface, allowing it to be used as a controller
 * for JavaFX FXML files. It handles the initialization of the game, user input, game state updates,
 * and transitions between different scenes.
 *
 * The game features a snake that moves across the canvas, consuming food and bonus objects,
 * with the goal of achieving the highest score possible. The class utilizes JavaFX for GUI components
 * and animation effects.
 *
 * @author Carolina Lee Pei Qian
 * @version 1.0
 */
public class MyFrameController implements Initializable {

    @FXML
    private Canvas gameCanvas;

    @FXML
    //Images for buttons
    private ImageView pauseImg, pauseBoard, darkenedBgImg, countDownBackDrop;

    @FXML
    //Shows the number of seconds left in countdown on screen
    private Label countdownLabel;
    @FXML
    private GraphicsContext graphicsContext;

    private MySnake mySnake; //The Snake of the game
    private Food food; //Food objects for Snake
    private PowerUp bonusObj; //Bonus points objects

    private boolean stopAnimation = false; // Flag to control the animation stopping condition
    private static AnimationTimer animationTimer;
    static boolean pause = false; //Keeps track whether the game is paused
    private Timeline countdownTimeline;
    private int countdownSeconds = 4; // Set the desired countdown time in seconds

    private Timer bonusSpawnTimer;
    private TimerTask bonusSpawnTask;
    private long bonusSpawnTime;

    private int snakeSpeed;
    private int onScreenTime; //Time for bonus points objects to stay on screen
    private static int finalScore; //Records the final score obtained

    private static String controls; //Whether left or right controls are used

    public Canvas getGameCanvas() {
        return gameCanvas;
    }

    public int getOnScreenTime() {
        return onScreenTime;
    }

    public void setOnScreenTime(int onScreenTime) {
        this.onScreenTime = onScreenTime;
    }

    public int getSnakeSpeed() {
        return snakeSpeed;
    }

    public void setSnakeSpeed(int snakeSpeed) {
        this.snakeSpeed = snakeSpeed;
    }
    public static int getFinalScore() {
        return finalScore;
    }

    public static void setFinalScore(int finalScore) {
        MyFrameController.finalScore = finalScore;
    }

    public static void setControls(String controls) {
        MyFrameController.controls = controls;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initialization();
            //Configuring the key press events
            StartFrameMain.getScene().setOnKeyPressed(event -> {
                try {
                    handleKeyPress(event);
                } catch (IOException e) {
                    // Log the exception or take appropriate action
                    e.printStackTrace();
                }
            });
        } catch (IOException | InterruptedException e) {
            // Log the exception or take appropriate action
            e.printStackTrace();
        }
    }

    @FXML
    public void initialization() throws IOException, InterruptedException {
        //Reset final score at the start of game
        setFinalScore(0);
        // Initialize the Timeline for countdown
        setSnakeSpeed(5);
        setOnScreenTime(4000);
        countdownTimeline = new Timeline();
        countdownTimeline.setCycleCount(countdownSeconds);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            countdownSeconds--;
            if (countdownSeconds > 0) {
                countdownLabel.setText(Integer.toString(countdownSeconds));
                //Plays countdown sound effect
                MusicPlayer.playSoundEffect("countdown-audio.mp3");
            } else {
                // Stops the countdown timer after countdown ends
                countdownTimeline.stop();
                try {
                    startGame(); //Start the game after the countdown
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        countdownTimeline.getKeyFrames().add(keyFrame);
        countdownTimeline.play();
    }

    /**
     * Starts the game after the countdown, initializing the snake, food, and bonus objects.
     *
     * @throws IOException          If an I/O error occurs.
     * @throws InterruptedException If the thread is interrupted.
     */
    public void startGame() throws IOException, InterruptedException {
        //Clears the snake body points
        MySnake.bodyPoints = new LinkedList<>();
        //Reset pause button
        pause = false;
        pauseBoard.setVisible(false);
        darkenedBgImg.setVisible(false);
        //Plays Start Game sound effect
        MusicPlayer.playSoundEffect("game_start_post-timer.mp3");
        countdownLabel.setVisible(false); //Hides previous countdown label
        countDownBackDrop.setVisible(false); //Hides previous background image used for countdown
        graphicsContext = gameCanvas.getGraphicsContext2D();
        mySnake = new MySnake(100, 100, getSnakeSpeed()); //Creates instance of 'Snake'
        food = new Food(); //Creates instance of Food object
        bonusObj = FactoryPowerUp.makePowerUp() ; //Creates instance of bonus points object
        //Initialize the bonusObj spawn timer and task if not already done
        if (bonusSpawnTimer == null) {
            bonusSpawnTimer = new Timer();
            scheduleBonusSpawn();
        }
        drawSnakeObjects(graphicsContext);
        animationTimer = animationTimer();
        gameCanvas.requestFocus(); //Ensure the buttons and objects on gameCanvas detect key presses
        playModeMusic(StartFrameMain.getCurrentMode()); //Plays the music for the game mode
    }

    /**
     * Plays the music corresponding to the selected game mode.
     *
     * @param gameMode The selected game mode.
     */
    public void playModeMusic(String gameMode) {
        StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/Sounds/"+ gameMode +"-mode-music.mp3"));
    }

    /**
     * Schedules the spawning of bonus points objects onto the screen at random intervals.
     */
    private void scheduleBonusSpawn() {
        int delay = getRandomDelay(); // Get a random delay in milliseconds
        int period = 30000; // Set the period for bonusObj spawn (30,000 milliseconds = 30 seconds)

        // Cancel the existing task if it exists
        if (bonusSpawnTask != null) {
            bonusSpawnTask.cancel();
        }

        bonusSpawnTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (!bonusObj.liveOfObject) {
                        bonusObj = FactoryPowerUp.makePowerUp();
                        bonusSpawnTime = System.currentTimeMillis(); // Set the bonus spawn time
                    }
                    scheduleBonusSpawn(); // Reschedule for the next random interval
                });
            }
        };
        bonusSpawnTimer.schedule(bonusSpawnTask, delay, period);
    }


    /**
     * Gets a random delay for scheduling the bonus points object spawn.
     *
     * @return A random delay in milliseconds.
     */
    private int getRandomDelay() {
        // Get a random delay between 5 and 15 seconds
        return (int) (Math.random() * 10000) + 5000;
    }

    /**
     * Draws the current score on the game canvas.
     *
     * @param gc The GraphicsContext used for rendering.
     */
    public void drawScore(GraphicsContext gc)
    {
        Font font = Font.font("Sans Serif", FontWeight.BOLD, FontPosture.REGULAR, 30);
        gc.setFont(font);
        gc.setFill(Color.BLACK);
        gc.fillText("SCORE : " + mySnake.score, 20, 40);
    }

    /**
     * Clears the canvas and draws the snake, food, and bonus objects onto the screen.
     *
     */
    public void clearCanvas(){
        graphicsContext.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
        mySnake.draw(graphicsContext); //Draws snake onto canvas
        if (food.liveOfObject) //If Food object has not been eaten
        {
            food.draw(graphicsContext); //Continue drawing current Food at current position
            food.eaten(mySnake); //Continue checking whether food has been eaten
        } else
        {
            food = new Food(); //Draws a new Food object
        }
    }

    /**
     * Sets the timer for bonus points objects and manages their visibility and lifetime.
     */
    public void setBonusObjTimer(){
        if (bonusObj.liveOfObject) { //If bonus points object has not been eaten
            bonusObj.checkObjectPosition(food); //Checks whether object collides with Food objects
            bonusObj.draw(graphicsContext);
            bonusObj.eaten(mySnake, getGameCanvas()); //Continue checking whether object has been eaten

            long currentTime = System.currentTimeMillis();
            if (currentTime - bonusSpawnTime > getOnScreenTime()) { //If object stays longer than set on screen time
                bonusObj.liveOfObject = false; // Mark the bonusObj as no longer live
            }
        }
    }

    /**
     * Resets the game state, plays the game over sound effect, saves the final score, and transitions
     * to the ending scene.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void resetGame() throws IOException {
        MySnake.bodyPoints = new LinkedList<>();
        pause = false; //Resets the pausing state
        //Plays Game Over sound effect
        MusicPlayer.playSoundEffect("game_over.mp3");
        //Saves final score
        HighScoreManager.saveHighScore(StartFrameMain.getPlayerName(), mySnake.score, StartFrameMain.getCurrentMode());
        MyFrameController.setFinalScore(mySnake.score);
        //Sets Ending/Game Over scene music
        StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/Sounds/ending-scene-music.mp3"));
        StartFrameMain.setRoot("/EndingFrame");
        stopAnimation = true;
    }

    /**
     * Draws the snake, food, and bonus objects onto the screen and manages game state transitions.
     *
     * @param gc The GraphicsContext used for rendering.
     * @throws IOException          If an I/O error occurs.
     * @throws InterruptedException If the thread is interrupted.
     */
    public void drawSnakeObjects(GraphicsContext gc) throws IOException, InterruptedException {

        // Determine the state of the game.
        if (mySnake.liveOfObject && !pause) //if Snake is alive and game is not paused
        {
            //Clears the canvas before output another snake body
            clearCanvas();
            //Implement a timer so that object stays for a limited amount of time
            setBonusObjTimer();
        }
        else if (!mySnake.liveOfObject)
        {
            //Resets the game
            resetGame();
        }
        drawScore(gc);
    }

    /**
     * Handles key presses for snake movements, pause/resume, and returning to the main menu.
     *
     * @param event The KeyEvent representing the key press.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void handleKeyPress(KeyEvent event) throws IOException {
        // Handle key presses for snake movements
        if (controls.equals("left")) {
            mySnake.handleKeyPressLeft(event);
        } else if (controls.equals("right")) {
            mySnake.handleKeyPressRight(event);
        }
        if (event.getCode() == KeyCode.P) { //If 'P' is pressed
            togglePauseBtn();
        } else if (event.getCode() == KeyCode.H) { //If 'H' is pressed
            //Saves current score before exiting to main menu
            HighScoreManager.saveHighScore(StartFrameMain.getPlayerName(), mySnake.score, StartFrameMain.getCurrentMode());
            backToMain();
        }
    }

    /**
     * Creates and starts an AnimationTimer for continuous game updates and rendering.
     *
     * @return The AnimationTimer object.
     */
    public AnimationTimer animationTimer() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    if (!pause) {
                        drawSnakeObjects(graphicsContext);
                        pauseBoard.setVisible(false); // Hide the paused image
                        darkenedBgImg.setVisible(false);
                    }
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // Check the condition to stop the animation
                if (stopAnimation) {
                    stopAnimationTimer(this);
                }
            }
        };

        animationTimer.start();
        return animationTimer;
    }

    private void stopAnimationTimer(AnimationTimer animationTimer) {
        animationTimer.stop();
    }

    /**
     * Toggles the game pause state, updating the UI accordingly and managing music playback.
     * Called when 'P' key is pressed
     */
    @FXML
    public void togglePauseBtn() {
        if (pause) { //If user wants to resume game
            //Reset countdown time
            countdownSeconds = 3;
            // Start a new countdown when the game is resumed
            pauseBoard.setVisible(false); // Hide the paused image
            //Plays countdown sound effect
            MusicPlayer.playSoundEffect("countdown-audio.mp3");
            startCountdown();
        } else {
            pause = true;
            StartFrameMain.getCurrenMusic().pause();
            pauseImg.setImage(ImageUtil.images.get("resume-btn"));
            pauseBoard.setVisible(true); // Show the paused image
            darkenedBgImg.setVisible(true); // Show the paused background image
        }
    }

    /**
     * Starts a countdown when the 'P' key is pressed to resume the game.
     */
    private void startCountdown() {
        // Reset the countdown label and visibility
        countdownLabel.setVisible(true);
        countdownLabel.setText(Integer.toString(countdownSeconds));

        // Initialize the Timeline for the new countdown
        countdownTimeline = new Timeline();
        countdownTimeline.setCycleCount(countdownSeconds);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            countdownSeconds--;
            if (countdownSeconds > 0) {
                countdownLabel.setText(Integer.toString(countdownSeconds));
                MusicPlayer.playSoundEffect("countdown-audio.mp3");
            } else {
                // Resumes the game after the countdown
                countdownTimeline.stop();
                pause = false;
                StartFrameMain.getCurrenMusic().play();
                pauseImg.setImage(ImageUtil.images.get("pause-btn"));
                darkenedBgImg.setVisible(false);
                countdownLabel.setVisible(false);
            }
        });

        countdownTimeline.getKeyFrames().add(keyFrame);
        countdownTimeline.play();

    }

    /**
     * Returns to the main menu, saving the current score and transitioning to the main menu scene.
     * Called when 'H' key is pressed
     *
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    public void backToMain() throws IOException {
        stopAnimationTimer(animationTimer);
        //Changes to Main Menu music
        StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/Sounds/main-menu-music.mp3"));
        //Sets and loads Main Menu's FXML
        StartFrameMain.setRoot("/StartFrame");
        //Loads and plays button clicked sound effect
        MusicPlayer.playSoundEffect("Button Press Sound Effect.wav");
    }

}
