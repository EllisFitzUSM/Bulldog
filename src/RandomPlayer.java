/**
 * Bulldog player which has a "random" decision towards rolling or not rolling.
 * @author DeepSeek
 * @version March 3rd 2025
 */
public class RandomPlayer extends Player {

    /**
     *
     * @param name
     */
    public RandomPlayer(String name) {
        super(name);
    }

    /**
     *
     * @return
     */
    public int play() {
        int turnScore = 0;
        while (true) {
            int roll = die.roll();
            listener.onRoll(this, roll);
            if (roll == 6) {
                listener.onMessage("Rolled a 6! Turn over.");
                return 0;
            }
            turnScore += roll;
            if (Math.random() < 0.5) {
                listener.onMessage("Choosing to stop rolling");
                return turnScore;
            }
        }
    }
}