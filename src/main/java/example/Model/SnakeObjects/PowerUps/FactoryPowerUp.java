package example.Model.SnakeObjects.PowerUps;

import java.util.Random;

/**
 * Factory class for creating different types of PowerUp objects.
 */
public class FactoryPowerUp {
    /**
     * Creates a PowerUp object based on a random selection.
     *
     * @return A PowerUp object with a specific type (Topaz, Diamond, Garnet, or Sapphire).
     */
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