/**
 * GameEventListener for the main program to implement to allow the players to modify the GUI.
 */
public interface GameEventListener {

    /**
     * Function to be called when a turn is started.
     * @param player Player whose turn started
     */
    void onTurnStart(Player player);

    /**
     * Function to be called when a turn is ended.
     * @param player Player whose turn ended
     */
    void onTurnEnd(Player player, int turnScore);

    /**
     * Function to be called after a player makes a roll.
     * @param player The player who rolled
     * @param roll The amount scored
     */
    void onRoll(Player player, int roll);

    /**
     * Function to be called after a decision has been made to continue rolling or not.
     * @param player The player who has made decision
     * @param currentTurnScore The Players current turn total
     */
    void onTurnDecision(Player player, int currentTurnScore);

    /**
     * Function to be called when a Player wins Bulldog
     * @param winner The player who won
     */
    void onGameOver(Player winner);

}