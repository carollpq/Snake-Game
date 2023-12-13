package example;

import javafx.scene.canvas.GraphicsContext;

public class Obstacle extends SnakeObject{

    public Obstacle() {

    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(foodImg, xPosition, yPosition);
    }
}
