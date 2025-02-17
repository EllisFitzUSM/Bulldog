package llmsrc;

public class RandomPlayer extends Player {
    public RandomPlayer(String name) {
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
            boolean continueRoll = Math.random() < 0.5;
            if (continueRoll) {
                System.out.println("   Random choice: continue rolling.");
            } else {
                System.out.println("   Random choice: stop. Turn score: " + turnScore);
                return turnScore;
            }
        }
    }
}