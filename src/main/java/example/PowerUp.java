package example;

import example.Model.ImageUtil;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import javafx.scene.media.AudioClip;

import java.util.Random;

public abstract class PowerUp extends SnakeObject {
    private int scoreImplement;
    private int powerUp;

    public PowerUp(int r) {
        this.liveOfObject = true;
        this.foodImg = ImageUtil.images.get(String.valueOf(r));
        this.widthOfObj = (int) foodImg.getWidth();
        this.heightOfObj = (int) foodImg.getHeight();
        this.xPosition = (int) (Math.random() * (860 - widthOfObj - 30) + 30);
        this.yPosition = (int) (Math.random() * (495 - heightOfObj - 60) + 55);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(foodImg, xPosition, yPosition);
    }
    //Makes sure the generated bonus point object does not stack on top of Food object
    public void checkObjectPosition(Food food) {
        //Regenerates x position when current object overlaps with Food object
        if (Math.abs(this.xPosition - food.xPosition) < 10) {
            this.xPosition = (int) (Math.random() * (860 - widthOfObj - 30) + 30);
        }
        //Regenerates y position when current object overlaps with Food object
        if (Math.abs(this.yPosition - food.yPosition) < 10) {
            this.yPosition = (int) (Math.random() * (495 - heightOfObj - 60) + 55);
        }
    }
    // check if snake overlaps with a power up
    public boolean handleSnakeTouch(MySnake mySnake, Canvas gameCanvas){
        if (mySnake.getRectangle().intersects(this.getRectangle()) && liveOfObject && mySnake.liveOfObject) {
            this.liveOfObject = false;
            mySnake.changeLength(mySnake.getLength() + 1);
            mySnake.score += scoreImplement;
            new AudioClip(
                    getClass()
                            .getResource("/cw1setup/Sounds/power-up-sparkle.mp3")
                            .toExternalForm())
                    .play();
            return true;
        }
        return false;
    }
    void displayBonusMessage(String message, double x, double y, Canvas gameCanvas) {
        Text bonusMessage = new Text(message);
        bonusMessage.setFont(Font.font("Sans Serif", FontWeight.BOLD, 20));
        bonusMessage.setFill(Color.BLACK);
        bonusMessage.setTextAlignment(TextAlignment.CENTER);

        // Set the position of the bonus message near the bonusObj
        bonusMessage.setX(x);
        bonusMessage.setY(y);

        // Get the parent of the Canvas (which is the AnchorPane)
        Parent parent = gameCanvas.getParent();

        if (parent instanceof Pane anchorPane) {

            // Add the bonus message to the AnchorPane
            anchorPane.getChildren().add(bonusMessage);

            // Use a Timeline or AnimationTimer to make the message disappear after a short delay
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), e -> anchorPane.getChildren().remove(bonusMessage))
            );
            timeline.play();
        }
    }

    public abstract void eaten(MySnake mySnake, Canvas gameCanvas);

    public void setScoreImplement(int scoreImplement) {
        this.scoreImplement = scoreImplement;
    }

}

