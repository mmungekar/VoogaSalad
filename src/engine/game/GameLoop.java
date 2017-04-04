package engine.game;

import engine.Entity;
import engine.game.eventobserver.CollisionObservable;
import engine.game.eventobserver.EventObservable;
import engine.game.eventobserver.InputObservable;
import engine.game.eventobserver.TimerObservable;
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
	private LevelManager levelManager;
	private Timeline timeline;
	
	/**
	 * currentLevelManager is instantiated in the Game Authoring Environment (probably somewhere in the authoring.settings
	 * package)
	 * @param currentLevelManager
	 */
	public GameLoop(String gameFilename){
		//Setup levelManager
		levelManager = new LevelManager();
		levelManager.loadAllSavedLevels(gameFilename);
		
		//Setup Observables
		EventObservable inputObservable = new InputObservable();
		EventObservable collisionObservable = new CollisionObservable();
		EventObservable timerObservable = new TimerObservable();
		
		//Start the GAE's current level
		levelManager.startCurrentLevel();   //TODO in this method - load this Level's Entities and bind with appropriate Observables, as loaded from GAE
		
		//Listen to keyboard input // game is wherever these variables are stored and entities can have access to them, Nikita will access game
		scene.setOnKeyPressed(event -> { game.setInput(event.getKeyCode()); game.setNewInput(true);});
		
		setupTimeline();
	}
	
	private void keyPressed(){
		
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

	private void step(){
		//Update all Entities in current Level
		// first need to observe all inputs and collisions happening at the step
		// inputObserver.observe() <- updates the current input if needed 
		// collisionObserver.observe() <- finds all collisions that are happening currently
		
		for (Entity entity: levelManager.getCurrentLevel().getEntities()){
			entity.update();
		}
		
		/*
		collisionObserver
		for (Entity entity: levelManager.getCurrentLevel().getEntities()){
			entity.update();
		}
		inputObserver.setNewInput(false);
		*/
	}
}
