/**
 * Abstract Dice Super Class
 * @author Ellis Fitzgerlad
 * @version 0.7
 */
public abstract class DiceSuper {
    protected int sides;
    public DiceSuper(int sides) {
        this.sides = sides;
    }
    abstract int roll();
}

