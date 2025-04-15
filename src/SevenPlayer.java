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
     * Play a turn for this player.
     * @return The score granted for this turn.
     */
    @Override
    public int play() {
        int turnScore = 0;
        while (true) {
            int roll = die.roll();
            if(listener != null) listener.onRoll(this, roll);
            if (roll == 6) {
                return 0;

            }
            turnScore += roll;
            if(listener != null) listener.onRoll(this, roll);
            if (turnScore >= 7) {
                return turnScore;
            }
        }
    }
}