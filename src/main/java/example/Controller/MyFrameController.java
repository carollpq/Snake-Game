package example.Controller;

import example.*;
import example.Model.HighScoreManager;
import example.Model.ImageUtil;
import example.Model.MusicPlayer;
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
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;


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
    private static boolean pause = false; //Keeps track whether the game is paused
    private Timeline countdownTimeline;
    private int countdownSeconds = 4; // Set the desired countdown time in seconds

    private Timer bonusSpawnTimer;
    private TimerTask bonusSpawnTask;
    private long bonusSpawnTime;

    private int snakeSpeed;
    private int onScreenTime; //Time for bonus points objects to stay on screen
    private static int finalScore; //Records the final score obtained

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
                new AudioClip(
                        getClass()
                                .getResource("/cw1setup/Sounds/countdown-audio.mp3")
                                .toExternalForm())
                        .play();
            } else {
                // Stops the countdown timer after countdown ends
                countdownTimeline.stop();
                try {
                    startGame(); //Start the game after the countdown
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        countdownTimeline.getKeyFrames().add(keyFrame);
        countdownTimeline.play();

    }

    public void startGame() throws IOException, InterruptedException {
        //Plays Start Game sound effect
        new AudioClip(
                getClass()
                        .getResource("/cw1setup/Sounds/game_start_post-timer.mp3")
                        .toExternalForm())
                .play();
        countdownLabel.setVisible(false); //Hides previous countdown label
        countDownBackDrop.setVisible(false); //Hides previous background image used for countdown
        graphicsContext = gameCanvas.getGraphicsContext2D();
        mySnake = new MySnake(100, 100, getSnakeSpeed()); //Creates instance of 'Snake'
        food = new Food(); //Creates instance of Food object
        bonusObj = new PowerUp(); //Creates instance of bonus points object
        // Initialize the bonusObj spawn timer and task if not already done
        if (bonusSpawnTimer == null) {
            bonusSpawnTimer = new Timer();
            scheduleBonusSpawn();
        }
        drawBgImg(graphicsContext);
        gameCanvas.setFocusTraversable(true);
        animationTimer = animationTimer();
        gameCanvas.requestFocus(); //Ensure the buttons and objects on gameCanvas detect key presses
        playModeMusic(StartFrameMain.getCurrentMode()); //Plays the music for the game mode
    }

    public void playModeMusic(String gameMode) {
        StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/cw1setup/Sounds/"+ gameMode +"-mode-music.mp3"));
    }

    //Schedules the spawning of bonus points objects onto screen at random time
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
                        bonusObj = new PowerUp();
                        bonusSpawnTime = System.currentTimeMillis(); // Set the bonus spawn time
                    }
                    scheduleBonusSpawn(); // Reschedule for the next random interval
                });
            }
        };

        bonusSpawnTimer.schedule(bonusSpawnTask, delay, period);
    }


    private int getRandomDelay() {
        // Get a random delay between 5 and 15 seconds (adjust as needed)
        return (int) (Math.random() * 10000) + 5000;
    }

    //Draws the score labels onto screen
    public void drawScore(GraphicsContext gc)
    {
        Font font = Font.font("Sans Serif", FontWeight.BOLD, FontPosture.REGULAR, 30);
        gc.setFont(font);
        gc.setFill(Color.BLACK);
        gc.fillText("SCORE : " + mySnake.score, 20, 40);
    }

    //Draws MySnake, Food, and PowerUp objects onto screen
    public void drawBgImg(GraphicsContext gc) throws IOException, InterruptedException {

        // Determine the state of the game.
        if (mySnake.liveOfObject && !pause) //if Snake is alive and game is not paused
        {
            //Clears the canvas before output another snake body
            gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
            mySnake.draw(gc); //Draws snake onto canvas
            if (food.liveOfObject) //If Food object has not been eaten
            {
                food.draw(gc); //Continue drawing current Food at current position
                food.eaten(mySnake); //Continue checking whether food has been eaten
            } else
            {
                food = new Food(); //Draws a new Food object
            }

            //Implement a timer so that object stays for a limited amount of time
            if (bonusObj.liveOfObject) { //If bonus points object has not been eaten
                bonusObj.checkObjectPosition(food); //Checks whether object collides with Food objects
                bonusObj.draw(gc);
                bonusObj.eaten(mySnake, getGameCanvas()); //Continue checking whether object has been eaten

                long currentTime = System.currentTimeMillis();
                if (currentTime - bonusSpawnTime > getOnScreenTime()) { //If object stays longer than set on screen time
                    bonusObj.liveOfObject = false; // Mark the bonusObj as no longer live
                }
            }
        }
        else if (!mySnake.liveOfObject)
        {
            //Resets the game
            MySnake.bodyPoints = new LinkedList<>();
            pause = false; //Resets the pausing state
            //Plays Game Over sound effect
            new AudioClip(
                    getClass()
                            .getResource("/cw1setup/Sounds/game_over.mp3")
                            .toExternalForm())
                    .play();
            //Saves final score
            HighScoreManager.saveHighScore(mySnake.score, StartFrameMain.getCurrentMode());
            MyFrameController.setFinalScore(mySnake.score);
            //Sets Ending/Game Over scene music
            StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/cw1setup/Sounds/ending-scene-music.mp3"));
            StartFrameMain.setRoot("/cw1setup/EndingFrame");
            stopAnimation = true;
        }
        drawScore(gc);
    }

    @FXML
    public void handleKeyPress(KeyEvent event) throws IOException {
        // Handle key presses for snake movements
        mySnake.handleKeyPress(event);
        if (event.getCode() == KeyCode.P) { //If 'P' is pressed
            togglePauseBtn();
        } else if (event.getCode() == KeyCode.H) { //If 'H' is pressed
            backToMain();
        }
    }

    public AnimationTimer animationTimer() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    if (!pause) {
                        drawBgImg(graphicsContext);
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

    @FXML
    //Called when 'P' key is pressed
    public void togglePauseBtn() {
        if (pause) { //If user wants to resume game
            //Reset countdown time
            countdownSeconds = 3;
            // Start a new countdown when the game is resumed
            pauseBoard.setVisible(false); // Hide the paused image
            //Plays countdown sound effect
            new AudioClip(
                    getClass()
                            .getResource("/cw1setup/Sounds/countdown-audio.mp3")
                            .toExternalForm())
                    .play();
            startCountdown();
        } else {
            pause = true;
            StartFrameMain.getCurrenMusic().pause();
            pauseImg.setImage(ImageUtil.images.get("resume-btn"));
            pauseBoard.setVisible(true); // Show the paused image
            darkenedBgImg.setVisible(true); // Show the paused background image
        }
    }

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
                new AudioClip(
                        getClass()
                                .getResource("/cw1setup/Sounds/countdown-audio.mp3")
                                .toExternalForm())
                        .play();
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

    @FXML
    //Called when 'H' key is pressed
    public void backToMain() throws IOException {
        stopAnimationTimer(animationTimer);
        //Changes to Main Menu music
        StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/cw1setup/Sounds/main-menu-music.mp3"));
        //Sets and loads Main Menu's FXML
        StartFrameMain.setRoot("/cw1setup/StartFrame");
        //Loads and plays button clicked sound effect
        new AudioClip(
                getClass()
                        .getResource("/cw1setup/Sounds/Button Press Sound Effect.wav")
                        .toExternalForm())
                .play();
    }

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
}
