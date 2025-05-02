/**
 * Bulldog player that quits rolling if their turn has doubled their points.
 * @author Ellis Fitzgerald & DeepSeek
 * @version March 3rd 2025
 */
public class UniquePlayer extends Player {

    /**
     * Create a UniquePlayer with a name
     * @param name name to give to UniquePlayer
     */
    public UniquePlayer(String name) {
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
        return getScore() == 0 && turnScore < 5 || turnScore <= getScore();
    }

}
