package example;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public abstract class SnakeObject
{
    //Attributes to initialize the Rectangle object
    int xPosition;
    int yPosition;
    Image snakeHeadImg;
    Image snakeBodyImg;

    Image foodImg;
    int widthOfSnake;
    int heightOfSnake;

    public boolean liveOfObject;


    public abstract void draw(GraphicsContext gc);

    public Bounds getRectangle()
    {
        return new Rectangle(xPosition, yPosition, widthOfSnake, heightOfSnake);
    }
}
