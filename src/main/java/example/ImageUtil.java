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
		images.put("snake-head-right", GameUtil.getImage("/cw1setup/Img/snake-head-right.png"));
		images.put("snake-body", GameUtil.getImage("/cw1setup/Img/snake-body.png"));
		// Food objects
		images.put("0", GameUtil.getImage("/cw1setup/Img/food-kiwi.png"));
		images.put("1", GameUtil.getImage("/cw1setup/Img/food-lemon.png"));
		images.put("2", GameUtil.getImage("/cw1setup/Img/food-litchi.png"));
		images.put("3", GameUtil.getImage("/cw1setup/Img/food-mango.png"));
		images.put("4", GameUtil.getImage("/cw1setup/Img/food-apple.png"));
		images.put("5", GameUtil.getImage("/cw1setup/Img/food-banana.png"));
		images.put("6", GameUtil.getImage("/cw1setup/Img/food-blueberry.png"));
		images.put("7", GameUtil.getImage("/cw1setup/Img/food-cherry.png"));
		images.put("8", GameUtil.getImage("/cw1setup/Img/food-durian.png"));
		images.put("9", GameUtil.getImage("/cw1setup/Img/food-grape.png"));
		images.put("10", GameUtil.getImage("/cw1setup/Img/food-grapefruit.png"));
		images.put("11", GameUtil.getImage("/cw1setup/Img/food-peach.png"));
		images.put("12", GameUtil.getImage("/cw1setup/Img/food-pear.png"));
		images.put("13", GameUtil.getImage("/cw1setup/Img/food-orange.png"));
		images.put("14", GameUtil.getImage("/cw1setup/Img/food-pineapple.png"));
		images.put("15", GameUtil.getImage("/cw1setup/Img/food-strawberry.png"));
		images.put("16", GameUtil.getImage("/cw1setup/Img/food-watermelon.png"));
		//Background images
		images.put("UI-background", GameUtil.getImage("/cw1setup/Img/UI-background.png"));
		images.put("game-scene-01", GameUtil.getImage("/cw1setup/Img/game-scene-01.jpg"));
		images.put("main-menu-scene", GameUtil.getImage("/cw1setup/Img/1629702755362_84a39ede3284dfcaa71703c98647a8f5.jpg"));
	}
}
