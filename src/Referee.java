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
        startNextTurn(model, listener);
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
     * Starts the next turn based on the currentPlayerIndex
     * @param model model containing the collection of players.
     * @param listener listener which responds to any events (such as the view)
     */
    private void startNextTurn(GameModel model, GameEventListener listener) {
        Player current = model.getPlayer(currentPlayerIndex);
        listener.onTurnStart(current);
        executePlayerTurn(model, listener, current);
    }

    /**
     * Starts the next turn based on the currentPlayerIndex
     * @param model model containing the collection of players.
     * @param listener listener which responds to any events (such as the view)
     */
    private void executePlayerTurn(GameModel model, GameEventListener listener, Player player) {
        new Thread(() -> {

            int result = player.play(model.getGameStatus(currentPlayerIndex , winningScore));
            processTurnResult(model, listener, player, result);
        }).start();
    }

    /**
     * Process a turn result (if it should cause a game over or if the next turn should start)
     * @param model model containing the collection of players.
     * @param listener listener which responds to any events (such as the view)
     * @param player Player whose turn has finished.
     * @param turnScore score they gained this turn.
     */
    private void processTurnResult(GameModel model, GameEventListener listener, Player player, int turnScore) {
        model.addToPlayerScore(currentPlayerIndex, turnScore);

        if (model.getPlayerScore(currentPlayerIndex) >= winningScore) {
            listener.onGameOver(player);
        } else {
            listener.onTurnEnd(player, turnScore);
            currentPlayerIndex = (currentPlayerIndex + 1) % model.getNumberOfPlayers();
            startNextTurn(model, listener);
        }
    }

}