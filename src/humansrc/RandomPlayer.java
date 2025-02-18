package humansrc;

/**
 * Author: Ellis Fitzgerald
 * Date: Januray 28th 2025
 * COS 420 Assignment 1
 * humansrc.RandomPlayer, a player that quits if their coinflip does not go their way.
 */
public class RandomPlayer extends Player {

    /**
     * Create a humansrc.RandomPlayer with a default name
     */
    public RandomPlayer() {
        this("Human");
    }

    /**
     * Create a humansrc.RandomPlayer
     * @param name Name given to humansrc.RandomPlayer
     */
    public RandomPlayer(String name) {
        super(name);
    }

    /**
     * Play until a coinflip decides not to play.
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
        while(Math.random() < 0.5 && rollTotal + getScore() < Prog1.SCORE_MAX);
        System.out.println("humansrc.Player " + getName() + " has decided to end their turn by flipping a coin.");
        return rollTotal;
    }
    
}
