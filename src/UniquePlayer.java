/**
 * Bulldog player that quits rolling if their turn has doubled their points.
 * @author Ellis Fitzgerald & DeepSeek
 * @version March 3rd 2025
 */
public class UniquePlayer extends Player {

    /**
     * Create a UniquePlayer with a default name
     */
    public UniquePlayer() {
        this("Human");
    }

    /**
     * Create a UniquePlayer with a name
     * @param name name to give to UniquePlayer
     */
    public UniquePlayer(String name) {
        super(name);
    }

    /**
     * Play until score is doubled.
     */
    public int play() {
        int rollTotal = 0;
        do {
            int roll = die.roll();
            System.out.print("Player " + getName() + " has a current turn total of " + rollTotal + " and just rolled a " + roll);
            if(roll == 6) {
                System.out.println(" scoring 0 for their turn");
                return 0;
            }
            rollTotal += roll;
            System.out.println(" making their new round score " + rollTotal);
        }
        while(getScore() == 0 && rollTotal < 5 || rollTotal + getScore() <= getScore() * 2);
        System.out.println("Player " + getName() + " has decided to end their turn because they have doubled their score this turn.");
        return rollTotal;
    }

}
