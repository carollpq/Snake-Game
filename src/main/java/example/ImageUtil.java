package example;

import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;

public class ImageUtil
{
	public static Map<String, Image> images = new HashMap<>();

	static
	{
		// Snake
		images.put("snake-head-right", GameUtil.getImage("/example/Img/snake-head-right.png"));
		images.put("snake-body", GameUtil.getImage("/example/Img/snake-body.png"));
		// Food objects
		images.put("0", GameUtil.getImage("/example/Img/food-kiwi.png"));
		images.put("1", GameUtil.getImage("/example/Img/food-lemon.png"));
		images.put("2", GameUtil.getImage("/example/Img/food-litchi.png"));
		images.put("3", GameUtil.getImage("/example/Img/food-mango.png"));
		images.put("4", GameUtil.getImage("/example/Img/food-apple.png"));
		images.put("5", GameUtil.getImage("/example/Img/food-banana.png"));
		images.put("6", GameUtil.getImage("/example/Img/food-blueberry.png"));
		images.put("7", GameUtil.getImage("/example/Img/food-cherry.png"));
		images.put("8", GameUtil.getImage("/example/Img/food-durian.png"));
		images.put("9", GameUtil.getImage("/example/Img/food-grape.png"));
		images.put("10", GameUtil.getImage("/example/Img/food-grapefruit.png"));
		images.put("11", GameUtil.getImage("/example/Img/food-peach.png"));
		images.put("12", GameUtil.getImage("/example/Img/food-pear.png"));
		images.put("13", GameUtil.getImage("/example/Img/food-orange.png"));
		images.put("14", GameUtil.getImage("/example/Img/food-pineapple.png"));
		images.put("15", GameUtil.getImage("/example/Img/food-strawberry.png"));
		images.put("16", GameUtil.getImage("/example/Img/food-watermelon.png"));
		images.put("UI-background", GameUtil.getImage("/example/Img/UI-background.png"));
		images.put("game-scene-01", GameUtil.getImage("/example/Img/game-scene-01.jpg"));
	}
}
