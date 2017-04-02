package engine.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Manages the highest level of time flow in the game.
 * @author Matthew Barbano
 *
 */
public class GameLoop {
	public static final int FRAME_TIME_MILLISECONDS = 20;
	LevelManager levelManager;
	Timeline timeline;
	
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
		//From ExampleBounce.java (will likely upgrade)
		KeyFrame frame = new KeyFrame(Duration.millis(FRAME_TIME_MILLISECONDS),
                e -> step());
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(frame);
	}
	
	/**
	 * External Engine API. Proceeds to the next frame of the animation while the game is playing. Responsibilities include:
	 * 	- loop through all Entities and call AlwaysEvent's act() (for Actions that occur on every frame, such as an enemy moving)
	 *  - for all three types of Observer Events, call updateObservables()
	 *  - and more
	 */
	public void startTimeline(){
		timeline.play();
	}
	
	private boolean done = false;
	private void step(){
		if(!done){
			levelManager.startCurrentLevel();
		}
		done = true;
	}
}
