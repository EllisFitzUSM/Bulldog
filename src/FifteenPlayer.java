/**
 * Bulldog player which when they score fifteen they end their turn.
 * @author DeepSeek
 * @version March 3rd 2025
 */
public class FifteenPlayer extends Player {

    /**
     * Create a Fifteen player with the given name.
     * @param name Name to give to player
     * @param die Dice to use
     */
    public FifteenPlayer(String name, DiceSuper die) {
        super(name, die);
    }

    /**
     * Decides if the Player needs to continue their turn
     * @param gameStatus Status of the Bulldog Game encapsulated in an object
     * @param turnScore Current turn score
     * @param rollsCount Number of rolls
     * @return boolean
     */
    public boolean continueTurn(GameStatus gameStatus, int turnScore, int rollsCount) {
        return turnScore < 15;
    }
}