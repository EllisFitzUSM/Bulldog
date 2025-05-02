/**
 * WimpPlayer.java
 * A Bulldog player that only rolls once every turn.
 * @author Ellis Fitzgerald
 * @version April 28th, 2025
 */
public class WimpPlayer extends Player {

	/**
	 * Construct a WimpPlayer
	 * @param name Name string of WimpPlayer
	 */
	public WimpPlayer (String name) {
		super(name);
	}

	/**
	 * Decides if the Player needs to continue their turn
	 * @param gameStatus Status of the Bulldog Game encapsulated in an object
	 * @param turnScore Current turn score
	 * @param rollsCount Number of rolls
	 * @return boolean
	 */
	public boolean continueTurn(GameStatus gameStatus, int turnScore, int rollsCount) {
		return false;
	}

}