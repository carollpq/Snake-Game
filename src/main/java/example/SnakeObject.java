package example;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public abstract class SnakeObject //Represents the objects that will be drawn onto the game canvas
{
    int xPosition;
    int yPosition;
    int widthOfObj;
    int heightOfObj;
    Image snakeBodyImg;
    Image foodImg;
    public boolean liveOfObject;
    final int HORIZONTAL_BUFFER = 30;
    final int VERTICAL_BUFFER = 60;
    final int HORIZONTAL_SHIFT = 30;
    final int VERTICAL_SHIFT = 55;


    public abstract void draw(GraphicsContext gc);

    public Rectangle2D getRectangle()
    {
        return new Rectangle2D(xPosition, yPosition, widthOfObj, heightOfObj);
    }

    public int generateRandomXPosition() {
        return (int) (Math.random() * (StartFrameMain.STAGE_WIDTH - widthOfObj - HORIZONTAL_BUFFER) + HORIZONTAL_SHIFT);
    }

    public int generateRandomYPosition() {
        return (int) (Math.random() * (StartFrameMain.STAGE_HEIGHT - heightOfObj - VERTICAL_BUFFER) + VERTICAL_SHIFT);
    }
}
