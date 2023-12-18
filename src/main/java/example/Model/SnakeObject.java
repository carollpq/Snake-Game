package example.Model;

import example.StartFrameMain;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;


/**
 * Abstract class representing objects that will be drawn onto the game canvas.
 */
public abstract class SnakeObject
{
    protected int xPosition;
    protected int yPosition;
    int widthOfObj;
    int heightOfObj;
    Image snakeBodyImg;
    Image foodImg;
    public boolean liveOfObject;
    final int HORIZONTAL_BUFFER = 30;
    final int VERTICAL_BUFFER = 60;
    final int HORIZONTAL_SHIFT = 30;
    final int VERTICAL_SHIFT = 55;


    /**
     * Draws the snake object on the game canvas using the provided GraphicsContext.
     *
     * @param gc The GraphicsContext used for drawing on the canvas.
     */
    public abstract void draw(GraphicsContext gc);

    /**
     * Retrieves the rectangle representing the bounds of the snake object.
     *
     * @return A Rectangle2D representing the bounds of the snake object.
     */
    public Rectangle2D getRectangle()
    {
        return new Rectangle2D(xPosition, yPosition, widthOfObj, heightOfObj);
    }


    /**
     * Generates a random X position within the specified bounds for the snake object.
     *
     * @return A random X position for the snake object.
     */
    public int generateRandomXPosition() {
        return (int) (Math.random() * (StartFrameMain.STAGE_WIDTH - widthOfObj - HORIZONTAL_BUFFER) + HORIZONTAL_SHIFT);
    }


    /**
     * Generates a random Y position within the specified bounds for the snake object.
     *
     * @return A random Y position for the snake object.
     */
    public int generateRandomYPosition() {
        return (int) (Math.random() * (StartFrameMain.STAGE_HEIGHT - heightOfObj - VERTICAL_BUFFER) + VERTICAL_SHIFT);
    }
}
