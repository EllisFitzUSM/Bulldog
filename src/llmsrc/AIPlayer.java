package llmsrc;

public class AIPlayer extends Player {
    public AIPlayer(String name) {
        super(name);
    }

    public int play() {
        int turnScore = 0;
        int rollsCount = 0;
        while (true) {
            int roll = (int) (Math.random() * 6 + 1);
            rollsCount++;
            System.out.print("   Rolled " + roll);
            if (roll == 6) {
                System.out.println(" Scored 0 for the turn.");
                return 0;
            }
            turnScore += roll;
            System.out.println(" Current turn score: " + turnScore);
            if (rollsCount == 3) {
                System.out.println("   Rolled 3 times, stopping. Turn score: " + turnScore);
                return turnScore;
            }
        }
    }
}
