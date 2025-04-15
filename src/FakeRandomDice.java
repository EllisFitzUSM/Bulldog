import java.util.Scanner;
/**
 * Uses a Scanner to iterate between a predetermined set of integers
 * @author Ellis Fitzgerald
 * @version 0.7
 */
public class FakeRandomDice extends DiceSuper {
    Scanner scanner;

    /**
     * Sides only parameter constructor
     * @param sides Number of sides the dice has
     * @throws Exception Exception thrown if no Scanner input provided
     */
    public FakeRandomDice(int sides) throws Exception {
        super(sides);
        throw new Exception("No scanner input provided");
    }

    /**
     * Creates a Fake Random Dice
     * @param sides The number of sides
     * @param scanner with predetermined set input
     */
    public FakeRandomDice(int sides, Scanner scanner) {
        super(sides);
        this.scanner = scanner;
    }

    /**
     *
     * @return Rolled amount
     */
    @Override
    int roll() {
        int nextInt = scanner.nextInt();
        System.out.println(nextInt);
        return nextInt;
    }
}
