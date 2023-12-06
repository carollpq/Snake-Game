package example.Controller;

import example.Food;
import example.Frame;
import example.MusicPlayer;
import example.MySnake;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class MyFrameController implements Frame {
    @FXML
    private Canvas gameCanvas;

    @FXML
    private Label scoreLabel;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private ImageView failImage;

    private GraphicsContext graphicsContext;  // Add this line

    private MySnake mySnake;

    private Food food;

    private MusicPlayer currentMusic;

    public void drawScore(GraphicsContext gc)
    {
        Font font = Font.font("Sans Serif", FontWeight.BOLD, FontPosture.REGULAR, 30);
        gc.setFont(font);
        gc.setFill(Color.MAGENTA);
        gc.fillText("SCORE : " + mySnake.score, 20, 40);
    }

    @Override
    public void drawBgImg(GraphicsContext gc)
    {
        //super.draw(gc);
        gc.drawImage(backgroundImage.getImage(), 0, 0);

        // Determine the state of the game.
        if (mySnake.liveOfObject)
        {
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
        else
        {
            //gc.drawImage(failImage.getImage(), 0, 0);
            currentMusic.stopMusic();
        }
        drawScore(gc);
    }

    // Example:
    public void initialize() {
        // Initialize your JavaFX components and set up event handlers
        // For example, set up key event handling and other initialization code.
        mySnake = new MySnake(100, 100);
        food = new Food();

        // Get the GraphicsContext from the canvas
        graphicsContext = gameCanvas.getGraphicsContext2D();
        currentMusic = new MusicPlayer("src/main/resources/cw1setup/Sounds/easy-mode-music.mp3");
        currentMusic.play();
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
                drawBgImg(graphicsContext);
            }
        };

        animationTimer.start();
    }
}
