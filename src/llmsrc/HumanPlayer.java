package llmsrc;

import java.util.Scanner;

public class HumanPlayer extends Player {
    private volatile boolean decision;
    private volatile boolean decisionMade;

    public HumanPlayer(String name) {
        super(name);
    }

    public int play() {
        int turnScore = 0;
        decisionMade = false;

        while (true) {
            int roll = rollDie();
            listener.onRoll(this, roll);

            if (roll == 6) {
                listener.onMessage("Rolled a 6! Turn over.");
                return 0;
            }

            turnScore += roll;
            listener.onTurnDecision(this, turnScore);

            // Wait for user decision
            while (!decisionMade) {
                try { Thread.sleep(100); }
                catch (InterruptedException ignored) {}
            }

            if (!decision) {
                return turnScore;
            }
            decisionMade = false;
        }
    }

    public void makeDecision(boolean decision) {
        this.decision = decision;
        this.decisionMade = true;
    }
}