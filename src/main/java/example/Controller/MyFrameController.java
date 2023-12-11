package example.Controller;

import example.Food;
import example.Model.StartFrameMain;
import example.MusicPlayer;
import example.MySnake;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
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

    private GraphicsContext graphicsContext;

    private MySnake mySnake;

    private Food food;

    private boolean stopAnimation = false; // Flag to control the animation stopping condition

    @FXML
    public void initialization() throws IOException, InterruptedException {
        if (gameCanvas == null) {
            System.out.println("gameCanvas is null in MyFrameController.initialize()");
        }
        graphicsContext = gameCanvas.getGraphicsContext2D();
        mySnake = new MySnake(100, 100);
        food = new Food();
        drawBgImg(graphicsContext);
        animationTimer();
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
        if (mySnake.liveOfObject)
        {
            this.mySnake.draw(gc);
            if (food.liveOfObject)
            {
                food.draw(gc);
                food.eaten(mySnake);
            } else
            {
                food = new Food();
            }
        }
        else
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

    public void animationTimer() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                mySnake.move();
                mySnake.draw(graphicsContext);
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
}
