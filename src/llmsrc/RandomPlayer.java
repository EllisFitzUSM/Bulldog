package llmsrc;

public class RandomPlayer extends Player {
    public RandomPlayer(String name) {
        super(name);
    }

    public int play() {
        int turnScore = 0;
        while (true) {
            int roll = rollDie();
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