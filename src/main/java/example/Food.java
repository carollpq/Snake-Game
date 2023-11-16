package example;

import java.awt.Graphics;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Image;

public class Food extends SnakeObject
{
	public Food()	{
		this.liveOfObject = true;

		this.i = ImageUtil.images.get(String.valueOf(new Random().nextInt(10)));
		//Changed the names accordingly (RMB TO WRITE ABOUT IT IN COMMIT)
		this.widthOfSnake = i.getWidth(null);
		this.heightOfSnake = i.getHeight(null);

		this.xPosition = (int) (Math.random() * (870 - widthOfSnake + 10));
		this.yPosition = (int) (Math.random() * (560 - heightOfSnake - 40));
	}

	public void eaten(MySnake mySnake)	{

		//If the snake object touches the food object
		if (mySnake.getRectangle().intersects(this.getRectangle()) && liveOfObject && mySnake.liveOfObject)		{
			this.liveOfObject = false;
			mySnake.changeLength(mySnake.getLength() + 1);
			mySnake.score += 521;
		}
	}
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(i, xPosition, yPosition, null);
	}
}
