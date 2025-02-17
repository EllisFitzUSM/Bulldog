/**
 * Author: Ellis Fitzgerald
 * Date: Januray 28th 2025
 * COS 420 Assignment 1
 * FifteenPlayer, a player that quits if the # score they make in a round is 15 or more. 
 */
public class FifteenPlayer extends Player {

    public FifteenPlayer() {
        this("Human");
    }

    public FifteenPlayer(String name) {
        super(name);
    }

    public int play() {
        int rollTotal = 0;
        do {
            int roll = (int) (Math.random()*6 + 1);
            System.out.print("Player " + getName() + " has a current turn total of " + rollTotal + " and just rolled a " + roll);
            if(roll == 6) {
                System.out.println(" scoring 0 for their turn");
                return 0;
            }
            rollTotal += roll;
            System.out.println(" making their new round score " + rollTotal);
        }
        while(rollTotal < 15 && getScore() + rollTotal < Prog1.SCORE_MAX);
        System.out.println("Player " + getName() + " has decided to end their turn because their turn total has reached over 15.");
        return rollTotal;
    }
    
}
