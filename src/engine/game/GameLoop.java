package engine.game;

/**
 * Manages the highest level of time flow in the game.
 * @author Matthew Barbano
 *
 */
public interface GameLoop {
	/**
	 * External Engine API. Proceeds to the next frame of the animation while the game is playing. Responsibilities include:
	 * 	- loop through all Entities and call AlwaysEvent's act() (for Actions that occur on every frame, such as an enemy moving)
	 *  - for all three types of Observer Events, call updateObservables()
	 *  - and more
	 */
	public void step();
}
