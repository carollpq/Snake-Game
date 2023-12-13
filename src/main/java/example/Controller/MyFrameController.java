package example.Controller;

import example.*;
import example.Model.StartFrameMain;
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

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;


public class MyFrameController implements Initializable {

    @FXML
    private Canvas gameCanvas;

    @FXML
    private Label scoreLabel;

    @FXML
    private ImageView pauseImg;

    @FXML
    private ImageView pauseBoard;

    @FXML
    private ImageView darkenedBgImg;

    @FXML
    private ImageView countDownBackDrop;

    @FXML
    private Label countdownLabel;

    private GraphicsContext graphicsContext;

    private MySnake mySnake;

    private Food food;

    private PowerUp bonusObj;

    private boolean stopAnimation = false; // Flag to control the animation stopping condition
    private static AnimationTimer animationTimer;

    private static boolean pause = false; //Initialise pause button to false

    private Timeline countdownTimeline;
    private int countdownSeconds = 4; // Set the desired countdown time in seconds

    private Timer bonusSpawnTimer;
    private TimerTask bonusSpawnTask;
    private long bonusSpawnTime;

    private int snakeSpeed;
    private int onScreenTime;

    @FXML
    public void initialization() throws IOException, InterruptedException {
        // Initialize the Timeline for countdown
        setSnakeSpeed(5);
        setOnScreenTime(4000);
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
                // Start the game after the countdown
                countdownTimeline.stop();
                try {
                    startGame();
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
        new AudioClip(
                getClass()
                        .getResource("/cw1setup/Sounds/game_start_post-timer.mp3")
                        .toExternalForm())
                .play();
        StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/cw1setup/Sounds/easy-mode-music.mp3"));
        countdownLabel.setVisible(false);
        countDownBackDrop.setVisible(false);
        graphicsContext = gameCanvas.getGraphicsContext2D();
        mySnake = new MySnake(100, 100, getSnakeSpeed());
        food = new Food();
        bonusObj = new PowerUp();
        // Initialize the bonusObj spawn timer and task if not already done
        if (bonusSpawnTimer == null) {
            bonusSpawnTimer = new Timer();
            scheduleBonusSpawn();
        }
        drawBgImg(graphicsContext);
        gameCanvas.setFocusTraversable(true);
        animationTimer = animationTimer();
        gameCanvas.requestFocus();
    }

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

    public void drawScore(GraphicsContext gc)
    {
        Font font = Font.font("Sans Serif", FontWeight.BOLD, FontPosture.REGULAR, 30);
        gc.setFont(font);
        gc.setFill(Color.BLACK);
        gc.fillText("SCORE : " + mySnake.score, 20, 40);
    }


    public MySnake getMySnake() {
        return mySnake;
    }

    public void drawBgImg(GraphicsContext gc) throws IOException, InterruptedException {

        // Determine the state of the game.
        if (mySnake.liveOfObject && !pause)
        {
            //Clears the canvas before output another snake body
            gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
            mySnake.draw(gc);
            if (food.liveOfObject)
            {
                food.draw(gc);
                food.eaten(mySnake);
            } else
            {
                food = new Food();
            }

            //Implement a timer so that object stays for a limited amount of time
            if (bonusObj.liveOfObject) {
                bonusObj.checkObjectPosition(food);
                bonusObj.draw(gc);
                bonusObj.eaten(mySnake, getGameCanvas());

                long currentTime = System.currentTimeMillis();
                if (currentTime - bonusSpawnTime > getOnScreenTime()) { // 4000 milliseconds = 4 seconds
                    bonusObj.liveOfObject = false; // Mark the bonusObj as no longer live
                }
            }
        }
        else if (!mySnake.liveOfObject)
        {
            MySnake.bodyPoints = new LinkedList<>();
            new AudioClip(
                    getClass()
                            .getResource("/cw1setup/Sounds/game_over.mp3")
                            .toExternalForm())
                    .play();
            StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/cw1setup/Sounds/ending-scene-music.mp3"));
            StartFrameMain.setRoot("/cw1setup/EndingFrame");
            stopAnimation = true;
        }
        drawScore(gc);
    }

    @FXML
    public void handleKeyPress(KeyEvent event) throws IOException {
        // Handle key press events
        mySnake.handleKeyPress(event);
        if (event.getCode() == KeyCode.P) {
            togglePauseBtn();
        } else if (event.getCode() == KeyCode.H) {
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

    // Method to stop the animation timer
    private void stopAnimationTimer(AnimationTimer animationTimer) {
        animationTimer.stop();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initialization();
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
    public void togglePauseBtn() {
        if (pause) {
            //Reset countdown time
            countdownSeconds = 3;
            // Start a new countdown when the game is resumed
            pauseBoard.setVisible(false); // Hide the paused image
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
            darkenedBgImg.setVisible(true);
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
                // Start the game after the countdown
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
    public void backToMain() throws IOException {
        stopAnimationTimer(animationTimer);
        StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/cw1setup/Sounds/main-menu-music.mp3"));
        StartFrameMain.setRoot("/cw1setup/StartFrame");
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
}
