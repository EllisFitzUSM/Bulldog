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
     * Play method which quits rolling as soon as 3 rolls have been made.
     * @return The score to be added to this players overall score
     */
    public int play() {
        int turnScore = 0;
        int rollsCount = 0;
        while (true) {
            int roll = die.roll();
            listener.onRoll(this, roll);
            rollsCount++;
            if (roll == 6) {
                return 0;
            }
            turnScore += roll;
            if (rollsCount == 3) {
                return turnScore;
            }
        }
    }
}
