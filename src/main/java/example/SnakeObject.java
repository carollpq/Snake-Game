package example;

import java.awt.*;

import javafx.scene.image.Image;


public abstract class SnakeObject
{
    //Attributes to initialize the Rectangle object
    int xPosition;
    int yPosition;
    Image snakeHeadImg;
    Image snakeBodyImg;
    int widthOfSnake;
    int heightOfSnake;

    public boolean liveOfObject;


    public abstract void draw(Graphics g);

    public Rectangle getRectangle()
    {
        return new Rectangle(xPosition, yPosition, widthOfSnake, heightOfSnake);
    }
}
