package engine.actions;

import engine.Action;
import engine.game.gameloop.LevelStepStrategy;

public class NextLevelAction extends Action {
	private LevelStepStrategy levelStepStrategy;

	public NextLevelAction() {
	}
	
	public void setLevelStepStrategy(LevelStepStrategy levelStepStrategy){
		this.levelStepStrategy = levelStepStrategy;		
	}

	@Override
	public void act() {
		// TODO call method on "levelStepStrategy" to begin next level"

	}

}
