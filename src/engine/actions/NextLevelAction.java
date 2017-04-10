package engine.actions;

import engine.Action;
import engine.game.gameloop.LevelStepStrategy;

/**
 * Starts the next level of a game.
 * @author Kyle Finke
 *
 */
public class NextLevelAction extends Action {
	private LevelStepStrategy levelStepStrategy;
	
	public void setLevelStepStrategy(LevelStepStrategy levelStepStrategy){
		this.levelStepStrategy = levelStepStrategy;		
	}

	@Override
	public void act() {
		levelStepStrategy.startNextLevel();
	}

}