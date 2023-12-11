package example;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;

public class Food extends SnakeObject
{
	private final int SCORE_INCREMENT = 1;
	public Food()	{
		this.liveOfObject = true;

		this.foodImg = ImageUtil.images.get(String.valueOf(new Random().nextInt(10)));
		this.widthOfSnake = (int) foodImg.getWidth();
		this.heightOfSnake = (int) foodImg.getHeight();

		this.xPosition = (int) (Math.random() * (870 - widthOfSnake + 10));
		this.yPosition = (int) (Math.random() * (560 - heightOfSnake - 40));
	}

	public void eaten(MySnake mySnake)	{

		//If the snake object touches the food object
		if (mySnake.getRectangle().intersects(this.getRectangle()) && liveOfObject && mySnake.liveOfObject)		{
			this.liveOfObject = false;
			mySnake.changeLength(mySnake.getLength() + 1);
			mySnake.score += SCORE_INCREMENT;
		}
	}
	@Override
	public void draw(GraphicsContext g)
	{
		g.drawImage(foodImg, xPosition, yPosition);
	}
}
