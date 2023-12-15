package example;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public abstract class SnakeObject //Represents the objects that will be drawn onto the game canvas
{
    int xPosition, yPosition, widthOfObj, heightOfObj;
    Image snakeBodyImg, foodImg;
    public boolean liveOfObject;


    public abstract void draw(GraphicsContext gc);

    public Rectangle2D getRectangle()
    {
        return new Rectangle2D(xPosition, yPosition, widthOfObj, heightOfObj);
    }
}
