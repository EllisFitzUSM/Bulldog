/**
 * Bulldog player which when they score fifteen they end their turn.
 * @author DeepSeek
 * @version March 3rd 2025
 */
public class FifteenPlayer extends Player {

    /**
     * Create a Fifteen player with the given name.
     * @param name Name to give to player
     */
    public FifteenPlayer(String name) {
        super(name);
    }

    /**
     * Play a turn for this player.
     * @return The score granted for this turn.
     */
    public int play() {
        int turnScore = 0;
        while (true) {
            int roll = die.roll();
            listener.onRoll(this, roll);
            if (roll == 6) {
                return 0;

            }
            turnScore += roll;
            listener.onRoll(this, roll);
            if (turnScore >= 15) {
                return turnScore;
            }
        }
    }
}