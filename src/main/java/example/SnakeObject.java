package example;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public abstract class SnakeObject
{
    //Attributes to initialize the Rectangle object
    int xPosition;
    int yPosition;
    //Image snakeHeadImg;
    Image snakeBodyImg;

    Image foodImg;
    int widthOfSnake;
    int heightOfSnake;

    public boolean liveOfObject;


    public abstract void draw(GraphicsContext gc);

    public Rectangle2D getRectangle()
    {
        return new Rectangle2D(xPosition, yPosition, widthOfSnake, heightOfSnake);
    }
}
