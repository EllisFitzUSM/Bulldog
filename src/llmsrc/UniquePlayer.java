package llmsrc;

/**
 * Author: Ellis Fitzgerald
 * Date: Januray 28th 2025
 * COS 420 Assignment 1
 * humansrc.UniquePlayer, a player that quits rolling if their turn has doubled their points.
 */
public class UniquePlayer extends Player {

    /**
     * Create a humansrc.UniquePlayer with a default name
     */
    public UniquePlayer() {
        this("Human");
    }

    /**
     * Create a humansrc.UniquePlayer with a name
     * @param name name to give to humansrc.UniquePlayer
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
            int roll = (int) (Math.random()*6 + 1);
            System.out.print("humansrc.Player " + getName() + " has a current turn total of " + rollTotal + " and just rolled a " + roll);
            if(roll == 6) {
                System.out.println(" scoring 0 for their turn");
                return 0;
            }
            rollTotal += roll;
            System.out.println(" making their new round score " + rollTotal);
        }
        while(getScore() == 0 && rollTotal < 5 || rollTotal + getScore() <= getScore() * 2);
        System.out.println("humansrc.Player " + getName() + " has decided to end their turn because they have doubled their score this turn.");
        return rollTotal;
    }

}
