package example;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class ImageUtil
{
	public static Map<String, Image> images = new HashMap<>();

	static
	{
		// Snake
		images.put("snake-head-right", GameUtil.getImage("/main/java/example/snake-head-right.png"));
		images.put("snake-body", GameUtil.getImage("/main/java/example/snake-body.png"));
		// Food objects
		images.put("0", GameUtil.getImage("/main/java/example/food-kiwi.png"));
		images.put("1", GameUtil.getImage("/main/java/example/food-lemon.png"));
		images.put("2", GameUtil.getImage("/main/java/example/food-litchi.png"));
		images.put("3", GameUtil.getImage("/main/java/example/food-mango.png"));
		images.put("4", GameUtil.getImage("/main/java/example/food-apple.png"));
		images.put("5", GameUtil.getImage("/main/java/example/food-banana.png"));
		images.put("6", GameUtil.getImage("/main/java/example/food-blueberry.png"));
		images.put("7", GameUtil.getImage("/main/java/example/food-cherry.png"));
		images.put("8", GameUtil.getImage("/main/java/example/food-durian.png"));
		images.put("9", GameUtil.getImage("/main/java/example/food-grape.png"));
		images.put("10", GameUtil.getImage("/main/java/example/food-grapefruit.png"));
		images.put("11", GameUtil.getImage("/main/java/example/food-peach.png"));
		images.put("12", GameUtil.getImage("/main/java/example/food-pear.png"));
		images.put("13", GameUtil.getImage("/main/java/example/food-orange.png"));
		images.put("14", GameUtil.getImage("/main/java/example/food-pineapple.png"));
		images.put("15", GameUtil.getImage("/main/java/example/food-strawberry.png"));
		images.put("16", GameUtil.getImage("/main/java/example/food-watermelon.png"));
		images.put("UI-background", GameUtil.getImage("/main/java/example/UI-background.png"));
		images.put("game-scene-01", GameUtil.getImage("/main/java/example/game-scene-01.jpg"));
	}
}
