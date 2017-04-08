package engine.game.gameloop;

import java.util.ArrayList;
import java.util.List;

import engine.game.LevelManager;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * Manages the highest level of time flow in the game.
 * 
 * @author Matthew Barbano
 *
 */
public class GameLoop {
	private ObservableBundle observableBundle;
	private LevelManager levelManager;
	private Screen level1Screen;
	
	public GameLoop(Scene gameScene, String gameFilename){
		// Setup Observables - at beginning of entire game only
		observableBundle = new ObservableBundle();
		
		// Setup levelManager
		levelManager = new LevelManager();
		levelManager.loadAllSavedLevels(gameFilename);
		
		//Instantiate Screens and their StepStrategies  //TODO Add LevelSelectionStrategy screen
		List<Screen> screenList = new ArrayList<>();
		level1Screen = new Screen(new LevelStepStrategy(), screenList, observableBundle, levelManager, gameScene);
		screenList.add(level1Screen);
	}
	
	public void startTimeline(){
		level1Screen.start();
	}
	
	public Pane getGameView() {
		return level1Screen.getGameView();
	}
}

