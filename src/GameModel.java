import java.util.ArrayList;

/**
 * @author Ellis Fitzgerald
 * @version Mon April 14
 * Game Model - Manages player data and scores
 */
public class GameModel {
    private final ArrayList<Player> players;

    /**
     * Construct a GameModel
     */
    public GameModel() {
        this.players = new ArrayList<>();
    }

    /**
     * Get player at index
     * @param index to access player
     * @return Player at index
     */
    public Player getPlayer(int index) {
        validateIndex(index);
        return players.get(index);
    }

    /**
     * Add player to ArrayList
     * @param player to add
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Get a Player's name
     * @param index to access the Player
     * @return Name
     */
    public String getPlayerName(int index) {
        validateIndex(index);
        return players.get(index).getName();
    }

    /**
     * Get a player score
     * @param index to acess the Player
     * @return Player's score
     */
    public int getPlayerScore(int index) {
        validateIndex(index);
        return players.get(index).getScore();
    }

    /**
     * Add a score value to a Player
     * @param index to access the Player
     * @param scoreToAdd amount of score to add
     */
    public void addToPlayerScore(int index, int scoreToAdd) {
        validateIndex(index);
        Player p = players.get(index);
        p.setScore(p.getScore() + scoreToAdd);
    }

    /**
     * @return Number of players
     */
    public int getNumberOfPlayers() {
        return players.size();
    }

    /**
     * Confirm if the index is within the bounds of the players
     * @param index to validate
     */
    private void validateIndex(int index) {
        if(index < 0 || index >= players.size()) {
            throw new IllegalArgumentException("Invalid player index: " + index);
        }
    }
}