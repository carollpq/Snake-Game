package example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.util.Random;

public class PowerUp extends SnakeObject {
    private int scoreImplement;
    private int powerUp;

    public PowerUp() {
        this.liveOfObject = true;
        this.powerUp = new Random().nextInt(4) + 17;
        this.foodImg = ImageUtil.images.get(String.valueOf(powerUp));
        checkPowerUpType();

        this.widthOfSnake = (int) foodImg.getWidth();
        this.heightOfSnake = (int) foodImg.getHeight();
        this.xPosition = (int) (Math.random() * (860 - widthOfSnake - 30) + 30);
        this.yPosition = (int) (Math.random() * (495 - heightOfSnake - 60) + 55);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(foodImg, xPosition, yPosition);
    }

    public void checkPowerUpType() {
        if (powerUp == 17) {
            setScoreImplement(3);
        } else if (powerUp == 18) {
            setScoreImplement(5);
        } else if (powerUp == 19) {
            setScoreImplement(8);
        } else {
            setScoreImplement(10);
        }
    }

    public void checkObjectPosition(Food food) {
        if (Math.abs(this.xPosition - food.xPosition) < 10) {
            this.xPosition = (int) (Math.random() * (860 - widthOfSnake - 30) + 30);
        }
        if (Math.abs(this.yPosition - food.yPosition) < 10) {
            this.yPosition = (int) (Math.random() * (495 - heightOfSnake - 60) + 55);
        }
    }

    public void eaten(MySnake mySnake, Canvas gameCanvas)	{

        //If the snake object touches the food object
        if (mySnake.getRectangle().intersects(this.getRectangle()) && liveOfObject && mySnake.liveOfObject)		{
            this.liveOfObject = false;
            mySnake.changeLength(mySnake.getLength() + 1);
            mySnake.score += scoreImplement;
            new AudioClip(
                    getClass()
                            .getResource("/cw1setup/Sounds/power-up-sparkle.mp3")
                            .toExternalForm())
                    .play();
            //Display how many points gained
            if (scoreImplement == 3) {
                displayBonusMessage("+3 points! Yippee!", this.xPosition, this.yPosition, gameCanvas);
            } else if (scoreImplement == 5) {
                displayBonusMessage("+5 points! Good Job!", this.xPosition, this.yPosition, gameCanvas);
            } else if (scoreImplement == 8) {
                displayBonusMessage("+8 points! Brilliant!", this.xPosition, this.yPosition, gameCanvas);
            } else if (scoreImplement == 10) {
                displayBonusMessage("+10 points! WOO!!", this.xPosition, this.yPosition, gameCanvas);
            }
        }
    }

    private void displayBonusMessage(String message, double x, double y, Canvas gameCanvas) {
        Text bonusMessage = new Text(message);
        bonusMessage.setFont(Font.font("Sans Serif", FontWeight.BOLD, 20));
        bonusMessage.setFill(Color.BLACK);
        bonusMessage.setTextAlignment(TextAlignment.CENTER);

        // Set the position of the bonus message near the bonusObj
        bonusMessage.setX(x);
        bonusMessage.setY(y);

        // Get the parent of the Canvas (which is the AnchorPane)
        Parent parent = gameCanvas.getParent();

        if (parent instanceof Pane) {
            Pane anchorPane = (Pane) parent;

            // Add the bonus message to the AnchorPane
            anchorPane.getChildren().add(bonusMessage);

            // Use a Timeline or AnimationTimer to make the message disappear after a short delay
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), e -> anchorPane.getChildren().remove(bonusMessage))
            );
            timeline.play();
        }
    }

    public void setScoreImplement(int scoreImplement) {
        this.scoreImplement = scoreImplement;
    }
}
