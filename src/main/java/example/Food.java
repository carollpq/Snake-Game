package example;

import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public class Food extends SnakeObject
{
	public Food()	{
		this.liveOfObject = true;

		this.foodImg = ImageUtil.images.get(String.valueOf(new Random().nextInt(10)));
		//Changed the names accordingly (RMB TO WRITE ABOUT IT IN COMMIT)
		this.widthOfSnake = (int) foodImg.getWidth(); //DONE: Removed unnecessary parameter
		this.heightOfSnake = (int) foodImg.getHeight();

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
	public void draw(GraphicsContext g)
	{
		g.drawImage(foodImg, xPosition, yPosition, null);
	}
}
