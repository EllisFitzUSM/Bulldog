import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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
        Player sevenPlayer = new SevenPlayer("Dummy");
        GameStatus gameStatus = new GameStatus(new ArrayList<Player>(), 0, 104);
        assertEquals(7, sevenPlayer.play(gameStatus));
    }

    /**
     * Verifying that the turn properly ends with a 6 roll instead of securing a >= 7 score.
     */
    @Test
    public void conditionOrder1() {
        Scanner scanner = new Scanner("1 6");
        DiceSingleton.getInstance(DiceType.FAKE_RANDOM);
        Player sevenPlayer = new SevenPlayer("Dummy");
        GameStatus gameStatus = new GameStatus(new ArrayList<Player>(), 0, 104);
        assertEquals(0, sevenPlayer.play(gameStatus));
    }

    /**
     * Verifying the turn properly ends with a 6 roll even if there is more rolls that follow granting a >= 7 score.
     */
    @Test
    public void conditionOrder2() {
        Scanner scanner = new Scanner("6 1 1 1");
        DiceSingleton.getInstance(DiceType.FAKE_RANDOM);
        Player sevenPlayer = new SevenPlayer("Dummy");
        GameStatus gameStatus = new GameStatus(new ArrayList<Player>(), 0, 104);
        assertEquals(0, sevenPlayer.play(gameStatus));
    }

    /**
     * Verifying the turn properly ends with a 6 roll.
     */
    @Test
    public void earlyExit() {
        Scanner scanner = new Scanner("6");
        DiceSingleton.getInstance(DiceType.FAKE_RANDOM);
        Player sevenPlayer = new SevenPlayer("Dummy");
        GameStatus gameStatus = new GameStatus(new ArrayList<Player>(), 0, 104);
        assertEquals(0, sevenPlayer.play(gameStatus));
    }

    /**
     * Verifies the maximum score a SevenPlayer can achieve in a single turn.
     */
    @Test
    public void maximumScore() {
        Scanner scanner = new Scanner("1 5 5 4 3 2 1");
        DiceSingleton.getInstance(DiceType.FAKE_RANDOM);
        Player sevenPlayer = new SevenPlayer("Dummy");
        GameStatus gameStatus = new GameStatus(new ArrayList<Player>(), 0, 104);
        assertEquals(11, sevenPlayer.play(gameStatus));
    }

    /**
     * Verifies the score after multiple turns.
     */
    @Test
    public void multipleTurnsExit() {
        Scanner scanner = new Scanner("1 2 3 4 5 6 7 8 9");
        DiceSingleton.getInstance(DiceType.FAKE_RANDOM);
        Player sevenPlayer = new SevenPlayer("Dummy");
        GameStatus gameStatus = new GameStatus(new ArrayList<Player>(), 0, 104);
        assertEquals(10, sevenPlayer.play(gameStatus));
        assertEquals(0, sevenPlayer.play(gameStatus));
    }
}
