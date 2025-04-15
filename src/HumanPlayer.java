/**
 * Bulldog player which is controlled by a Human to determine continuing their turn.
 * @author DeepSeek
 * @version March 3rd 2025
 */
public class HumanPlayer extends Player {
    private volatile boolean decision;
    private volatile boolean decisionMade;

    /**
     * Create a HumanPlayer with given name.
     * @param name Given name to assign to player
     */
    public HumanPlayer(String name) {
        super(name);
    }

    /**
     * Play a turn for this player.
     * @return The score to be added to this players overall score
     */
    public int play() {
        System.out.println("Hello I am a human player");
        int turnScore = 0;
        decisionMade = false;

        while (true) {
            int roll = die.roll();
            listener.onRoll(this, roll);
            if (roll == 6) {
                return 0;
            }

            turnScore += roll;
            listener.onTurnDecision(this, turnScore);
            synchronized(this) {
                // Wait for user decision
                while (!decisionMade) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {
                    }
                }
            }

            if (!decision) {
                return turnScore;
            }
            decisionMade = false;
        }
    }

    /**
     * Decide if the HumanPlayer should roll again
     * @param decision Decision to set
     */
    public void makeDecision(boolean decision) {
        this.decision = decision;
        this.decisionMade = true;
    }
}