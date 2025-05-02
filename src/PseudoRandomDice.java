import java.util.Random;

/**
 * PsuedoRandomDice that uses Java Random
 * @author Ellis Fitzgerald
 * @version 0.7
 */
public class PseudoRandomDice extends DiceSuper {
    private final Random random;

    public PseudoRandomDice(int side) {
        super(side);
        this.random = RandomSingleton.getInstance();
    }

    /**
     * Roll the dice
     * @return Rolled amount
     */
    @Override
    int roll() {
        return random.nextInt(sides) + 1;
    }
}
