package example.Model.GameObjects.PowerUps;

import example.Model.GameObjects.Food;
import example.Model.GameObjects.Snake;
import example.Model.GameObjects.SnakeObject;
import example.Model.Utilities.ImageUtil;
import example.Model.Utilities.MusicPlayer;
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

/**
 * Represents a power-up object in the Snake game.
 */
public abstract class PowerUp extends SnakeObject {
    private int scoreImplement;

    /**
     * Constructs a PowerUp object with a given image index.
     *
     * @param r The index used to obtain the image for the PowerUp object.
     */
    public PowerUp(int r) {
        this.liveOfObject = true;
        this.foodImg = ImageUtil.images.get(String.valueOf(r));
        this.widthOfObj = (int) foodImg.getWidth();
        this.heightOfObj = (int) foodImg.getHeight();
        this.xPosition = generateRandomXPosition();
        this.yPosition = generateRandomYPosition();
    }

    /**
     * Draws the PowerUp object on the game canvas using the provided GraphicsContext.
     *
     * @param gc The GraphicsContext used for drawing on the canvas.
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(foodImg, xPosition, yPosition);
    }

    /**
     * Ensures that the generated bonus point object does not overlap with the Food object.
     *
     * @param food The Food object used for checking overlap.
     */
    public void checkObjectPosition(Food food) {
        //Regenerates x position when current object overlaps with Food object
        if (Math.abs(this.xPosition - food.xPosition) < 10) {
            this.xPosition = generateRandomXPosition();
        }
        //Regenerates y position when current object overlaps with Food object
        if (Math.abs(this.yPosition - food.yPosition) < 10) {
            this.yPosition = generateRandomYPosition();
        }
    }
    /**
     * Checks if the Snake overlaps with a power-up and handles the interaction.
     *
     * @param snake    The Snake object that interacts with the power-up.
     * @param gameCanvas The Canvas on which the game is drawn.
     * @return True if the Snake overlaps with the power-up, false otherwise.
     */
    public boolean handleSnakeTouch(Snake snake, Canvas gameCanvas){
        if (snake.getRectangle().intersects(this.getRectangle()) && liveOfObject && snake.liveOfObject) {
            this.liveOfObject = false;
            snake.changeLength(snake.getLength() + 1);
            snake.score += scoreImplement;
            MusicPlayer.playSoundEffect("power-up-sparkle.mp3");
            return true;
        }
        return false;
    }

    /**
     * Displays a bonus message on the game canvas.
     *
     * @param message    The message to be displayed.
     * @param x          The x-coordinate of the message.
     * @param y          The y-coordinate of the message.
     * @param gameCanvas The Canvas on which the game is drawn.
     */
    protected void displayBonusMessage(String message, double x, double y, Canvas gameCanvas) {
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

    /**
     * Handles the logic when the PowerUp object is eaten by the Snake.
     *
     * @param snake    The Snake object that interacts with the power-up.
     * @param gameCanvas The Canvas on which the game is drawn.
     */
    public abstract void eaten(Snake snake, Canvas gameCanvas);

    /**
     * Sets the score increment for the power-up.
     *
     * @param scoreImplement The score increment value.
     */
    public void setScoreImplement(int scoreImplement) {
        this.scoreImplement = scoreImplement;
    }

}

