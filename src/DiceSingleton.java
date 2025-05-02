import java.util.Scanner;

/**
 * DiceSingleton.java
 * Manages a Dice singleton
 *
 * @author Ellis Fitzgerald
 * @version May 2nd, 2025
 */
public class DiceSingleton {

    private static DiceSuper instance;
    private static final int SIDES = 6;

    /**
     * Retrieves the global instance of the dice
     *
     * @return Dice to be used by the players
     */
    public static DiceSuper getInstance() {
        return getInstance(DiceType.PSEUDO_RANDOM);
    }

    /**
     * Retrieves the global instance of the dice, if uninitialized it promptly creates an object of specified type
     *
     * @return Dice to be used by the players
     */
    public static DiceSuper getInstance(DiceType type) {
        if(instance == null) {
            switch(type) {
                case PSEUDO_RANDOM:
                    instance = new PseudoRandomDice(SIDES);
                    break;
                case FAKE_RANDOM:
                    instance = new FakeRandomDice(SIDES, new Scanner(System.in));
                    break;
            }
        }
        return instance;
    }
}

enum DiceType {
    PSEUDO_RANDOM,
    FAKE_RANDOM
}
