/**
 * Bulldog player which when they score seven they end their turn.
 * @author Ellis Fitzgerald
 * @version 0.7
 */
public class SevenPlayer extends Player {

    /**
     * Create a Seven player with the given name.
     * @param name Name to give to player
     * @param die Dice to use
     */
    public SevenPlayer(String name, DiceSuper die) {
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
        return turnScore < 7;
    }
}