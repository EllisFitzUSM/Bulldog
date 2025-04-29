import java.util.List;

/**
 * Bulldog Game Status encapsulated in an object
 * @author DeepSeek & Ellis Fitzgerald
 * @version April 27th, 2025
 */
public class GameStatus {
    private final List<Player> players;
    private final int currentPlayerIndex;
    private final int winningScore;

    /**
     * Constructs a GameStatus object
     * @param players List of Player Objects
     * @param currentPlayerIndex The current player index in players
     * @param winningScore The winning score to get
     */
    public GameStatus(List<Player> players, int currentPlayerIndex, int winningScore) {
        this.players = players;
        this.currentPlayerIndex = currentPlayerIndex;
        this.winningScore = winningScore;
    }

    /**
     * Gets the player at currentPlayerIndex
     * @return Player object
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /**
     * Get the currentPlayer score
     * @return int score
     */
    public int getCurrentPlayerScore() {
        return getCurrentPlayer().getScore();
    }

    /**
     * Get the winning score
     * @return int winning score
     */
    public int getWinningScore() {
        return winningScore;
    }

    /**
     * Get all the players in the game status
     * @return List of players
     */
    public List<Player> getAllPlayers() {
        return players;
    }
}