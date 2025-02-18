package humansrc;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Author: Ellis Fitzgerald
 * Date: Januray 28th 2025
 * COS 420 Assignment 1
 * Main Program File
 */

public class Prog1 {

    public static int SCORE_MAX = 104;

    /**
     * Main Function (Entry Point)
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        List<Player> players = getPlayers(sc);
        ListIterator<Player> playerIterator = players.listIterator();

        while(true) {
            Player currentPlayer;
            if(playerIterator.hasNext()) {
                currentPlayer = playerIterator.next();
            }
            else {
                playerIterator = players.listIterator();
                currentPlayer = playerIterator.next();
            }
            currentPlayer.setScore(currentPlayer.getScore() + currentPlayer.play());
            if(currentPlayer.getScore() >= SCORE_MAX) {
                System.out.println("humansrc.Player " + currentPlayer.getName() + " has won the game with " + currentPlayer.getScore());
                break;
            }
            System.out.println("humansrc.Player " + currentPlayer.getName() + "'s Cumulative Score: " + currentPlayer.getScore());
        }
        sc.close();

    }

    /**
     * Retrieves players of varying types for the game to be played with.
     * @param sc Scanner to get input for things like names and types.
     * @return ArrayList of Players of varying type to be cycled through turns.
     */
    public static ArrayList<Player> getPlayers(Scanner sc) {
        System.out.print("How many players are playing?: ");

        int playerCount;
        while(true) {
            try{
                playerCount = Integer.parseInt(sc.nextLine());
                break;
            }
            catch(Exception e) {
                System.out.println("Please enter a valid number with no alphabet or special characters!");
            }
        }

        ArrayList<Player> players = new ArrayList<Player>(playerCount);
        for(int i = 0; i < playerCount; i++) {
            while(true) {
                printPlayerChoices();
                System.out.print("\nWhat type (number) of player is humansrc.Player " + (i + 1) + "?:");

                try {
                    int playerChoice = Integer.parseInt(sc.nextLine());
                    if(playerChoice <= 5 && playerChoice >= 1) {

                        System.out.print("What name would you like to give this player?: ");
                        String playerName = sc.next();
                        sc.nextLine();

                        players.add(getPlayer(playerChoice, sc, playerName));
                        break;
                    }
                    else {
                        System.out.println("Please enter a number within 1-5 range!");
                    }
                }
                catch(Exception e) {
                    System.out.println("Please enter a valid number with no alphabet or special characters!");
                }

            }
        }
        return players;
    }

    /**
     * Basic printing function.
     */
    public static void printPlayerChoices() {
        System.out.println("Enumerated list of different types of players:");
        System.out.println("1. Human");
        System.out.println("2. Random");
        System.out.println("3. Fifteen");
        System.out.println("4. Wimp");
        System.out.print("5. Unique");
    }

    /**
     * Retrieves player based on the type provided via an index.
     * @param i Selected type
     * @param sc Scanner to get input (necessary for human player)
     * @param name Name of said player
     * @return Initialized player
     */
    public static Player getPlayer(int i, Scanner sc, String name) {
        switch(i) {
            case 1:
            return new HumanPlayer(name, sc);
            case 2:
            return new RandomPlayer(name);
            case 3:
            return new FifteenPlayer(name);
            case 4:
            return new WimpPlayer(name);
            case 5:
            return new UniquePlayer(name);
            default:
            return null;
        }
    }

}
