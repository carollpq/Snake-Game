package example.Controller;

import example.Food;
import example.ImageUtil;
import example.Model.StartFrameMain;
import example.MusicPlayer;
import example.MySnake;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MyFrameController implements Initializable {

    @FXML
    private Canvas gameCanvas;

    @FXML
    private Label scoreLabel;

    @FXML
    private ImageView pauseImg;

    private GraphicsContext graphicsContext;

    private MySnake mySnake;

    private Food food;

    private boolean stopAnimation = false; // Flag to control the animation stopping condition
    private static AnimationTimer animationTimer;

    private static boolean pause = false; //Initialise pause button to false

    @FXML
    public void initialization() throws IOException, InterruptedException {
        graphicsContext = gameCanvas.getGraphicsContext2D();
        mySnake = new MySnake(100, 100);
        food = new Food();
        drawBgImg(graphicsContext);
        gameCanvas.setFocusTraversable(true);
        animationTimer = animationTimer();
    }

    public void drawScore(GraphicsContext gc)
    {
        Font font = Font.font("Sans Serif", FontWeight.BOLD, FontPosture.REGULAR, 30);
        gc.setFont(font);
        gc.setFill(Color.MAGENTA);
        gc.fillText("SCORE : " + mySnake.score, 20, 40);
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
        }
        else if (!mySnake.liveOfObject)
        {
            StartFrameMain.changeMusic(new MusicPlayer("src/main/resources/cw1setup/Sounds/ending-scene-music.mp3"));
            StartFrameMain.setRoot("/cw1setup/EndingFrame");
            stopAnimation = true;
        }
        drawScore(gc);
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        // Handle key press events
        mySnake.handleKeyPress(event);
    }

    public AnimationTimer animationTimer() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    drawBgImg(graphicsContext);
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void togglePauseBtn() {
        if (pause) {
            pause = false;
            pauseImg.setImage(ImageUtil.images.get("pause-btn"));
        } else {
            pause = true;
            pauseImg.setImage(ImageUtil.images.get("resume-btn"));
        }
        System.out.println("Pause button is pressed");
        //Add / change to a pause backdrop
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
}
