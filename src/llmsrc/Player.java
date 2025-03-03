package llmsrc; /********************************************************/
/* David Levine                                         */
/* Login ID: david.b.levine@maine.edu                   */
/* COS 497, Summer 2024                                 */
/* Programming Assignment 6                             */
/* abstract humansrc.Player class: holds generic info about a    */
/*           player of the game Bulldog                 */
/*      See Kettering University, CS-101, Prog 6        */
/********************************************************/

public abstract class Player {

	protected GameEventListener listener;

	public void setGameEventListener(GameEventListener listener) {
		this.listener = listener;
	}
	
	private String name;   	// The name of the humansrc.Player
	
	private int score;		// The score earned by this humansrc.Player during the game
	
	/********************************************************/
	/* Constructor: humansrc.Player                                  */
	/* Purpose: Create a new humansrc.Player object                  */
	/* Parameters:                                          */
	/*   String name:  the name of the humansrc.Player being created */
	/********************************************************/
	public Player (String name) {
		this.name = name;
		this.score = 0;
	}
	
	/********************************************************/
	/* Method:  getName                                     */
	/* Purpose: return the name of this humansrc.Player              */
	/* Parameters:                                          */
	/*   none                                               */
	/* Returns:                                             */
	/*   the name of this humansrc.Player                            */
	/********************************************************/
	public String getName() {
		return this.name;
	}

	/********************************************************/
	/* Method:  getScore                                    */
	/* Purpose: return the current score of this humansrc.Player     */
	/* Parameters:                                          */
	/*   none                                               */
	/* Returns:                                             */
	/*   the current score of this humansrc.Player                   */
	/********************************************************/
	public int getScore() {
		return this.score;
	}
	
	/********************************************************/
	/* Method:  setScore                                    */
	/* Purpose: set the current score of this humansrc.Player        */
	/* Parameters:                                          */
	/*   int score - the new value of the score             */
	/* Returns:                                             */
	/*   none                                               */
	/********************************************************/
	public void setScore(int score) {
		this.score = score;
	}
	
	/********************************************************/
	/* Method:  play                                        */
	/* Purpose: abstract method that encapsulates one turn  */
	/*          for this humansrc.Player                             */
	/* Parameters:                                          */
	/*   none                                               */
	/* Returns:                                             */
	/*   the score earned by the player on this turn,       */
	/*       which will be zero if a six was rolled         */
	/********************************************************/
	public abstract int play();

	public int rollDie() {
		return (int) (Math.random() * 6 + 1);
	}
	
}