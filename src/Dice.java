import java.util.Random;

/**
 * A class representing a multi-sided die for generating random numbers.
 * Uses a single static Random instance for all dice rolls.
 * @author DeepSeek
 * @version March 3rd 2025
 */
public class Dice {
    private static final Random random = new Random();
    private final int sides;

    /**
     * Creates a new Dice object with the specified number of sides.
     * @param sides Number of sides on the die (must be >= 1)
     */
    public Dice(int sides) {
        if(sides < 1) {
            throw new IllegalArgumentException("Dice must have at least one side");
        }
        this.sides = sides;
    }

    /**
     * Rolls the die and returns a random number between 1 and the number of sides.
     * @return Random number between 1 and sides (inclusive)
     */
    public int roll() {
        return random.nextInt(sides) + 1;
    }

}
