package example;

import javafx.scene.canvas.GraphicsContext;

public interface Frame {
    int STAGE_WIDTH = 870;
    int STAGE_HEIGHT = 560;

    void drawBgImg(GraphicsContext gc);
}