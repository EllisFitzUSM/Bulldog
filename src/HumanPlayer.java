import java.util.Scanner;

/**
 * Author: Ellis Fitzgerald
 * Date: Januray 28th 2025
 * COS 420 Assignment 1
 * HumanPlayer, a player that gets to decide whether they can quit or not each roll via console input.
 */
public class HumanPlayer extends Player {

    Scanner sc;

    /**
     * Create a new HumanPlayer with a default name
     * @param sc Scanner to take human input
     */
    public HumanPlayer(Scanner sc) {
        this("Human", sc);
    }

    /**
     * Create a new HumanPlayer
     * @param name Name to give to HumanPlayer
     * @param sc Scanner to take human input
     */
    public HumanPlayer(String name, Scanner sc) {
        super(name);
        this.sc = sc;
    }

    /**
     * Play turns.
     * After initial roll, if it is not 6, Human will be prompted to decide to roll again.
     */
    public int play() {
        int rollTotal = 0;
        do {
            int roll = (int) (Math.random()*6 + 1);
            System.out.print("Player " + getName() + " just rolled a " + roll);
            if(roll == 6) {
                System.out.println(" scoring 0 for this round and ending their turn."); // This should be in all files
                return 0;
            }
            rollTotal += roll;
            System.out.println(" making their new round score " + rollTotal + ".");
            System.out.print("Press any character key to keep rolling!\nOr press N/n (for No):");
            try {
                String nextLine = sc.nextLine();
                if(nextLine.contains("n") || nextLine.contains("N")) {
                    break;
                }
            } catch(Exception e) {

            }
        }
    while(true);
        System.out.println("Player " + getName() + " has decided to end their turn by pressing 0.");
        return rollTotal;
    }
    
}
