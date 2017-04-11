package engine.game.gameloop;

import java.util.ResourceBundle;

import engine.entities.CharacterEntity;
import engine.game.LevelManager;
import engine.graphics.GraphicsEngine;
import javafx.scene.Scene;

public abstract class TransitionStepStrategy implements StepStrategy {
	private static final String RESOURCES_NAME =  "resources/TransitionScreen";
	private static final int FRAME_DURATION = 150;
	
	private int frameNumber = 1;
	private String resourceFileTextName;
	
	private ObservableBundle observableBundle;   //TODO These are just being used to pass to next level - too many parameters in method names; find a better way!
	private LevelManager levelManager;
	private Scene gameScene;
	private GraphicsEngine graphicsEngine;
	private Screen screen;
	
	public TransitionStepStrategy(String resourceFileTextName){
		 this.resourceFileTextName = resourceFileTextName;
	}
	
	@Override
	public void setup(ObservableBundle newObservableBundle, LevelManager levelManager, Scene gameScene, Screen screen,
			GraphicsEngine graphicsEngine) {
		this.observableBundle = newObservableBundle;   //TODO These are just being used to pass to next level - too many parameters in method names; find a better way!
		this.levelManager = levelManager;
		this.gameScene = gameScene;
		this.graphicsEngine = graphicsEngine;
		this.screen = screen;
		//TODO Also display number of lives left for lose a life transition
		graphicsEngine.fillScreenWithText(ResourceBundle.getBundle(RESOURCES_NAME).getString(resourceFileTextName));
	}
	
	@Override
	public void step() {
		System.out.println("Step in Transition Screen" + frameNumber);
		//TODO Could add animation here
		if(frameNumber == FRAME_DURATION){
			System.out.println("move to next screen");
			moveToNextScreen();
		}
		frameNumber++;
	}
	
	//protected abstract String getSubclassSpecificText();
	protected abstract StepStrategy getNextStepStrategy(LevelManager levelManager);
	protected abstract int nextLevelNumber(LevelManager levelManager);
	protected abstract boolean hasNextScreen();
	
	private void moveToNextScreen(){
		screen.getTimeline().stop();
		boolean hasNextLevel = levelManager.setLevelNumber(nextLevelNumber(levelManager));  //this boolean is just a safety measure, but TransitionStepStrategies should only stay on current level if moving to a new level at all
		System.out.println("Levelnum = " + levelManager.getLevelNumber());
		if(hasNextLevel && hasNextScreen()){
			Screen nextScreen = new Screen(getNextStepStrategy(levelManager), observableBundle, levelManager, gameScene, graphicsEngine);
			System.out.println(nextScreen);
			nextScreen.getTimeline().play();
		}
		//TODO Throw exception here or do something...
	}
	
	
}
