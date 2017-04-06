package engine.game;

import engine.game.selectiongroup.ListSG;
import engine.game.selectiongroup.SelectionGroup;

/**
 * Holds all the levels in the current game and allows for game-wide behavior.
 * 
 * IMPORTANT NOTE TO ANYONE USING THIS CLASS: When using the methods in this
 * class, levels are one-indexed (likely the only information anyone else will
 * need to know). If for some reason anyone needs to use SelectionGroup, that is
 * zero-indexed.
 * 
 * @author Matthew Barbano
 *
 */
public class LevelManager {
	private SelectionGroup<Level> levels; // zero-indexed
	int currentLevel; // one-indexed

	public LevelManager() {
		levels = new ListSG<>(); // TODO: Change to reflection, or something
									// more modular
		currentLevel = 1;
	}

	/**
	 * External Engine API. Needed for authoring.
	 * 
	 * @param level
	 * @return
	 */
	public void addLevel(Level level) {
		levels.add(level);
	}

	/**
	 * External Engine API. Needed for gameplay.
	 * 
	 * @return
	 */
	public Level getCurrentLevel() {
		return levels.get(currentLevel - 1);
	}

	public void moveToNextLevel() {
		// TODO
	}

	/**
	 * External Engine API. Called by the Game Player during gameplay. Pulls up
	 * the pause menu.
	 * 
	 * @return
	 */
	public void pause() {

	}

	/**
	 * External Engine API. Called by the GAE during editing and uses methods
	 * from Game Data.
	 * 
	 * @return
	 */
	public void saveCurrentLevel() {

	}

	public void saveAllLevels() {
		//GameDataExternalAPI gameData = new GameDataExternalAPI();
		//gameData.saveGame(levels); // TODO Ask Game Data people if they can save
									// the entire SelectionGroup object (so I
									// don't have to reconstruct a graph from a
									// List...alternatively if I have them save
									// the edge list, this will be OK: create
									// getSaveableList() method in
									// SelectionGroup interface)
		System.out.println("Saved game");
	}

	/**
	 * External Engine API. Called by the GAE during editing and uses methods
	 * from Game Data.
	 * 
	 * @param filename
	 * @return
	 */
	public void openLevel(String filename) {
		
	}
	
	public void loadAllSavedLevels(String filename){
		//GameDataExternalAPI gameData = new GameDataExternalAPI();
		//levels = gameData.loadGame(filename); //TODO tell Game Data people to change this to return SelectionGroup<Level> (or List<Level>, in which case I need to convert to the Selection Group here)
		levels.add(new Level());
		System.out.println("Loaded the current level");
	}

	public void startCurrentLevel() {
		getCurrentLevel().start();
		
	}

}
