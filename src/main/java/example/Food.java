package example;

import java.awt.Graphics;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Image;

public class Food extends MyFrame.SnakeObject
{
	public Food()	{
		this.live_of_object = true;

		this.i = ImageUtil.images.get(String.valueOf(new Random().nextInt(10)));
		//Changed the names accordingly (RMB TO WRITE ABOUT IT IN COMMIT)
		this.width_of_snake = i.getWidth(null);
		this.height_of_snake = i.getHeight(null);

		this.x_position = (int) (Math.random() * (870 - width_of_snake + 10));
		this.y_position = (int) (Math.random() * (560 - height_of_snake - 40));
	}

	public void eaten(MyFrame.MySnake mySnake)	{

		//If the snake object touches the food object
		if (mySnake.getRectangle().intersects(this.getRectangle()) && live_of_object && mySnake.live_of_object)		{
			this.live_of_object = false; //The 'live'
			mySnake.changeLength(mySnake.getLength() + 1);
			mySnake.score += 521;
		}
	}
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(i, x_position, y_position, null);
	}
}
