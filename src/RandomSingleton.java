import java.util.Random;

/**
 * Lazily initializes and returns a Java Random object
 * @author Ellis Fitzgerald
 * @version 0.7
 */
public class RandomSingleton {
    static Random random = null;

    /**
     * Returns the global Random object
     * @return Java Random object
     */
    public static Random getInstance() {
        if (random == null) {
            random = new Random();
        }
        return random;
    }
}