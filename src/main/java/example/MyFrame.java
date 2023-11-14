package example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @Project Snakee
 * @Description Load the game and update it continuously
 * @Author Sigurður Sigurðardóttir
 * @version Not sure
 */ 


public class MyFrame extends JPanel implements KeyListener
{
	private static final long serialVersionUID = -3149926831770554380L;

	public JFrame jFrame = new JFrame();

	public MyFrame() //Constructor
	{
		jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(MyFrame.class.getResource("snake-logo.png")));
	}

	public void loadFrame()
	{
		/*
		 * Prevent the image from flashing.
		 */
		this.setDoubleBuffered(true);
		jFrame.add(this);
		jFrame.addKeyListener(this);

		jFrame.setTitle("Snakee Yipee");
		jFrame.setSize(870, 560);
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

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	public static class MySnake extends SnakeObject implements movable
	{
		// The game changer.
		private int speed_XY;
		private int length;
		private int num; // ?
		public int score = 0; //K

		private static final BufferedImage IMG_SNAKE_HEAD = (BufferedImage) ImageUtil.images.get("snake-head-right");

		public static List<Point> bodyPoints = new LinkedList<>();

		private static BufferedImage newImgSnakeHead;
		boolean up, down, left, right = true;

		public MySnake(int x, int y)
		{
			this.live_of_object = true;
			this.x_position = x;
			this.y_position = y;
			this.i = ImageUtil.images.get("snake-body");
			this.width_of_snake = i.getWidth(null);
			this.height_of_snake = i.getHeight(null);

			this.speed_XY = 5; //Starting speed is 5
			this.length = 1; //Starting length is 1

			/*
			 * Attention : ?
			 */
			this.num = width_of_snake / speed_XY;
			newImgSnakeHead = IMG_SNAKE_HEAD;

		}

		public int getLength()
		{
			return length;
		}

		public void changeLength(int length)
		{
			this.length = length;
		}

		public void keyPressed(KeyEvent e)
		{
			// athugaðu lykilinn
			switch (e.getKeyCode())
			{
			case KeyEvent.VK_UP:
				if (!down)
				{
					up = true;
					down = false;
					left = false;
					right = false;

					newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -90);
				}
				break;

			case KeyEvent.VK_DOWN:
				if (!up)
				{
					up = false;
					down = true;
					left = false;
					right = false;

					newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, 90);
				}
				break;

			case KeyEvent.VK_LEFT:
				if (!right)
				{
					up = false;
					down = false;
					left = true;
					right = false;

					newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -180);

				}
				break;

			case KeyEvent.VK_RIGHT:
				if (!left)
				{
					up = false;
					down = false;
					left = false;
					right = true;

					newImgSnakeHead = IMG_SNAKE_HEAD;
				}

			default:
				break;
			}
		}


		public void move()
		{
			// Let the snake move
			if (up)
			{
				y_position -= speed_XY;
			} else if (down)
			{
				y_position += speed_XY;
			} else if (left)
			{
				x_position -= speed_XY;
			} else if (right)
			{
				x_position += speed_XY;
			}

		}

		@Override
		public void draw(Graphics g)
		{
			outofBounds();
			eatBody();

			bodyPoints.add(new Point(x_position, y_position));

			if (bodyPoints.size() == (this.length + 1) * num)
			{
				bodyPoints.remove(0);
			}
			g.drawImage(newImgSnakeHead, x_position, y_position, null);
			drawBody(g);

			move();
		}

		public void eatBody()
		{
			for (Point point : bodyPoints)
			{
				for (Point point2 : bodyPoints)
				{
					if (point.equals(point2) && point != point2)
					{
						this.live_of_object = false;
					}
				}
			}
		}

		public void drawBody(Graphics g)
		{
			int length = bodyPoints.size() - 1 - num;

			for (int i = length; i >= num; i -= num)
			{
				Point point = bodyPoints.get(i);
				g.drawImage(this.i, point.x, point.y, null);
			}
		}

		private void outofBounds()
		{
			boolean xOut = (x_position <= 0 || x_position >= (870 - width_of_snake));
			boolean yOut = (y_position <= 40 || y_position >= (560 - height_of_snake));
			if (xOut || yOut)
			{
				live_of_object = false;
			}
		}
	}

	public abstract static class SnakeObject
	{
		//Attributes to initialize the Rectangle object
		int x_position;
		int y_position;
		Image i;
		int width_of_snake;
		int height_of_snake;

		public boolean live_of_object;


		public abstract void draw(Graphics g);

		public Rectangle getRectangle()
		{
			return new Rectangle(x_position, y_position, width_of_snake, height_of_snake);
		}
	}
}
