package engine.game.gameloop;

/**
 * Class which passes GameObjects (that is, concrete Entities, Events, or Actions) data from the Game Loop.
 * Example: Suppose the DieAction's act() needs to call LevelStepStrategy's endLevel(), one would do:
 * public void act(){
 * 		GameObjectPasser gameObjectPasser = new GameObjectPasser();
 * 		gameObjectPasser.getCurrentStepStrategy().endLevel();   (but of course with appropriate modifications to take care of Game Over/lives)
 * }
 * 
 * This eliminates looping over all Entites/Events/Actions in Game Loop and helper classes.
 * 
 * @author Matthew Barbano
 *
 */
public class GameObjectPasser {
	private StepStrategy currentStepStrategy;
	private ObservableBundle observableBundle;
	
	public GameObjectPasser(){
		
	}

	public StepStrategy getCurrentStepStrategy() {
		return currentStepStrategy;
	}

	public void setCurrentStepStrategy(StepStrategy currentStepStrategy) {
		this.currentStepStrategy = currentStepStrategy;
	}

	public ObservableBundle getObservableBundle() {
		return observableBundle;
	}

	public void setObservableBundle(ObservableBundle observableBundle) {
		this.observableBundle = observableBundle;
	}
}
