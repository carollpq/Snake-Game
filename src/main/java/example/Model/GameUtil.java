package example.Model;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


/**
 * Utility class for common game-related operations.
 *
 * <p>This class provides methods for loading images and rotating them, particularly useful
 * for graphics operations in the Snake Game. It includes error handling for image loading
 * to handle cases where the specified image is not found.</p>
 *
 * @author Carolina Lee Pei Qian
 * @version 1.0
 */

public class GameUtil
{
	/**
	 * Loads an image from the specified resource path.
	 *
	 * @param imagePath The path to the image resource.
	 * @return The loaded Image object, or null if the image is not found.
	 */
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

	/**
	 * Rotates the given image by the specified degree.
	 *
	 * @param image  The image to rotate.
	 * @param degree The degree by which to rotate the image.
	 * @return The rotated Image object.
	 */
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
