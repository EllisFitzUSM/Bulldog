/**
 * Referee class that faciliates the ordering of players in a game of Bulldog
 * @author DeepSeek
 * @version 0.7
 */
public class Referee {
    private static Referee instance;
    private int currentPlayerIndex;
    private int winningScore = 104;

    /**
     * Creates an instance of Referee.
     * Private for singleton pattern.
     */
    private Referee() {}

    /**
     * Singleton access to Referee instance.
     * @return Referee instance.
     */
    public static synchronized Referee getInstance() {
        if (instance == null) instance = new Referee();
        return instance;
    }

    /**
     * Set the winning score amount to achieve before a game over.
     * @author Ellis Fitzgerald
     * @param score to set to
     */
    public void setWinningScore(int score) {
        winningScore = score;
    }

    /**
     * Starts the game
     * @param model model containing the collection of players.
     * @param listener listener which responds to any events (such as the view)
     */
    public void startGame(GameModel model, GameEventListener listener) {
        turnCycle(model, listener);
    }

    /**
     * Gets the player currently in a turn.
     * @param model model containing the collection of players.
     * @return The player currently in a turn.
     */
    public Player getCurrentPlayer(GameModel model) {
        return model.getPlayer(currentPlayerIndex);
    }

    /**
     * Plays through the turn cycle of all the players
     *
     * @param model Game model of all the players
     * @param listener Game event listener
     */
    public void turnCycle(GameModel model, GameEventListener listener) {
        while(true) {
            Player current = model.getPlayer(currentPlayerIndex);
            listener.onTurnStart(current);
            int turnScore = playTurn(current, model.getGameStatus(currentPlayerIndex, winningScore), listener);

            model.addToPlayerScore(currentPlayerIndex, turnScore);

            if (model.getPlayerScore(currentPlayerIndex) >= winningScore) {
                listener.onGameOver(model.getPlayer(currentPlayerIndex));
                break;
            } else {
                listener.onTurnEnd(model.getPlayer(currentPlayerIndex), turnScore);
                currentPlayerIndex = (currentPlayerIndex + 1) % model.getNumberOfPlayers();
            }
        }
    }

    /**
     * Plays a turn for a player
     *
     * @param player
     * @param gameStatus
     * @param listener
     * @return
     */
    public int playTurn(Player player, GameStatus gameStatus, GameEventListener listener) {
        int turnScore = 0;
        int rollsCount = 0;
        do {
            int roll = DiceSingleton.getInstance().roll();
            listener.onRoll(player, roll);
            if(roll == 6) {
                return 0;
            }
            turnScore += roll;
            rollsCount++;
        } while(player.continueTurn(gameStatus, turnScore, rollsCount));
        return turnScore;
    }

}