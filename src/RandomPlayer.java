/**
 * Bulldog player which has a "random" decision towards rolling or not rolling.
 * @author DeepSeek
 * @version March 3rd 2025
 */
public class RandomPlayer extends Player {

    /**
     * Creates a random player with the given name.
     * @param name Name to assign to player.
     * @param die Dice to use
     */
    public RandomPlayer(String name, DiceSuper die) {
        super(name, die);
    }

    /**
     * Plays the players turn.
     * @return The score earned this turn.
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
            if (RandomSingleton.getInstance().nextFloat() < 0.5) {
                return turnScore;
            }
        }
    }
}