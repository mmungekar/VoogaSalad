package engine.game;

/**
 * Manages the highest level of time flow in the game.
 * @author Matthew Barbano
 *
 */
public class GameLoop {
	LevelManager levelManager;
	
	/**
	 * currentLevelManager is instantiated in the Game Authoring Environment (probably somewhere in the authoring.settings
	 * package)
	 * @param currentLevelManager
	 */
	public GameLoop(String gameFilename){
		levelManager = new LevelManager();
		levelManager.loadAllSavedLevels(gameFilename);
		setupTimeline();
	}
	private void setupTimeline(){
		//TODO
	}
	
	/**
	 * External Engine API. Proceeds to the next frame of the animation while the game is playing. Responsibilities include:
	 * 	- loop through all Entities and call AlwaysEvent's act() (for Actions that occur on every frame, such as an enemy moving)
	 *  - for all three types of Observer Events, call updateObservables()
	 *  - and more
	 */
	public void startTimeline(){
		step();  //TODO
	}
	
	private void step(){
		levelManager.startCurrentLevel();
	}
}
