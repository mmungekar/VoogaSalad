package engine.actions;

import engine.Action;
import engine.Entity;
import engine.game.gameloop.LevelStepStrategy;

public class NextLevelAction extends Action {
	private LevelStepStrategy levelStepStrategy;

	public NextLevelAction(Entity entity) {
		super(entity);
	}
	
	public void setLevelStepStrategy(LevelStepStrategy levelStepStrategy){
		this.levelStepStrategy = levelStepStrategy;		
	}

	@Override
	public void act() {
		levelStepStrategy.startNextLevel();
	}

}
