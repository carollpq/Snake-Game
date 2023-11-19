package example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.io.IOException

import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

/**
 * 
 * @Project Snakee
 * @Description Load the game and update it continuously
 * @Author Sigurður Sigurðardóttir
 * @version Not sure
 */ 


public class MyFrame extends Application implements EventHandler<ActionEvent>
{
	//private static final long serialVersionUID = -3149926831770554380L;
	private static final int STAGE_WIDTH = 870;
	private static final int STAGE_HEIGHT = 560;

	private MySnake mySnake;

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Snake Game");
		primaryStage.getIcons().add(new Image("/main/java/example/snake-logo.png"));

		Canvas canvas = new Canvas(STAGE_WIDTH, STAGE_HEIGHT);
		//Returns GraphicsContext -> draw shapes, text, and images onto Canvas
		GraphicsContext gc = canvas.getGraphicsContext2D();

		StackPane layout = new StackPane();
		layout.getChildren().add(canvas);

		Scene scene = new Scene(layout, STAGE_WIDTH, STAGE_HEIGHT);

		scene.setOnKeyPressed(e -> {
            mySnake.handleKeyPress(e);
        });

		mySnake = new MySnake(50, 50);
	}

	public void loadFrame()
	{
		/*
		 * Prevent the image from flashing.
		 */
		this.setDoubleBuffered(true);
		jFrame.add(this);
		jFrame.addKeyListener(this);

		jFrame.setLocationRelativeTo(null); //Positions the frame to the center of the screen
		jFrame.addWindowListener(new WindowAdapter()// loka
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				super.windowClosing(e);
				System.exit(0);
			}
		});
		jFrame.setVisible(true);

		new MyThread().start(); //Creates an instance of MyThread and calls the start() method
	}

	@Override
	public void handle(ActionEvent actionEvent) {

	}

	class MyThread extends Thread
	{
		@Override
		public void run()
		{
			super.run(); //Nothing is being overriden, it's just re-using the parent method so this line has no purpose basically??
			while (true) //Need to throw an exception
			{
				repaint(); // Constantly re-renders?? Or re-renders every second?
				try
				{
					sleep(30); //Makes this thread sleep indefinitely, busy-waiting
				} catch (Exception e)
				{
					e.printStackTrace(); //Should be replaced with more robust logging
				}
			}
		}
	}

//	@Override
//	public void keyTyped(KeyEvent e)
//	{
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void keyPressed(KeyEvent e)
//	{
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e)
//	{
//		// TODO Auto-generated method stub
//
//	}
}
