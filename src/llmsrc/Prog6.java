package llmsrc;

import java.util.ArrayList;
import java.util.Scanner;

public class Prog6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();

        System.out.print("Enter number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Player " + (i + 1) + " setup:");
            System.out.println("Choose type: 1. Human, 2. Random, 3. Fifteen, 4. Wimp, 5. Unique, 6. AI");
            int type = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter player's name: ");
            String name = scanner.nextLine();

            switch (type) {
                case 1:
                    players.add(new HumanPlayer(name));
                    break;
                case 2:
                    players.add(new RandomPlayer(name));
                    break;
                case 3:
                    players.add(new FifteenPlayer(name));
                    break;
                case 4:
                    players.add(new WimpPlayer(name));
                    break;
                case 5:
                    players.add(new UniquePlayer(name));
                    break;
                case 6:
                    players.add(new AIPlayer(name));
                    break;
                default:
                    System.out.println("Invalid type, defaulting to Wimp.");
                    players.add(new WimpPlayer(name));
            }
        }

        boolean gameOver = false;
        while (!gameOver) {
            for (Player p : players) {
                System.out.println("\n--- " + p.getName() + "'s turn ---");
                int turnScore = p.play();
                int newScore = p.getScore() + turnScore;
                p.setScore(newScore);

                System.out.println("   " + p.getName() + " scores " + turnScore + " this turn. Total: " + newScore);

                if (newScore >= 104) {
                    System.out.println("\n!!! " + p.getName() + " wins with a score of " + newScore + " !!!");
                    gameOver = true;
                    break;
                }

                System.out.println("\nCurrent Scores:");
                for (Player pl : players) {
                    System.out.println("   " + pl.getName() + ": " + pl.getScore());
                }
            }
            if (gameOver) break;
        }
        scanner.close();
    }
}