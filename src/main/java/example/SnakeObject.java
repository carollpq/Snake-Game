package example;

import java.awt.*;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

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

    public Rectangle getRectangle()
    {
        return new Rectangle(xPosition, yPosition, widthOfSnake, heightOfSnake);
    }
}
