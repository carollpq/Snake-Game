package com.SnakeGame.Model.Utilities;

import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for managing and providing access to images used in the Snake Game.
 *
 * <p>This class initializes and provides a mapping of image names to corresponding Image objects.
 * It includes images for the snake, food objects, and various buttons used in the game.</p>
 *
 * <p>To use the images, call the static method {@code initializeImages()} or access the images
 * directly from the {@code images} map. The images are loaded and stored as Image objects for
 * efficient use within the application.</p>
 *
 * <p><strong>Note:</strong> Make sure to call {@code initializeImages()} before using any images.</p>
 * <p>For this project, a single instance was created in the StartFrameMain class</p>
 *
 * @author Carolina Lee-modified
 *
 */

public class ImageUtil
{
	public static Map<String, Image> images = new HashMap<>();

	// Initialize the images in a separate method
	public static void initializeImages() {
		// Snake
		images.put("snake-head-right", GameUtil.getImage("/Img/snake-head-right.png"));
		images.put("snake-body", GameUtil.getImage("/Img/snake-body.png"));
		images.put("snake-head-up", GameUtil.rotateImage(GameUtil.getImage("/Img/snake-head-right.png"), -90));
		images.put("snake-head-down", GameUtil.rotateImage(GameUtil.getImage("/Img/snake-head-right.png"), 90));
		images.put("snake-head-left", GameUtil.rotateImage(GameUtil.getImage("/Img/snake-head-right.png"), -180));
		// Food objects
		images.put("0", GameUtil.getImage("/Img/food-kiwi.png"));
		images.put("1", GameUtil.getImage("/Img/food-lemon.png"));
		images.put("2", GameUtil.getImage("/Img/food-litchi.png"));
		images.put("3", GameUtil.getImage("/Img/food-mango.png"));
		images.put("4", GameUtil.getImage("/Img/food-apple.png"));
		images.put("5", GameUtil.getImage("/Img/food-banana.png"));
		images.put("6", GameUtil.getImage("/Img/food-blueberry.png"));
		images.put("7", GameUtil.getImage("/Img/food-cherry.png"));
		images.put("8", GameUtil.getImage("/Img/food-durian.png"));
		images.put("9", GameUtil.getImage("/Img/food-grape.png"));
		images.put("10", GameUtil.getImage("/Img/food-grapefruit.png"));
		images.put("11", GameUtil.getImage("/Img/food-peach.png"));
		images.put("12", GameUtil.getImage("/Img/food-pear.png"));
		images.put("13", GameUtil.getImage("/Img/food-orange.png"));
		images.put("14", GameUtil.getImage("/Img/food-pineapple.png"));
		images.put("15", GameUtil.getImage("/Img/food-strawberry.png"));
		images.put("16", GameUtil.getImage("/Img/food-watermelon.png"));
		images.put("17", GameUtil.getImage("/Img/topaz.png"));
		images.put("18", GameUtil.getImage("/Img/diamond.png"));
		images.put("19", GameUtil.getImage("/Img/garnet.png"));
		images.put("20", GameUtil.getImage("/Img/sapphire.png"));
		//Button images
		images.put("resume-btn", GameUtil.getImage("/Img/resume-btn.png"));
		images.put("pause-btn", GameUtil.getImage("/Img/pause-btn-modified.png"));
		images.put("start-btn", GameUtil.getImage("/Img/start-btn-modified.png"));
		images.put("highscore-btn", GameUtil.getImage("/Img/high-score-modified.png"));
		images.put("instructions-btn", GameUtil.getImage("/Img/instructions-btn.png"));
		images.put("easy-btn", GameUtil.getImage("/Img/easy-btn-modified.png"));
		images.put("medium-btn", GameUtil.getImage("/Img/medium-btn-modified.png"));
		images.put("hard-btn", GameUtil.getImage("/Img/hard-btn-modified.png"));
		images.put("play-again-btn", GameUtil.getImage("/Img/play-again-btn (2)-modified.png"));
		images.put("main-menu-btn", GameUtil.getImage("/Img/main-menu-btn (2)-modified.png"));
		images.put("start-btn-hover", GameUtil.getImage("/Img/start-btn-hover.png"));
		images.put("highscore-btn-hover", GameUtil.getImage("/Img/highscore-btn-hover.png"));
		images.put("instructions-btn-hover", GameUtil.getImage("/Img/instructions-btn-hover.png"));
		images.put("easy-btn-hover", GameUtil.getImage("/Img/easy-btn-hover.png"));
		images.put("medium-btn-hover", GameUtil.getImage("/Img/medium-btn-hover.png"));
		images.put("hard-btn-hover", GameUtil.getImage("/Img/hard-btn-hover.png"));
		images.put("play-again-hover", GameUtil.getImage("/Img/play-again-hover.png"));
		images.put("main-menu-hover", GameUtil.getImage("/Img/main-menu-hover.png"));
	}

	// Call this method to initialize images
	static {
		initializeImages();
	}

}
