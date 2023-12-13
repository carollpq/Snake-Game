package example.Controller;

import java.io.IOException;

public class MediumFrameController extends MyFrameController {
    //Change the speed of snake
    //Change the amount of time that the bonusObj stays
    @Override
    public void initialization() throws IOException, InterruptedException {
        super.initialization();
        setSnakeSpeed(8);
        setOnScreenTime(2000);
    }
}
