package example.Model;

import java.util.Random;

public class FactoryPowerUp {
    public static PowerUp makePowerUp(){
        int r = new Random().nextInt(4) + 17;
        if (r == 17) return new TopazPowerUp(r);
        else if (r == 18) {
            return new DiamondPowerUp(r);
        } else if (r == 19) {
            return new GarnetPowerUp(r);
        } else {
            return new SapphirePowerUp(r);
        }
    }
}