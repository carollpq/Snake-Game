package example;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameUtil
{
	public static Image getImage(String imagePath)
	{
		Image image = null;
		try
		{
			image = new Image(GameUtil.class.getResourceAsStream(imagePath));
			return image; //DONE: put 'return' at the correct place
		} catch (Exception e)
		{
			System.err.println("ERROR : SPECIFIC IMAGE NOT FOUND !\n");
			e.printStackTrace();
			return null;
		}
	}

	public static ImageView rotateImage(final Image image, final int degree)
	{
		ImageView imageView = new ImageView(image);
		imageView.setRotate(degree);

		return imageView;
	}
}
