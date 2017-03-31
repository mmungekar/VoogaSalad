package engine.game;

/**
 * Holds all the levels in the current game and allows for game-wide behavior.
 * @author Matthew Barbano
 *
 */
public interface LevelManager {
	/**
	 * External Engine API. Needed for authoring.
	 * @param level
	 * @return
	 */
	public void addLevel(Level level);
	/**
	 * External Engine API. Needed for gameplay.
	 * @return
	 */
	public void getCurrentLevel();
	/**
	 * External Engine API. Called by the Game Player during gameplay. Pulls up the pause menu.
	 * @return
	 */
	public void pause();
	/**
	 * External Engine API. Called by the GAE during editing and uses methods from Game Data.
	 * @return
	 */
	public void saveCurrentLevel();
	/**
	 * External Engine API. Called by the GAE during editing and uses methods from Game Data.
	 * @param filename
	 * @return
	 */
	public void openLevel(String filename);

}
