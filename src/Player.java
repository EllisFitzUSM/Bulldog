/**
 * Player class
 * @author Ellis Fitzgerald
 * @version April 24th, 2025
 */
public abstract class Player {

	protected GameEventListener listener;
	private final String name;   	// The name of the Player
	private int score;		// The score earned by this Player during the game

	/**
	 * Constructs a player
	 * @param name player name
	 */
	public Player (String name) {
		this.name = name;
		this.score = 0;
	}


	/**
	 * Get the name of the player
	 * @return String representing players name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get the score that the player has
	 * @return Int representing players score
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Set the score of the player
	 * @param score Int representation of the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Decides if the Player needs to continue their turn
	 * @param gameStatus Status of the Bulldog Game encapsulated in an object
	 * @param turnScore Current turn score
	 * @param rollsCount Number of rolls
	 * @return boolean
	 */
	public abstract boolean continueTurn(GameStatus gameStatus, int turnScore, int rollsCount);
	
}