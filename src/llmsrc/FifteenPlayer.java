package llmsrc;

public class FifteenPlayer extends Player {
    public FifteenPlayer(String name) {
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
            if (turnScore >= 15) {
                System.out.println("   Reached 15, stopping. Turn score: " + turnScore);
                return turnScore;
            }
        }
    }
}