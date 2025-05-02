/**
 * Bulldog player with DeepSeek's "Unique" strategy.
 * @author DeepSeek
 * @version March 3rd 2025
 */
public class AIPlayer extends Player {

    /**
     * Creates an AIPlayer with the given name
     * @param name Name to give to associate with the player
     */
    public AIPlayer(String name) {
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
        return rollsCount < 3;
    }
}
