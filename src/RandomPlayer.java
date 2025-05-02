/**
 * Bulldog player which has a "random" decision towards rolling or not rolling.
 * @author DeepSeek
 * @version March 3rd 2025
 */
public class RandomPlayer extends Player {

    /**
     * Creates a random player with the given name.
     * @param name Name to assign to player.
     */
    public RandomPlayer(String name) {
        super(name);
    }

    /**
     * Decides if the Player needs to continue their turn
     * @param gameStatus Status of the Bulldog Game encapsulated in an object
     * @param turnScore Current turn score
     * @param rollsCount Number of rolls
     * @return boolean
     */
    public boolean continueTurn(GameStatus gameStatus, int turnScore, int rollsCount) {
        return RandomSingleton.getInstance().nextFloat() >= 0.5;
    }
}