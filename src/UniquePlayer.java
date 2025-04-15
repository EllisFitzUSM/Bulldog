/**
 * Bulldog player that quits rolling if their turn has doubled their points.
 * @author Ellis Fitzgerald & DeepSeek
 * @version March 3rd 2025
 */
public class UniquePlayer extends Player {

    /**
     * Create a UniquePlayer with a name
     * @param name name to give to UniquePlayer
     * @param die Dice to use
     */
    public UniquePlayer(String name, DiceSuper die) {
        super(name, die);
    }

    /**
     * Play until score is doubled.
     */
    public int play() {
        int rollTotal = 0;
        do {
            int roll = die.roll();
            listener.onRoll(this, roll);
            if(roll == 6) {
                return 0;
            }
            rollTotal += roll;
//            listener.onMessage("Current score total: " + rollTotal);
        }
        while(getScore() == 0 && rollTotal < 5 || rollTotal + getScore() <= getScore() * 2);
        return rollTotal;
    }

}
