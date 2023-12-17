package example.Model;

import example.Model.GameUtil;
import javafx.application.Platform;
import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;

public class ImageUtil
{
	public static Map<String, Image> images = new HashMap<>();

	// Initialize the images in a separate method
	public static void initializeImages() {
		// Snake
		images.put("snake-head-right", GameUtil.getImage("/cw1setup/Img/snake-head-right.png"));
		images.put("snake-body", GameUtil.getImage("/cw1setup/Img/snake-body.png"));
		images.put("snake-head-up", GameUtil.rotateImage(GameUtil.getImage("/cw1setup/Img/snake-head-right.png"), -90));
		images.put("snake-head-down", GameUtil.rotateImage(GameUtil.getImage("/cw1setup/Img/snake-head-right.png"), 90));
		images.put("snake-head-left", GameUtil.rotateImage(GameUtil.getImage("/cw1setup/Img/snake-head-right.png"), -180));
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
		images.put("17", GameUtil.getImage("/cw1setup/Img/topaz.png"));
		images.put("18", GameUtil.getImage("/cw1setup/Img/diamond.png"));
		images.put("19", GameUtil.getImage("/cw1setup/Img/garnet.png"));
		images.put("20", GameUtil.getImage("/cw1setup/Img/sapphire.png"));
		//Background images
		images.put("main-menu-scene", GameUtil.getImage("/cw1setup/Img/1629702755362_84a39ede3284dfcaa71703c98647a8f5.jpg"));
		//Button images
		images.put("resume-btn", GameUtil.getImage("/cw1setup/Img/resume-btn.png"));
		images.put("pause-btn", GameUtil.getImage("/cw1setup/Img/pause-btn-modified.png"));
		images.put("start-btn", GameUtil.getImage("/cw1setup/Img/start-btn-modified.png"));
		images.put("highscore-btn", GameUtil.getImage("/cw1setup/Img/high-score-modified.png"));
		images.put("instructions-btn", GameUtil.getImage("/cw1setup/Img/instructions-btn.png"));
		images.put("easy-btn", GameUtil.getImage("/cw1setup/Img/easy-btn-modified.png"));
		images.put("medium-btn", GameUtil.getImage("/cw1setup/Img/medium-btn-modified.png"));
		images.put("hard-btn", GameUtil.getImage("/cw1setup/Img/hard-btn-modified.png"));
		images.put("play-again-btn", GameUtil.getImage("/cw1setup/Img/play-again-btn (2)-modified.png"));
		images.put("main-menu-btn", GameUtil.getImage("/cw1setup/Img/main-menu-btn (2)-modified.png"));
		images.put("start-btn-hover", GameUtil.getImage("/cw1setup/Img/start-btn-hover.png"));
		images.put("highscore-btn-hover", GameUtil.getImage("/cw1setup/Img/highscore-btn-hover.png"));
		images.put("instructions-btn-hover", GameUtil.getImage("/cw1setup/Img/instructions-btn-hover.png"));
		images.put("easy-btn-hover", GameUtil.getImage("/cw1setup/Img/easy-btn-hover.png"));
		images.put("medium-btn-hover", GameUtil.getImage("/cw1setup/Img/medium-btn-hover.png"));
		images.put("hard-btn-hover", GameUtil.getImage("/cw1setup/Img/hard-btn-hover.png"));
		images.put("play-again-hover", GameUtil.getImage("/cw1setup/Img/play-again-hover.png"));
		images.put("main-menu-hover", GameUtil.getImage("/cw1setup/Img/main-menu-hover.png"));
	}

	// Call this method to initialize images
	static {
		initializeImages();
	}

}
