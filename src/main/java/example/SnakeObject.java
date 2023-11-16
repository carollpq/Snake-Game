package example;

import java.awt.*;

import javafx.scene.image.Image;


public abstract class SnakeObject
{
    //Attributes to initialize the Rectangle object
    int x_position;
    int y_position;
    Image snakeHeadImg;
    Image snake_body_img;
    int width_of_snake;
    int height_of_snake;

    public boolean live_of_object;


    public abstract void draw(Graphics g);

    public Rectangle getRectangle()
    {
        return new Rectangle(x_position, y_position, width_of_snake, height_of_snake);
    }
}
