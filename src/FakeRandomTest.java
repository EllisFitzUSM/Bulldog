import static org.junit.Assert.assertEquals;
import java.util.Scanner;

import org.junit.Test;

/**
 * Testing class for SevenPlayer and FakeRandomDice
 * @author Ellis Fitzgerald
 * @version 0.7
 */
public class FakeRandomTest {

    /**
     * Tests the maximum number of rolls for a scoring turn.
     */
    @Test
    public void maximumRoll() {
        Scanner scanner = new Scanner("1 1 1 1 1 1 1");
        DiceSuper die = new FakeRandomDice(6, scanner);
        Player sevenPlayer = new SevenPlayer("Dummy", die);
        assertEquals(7, sevenPlayer.play());
    }

    /**
     * Verifying that the turn properly ends with a 6 roll instead of securing a >= 7 score.
     */
    @Test
    public void conditionOrder1() {
        Scanner scanner = new Scanner("1 6");
        DiceSuper die = new FakeRandomDice(6, scanner);
        Player sevenPlayer = new SevenPlayer("Dummy", die);
        assertEquals(0, sevenPlayer.play());
    }

    /**
     * Verifying the turn properly ends with a 6 roll even if there is more rolls that follow granting a >= 7 score.
     */
    @Test
    public void conditionOrder2() {
        Scanner scanner = new Scanner("6 1 1 1");
        DiceSuper die = new FakeRandomDice(6, scanner);
        Player sevenPlayer = new SevenPlayer("Dummy", die);
        assertEquals(0, sevenPlayer.play());
    }

    /**
     * Verifying the turn properly ends with a 6 roll.
     */
    @Test
    public void earlyExit() {
        Scanner scanner = new Scanner("6");
        DiceSuper die = new FakeRandomDice(6, scanner);
        Player sevenPlayer = new SevenPlayer("Dummy", die);
        assertEquals(0, sevenPlayer.play());
    }

    /**
     * Verifies the maximum score a SevenPlayer can achieve in a single turn.
     */
    @Test
    public void maximumScore() {
        Scanner scanner = new Scanner("1 5 5 4 3 2 1");
        DiceSuper die = new FakeRandomDice(6, scanner);
        Player sevenPlayer = new SevenPlayer("Dummy", die);
        assertEquals(11, sevenPlayer.play());
    }

    /**
     * Verifies the score after multiple turns.
     */
    @Test
    public void multipleTurnsExit() {
        Scanner scanner = new Scanner("1 2 3 4 5 6 7 8 9");
        DiceSuper die = new FakeRandomDice(6, scanner);
        Player sevenPlayer = new SevenPlayer("Dummy", die);
        assertEquals(10, sevenPlayer.play());
        assertEquals(0, sevenPlayer.play());
    }
}
