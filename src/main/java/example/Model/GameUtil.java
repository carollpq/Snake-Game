package example.Model;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class GameUtil
{
	public static Image getImage(String imagePath)
	{
		Image image;
		try
		{
			image = new Image(GameUtil.class.getResourceAsStream(imagePath));
			return image;
		} catch (Exception e)
		{
			System.err.println("ERROR : SPECIFIC IMAGE NOT FOUND !\n");
			e.printStackTrace();
			return null;
		}
	}

	public static Image rotateImage(final Image image, final int degree) {
		ImageView imageView = new ImageView(image);
		imageView.setRotate(degree);

		// Create a snapshot of the rotated ImageView
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT); // Set the background to be transparent
		WritableImage rotatedImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
		imageView.snapshot(params, rotatedImage);

		return rotatedImage;
	}
}
