/**
 * GameEventListener for the main program to implement to allow the players to modify the GUI.
 */
public interface GameEventListener {

    /**
     * Function to be called after a player makes a roll.
     * @param player The player who rolled
     * @param roll The amount scored
     */
    void onRoll(Player player, int roll);

    /**
     * Function to be called after a message is intended to be sent from a player.
     * @param message The message to be sent
     */
    void onMessage(String message);

    /**
     * Function to be called after a decision has been made to continue rolling or not.
     * @param player The player who has made decision
     * @param currentTurnScore The Players current turn total
     */
    void onTurnDecision(Player player, int currentTurnScore);

}