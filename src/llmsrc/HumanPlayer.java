package llmsrc;

import java.util.Scanner;

public class HumanPlayer extends Player {
    private static Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String name) {
        super(name);
    }

    public int play() {
        int turnScore = 0;
        while (true) {
            int roll = (int) (Math.random() * 6 + 1);
            System.out.print("   Rolled " + roll);
            if (roll == 6) {
                System.out.println(" Scored 0 for the turn.");
                return 0;
            }
            turnScore += roll;
            System.out.println(" Current turn score: " + turnScore);
            System.out.print("   Continue? (Y/N): ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("N")) {
                System.out.println("   Player chose to stop. Turn score: " + turnScore);
                return turnScore;
            }
        }
    }
}