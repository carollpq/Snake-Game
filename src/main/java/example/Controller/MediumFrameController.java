package example.Controller;

import java.io.IOException;

public class MediumFrameController extends MyFrameController {
    @Override
    public void initialization() throws IOException, InterruptedException {
        super.initialization();
        //Increase the speed of snake
        setSnakeSpeed(8);
        //Decrease the amount of time that the bonusObj stays
        setOnScreenTime(2000);
    }
}
