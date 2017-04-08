package engine.actions;

import engine.Action;
import engine.Entity;
import engine.entities.CharacterEntity;
import engine.game.gameloop.LevelStepStrategy;

/**
 * Action for when a character dies. Can only be attached to a CharacterEntity; if attached to another type of 
 * Entity, will throw an exception.
 * @author Matthew Barbano
 *
 */
public class DieAction extends Action{
	private LevelStepStrategy levelStepStrategy;
	private Entity tempEntity; //TEMPORARY, to get around a NullPointerException that Nikita needs to fix (getEntity() returns null)
	
	public DieAction(){
		
	}
	
	public void setLevelStepStrategy(LevelStepStrategy levelStepStrategy) {
		this.levelStepStrategy = levelStepStrategy;
	}
	
	public void tempEntity(Entity tempEntity){
		this.tempEntity = tempEntity;
	}

	@Override
	public void act() {
		//CharacterEntity entity = (CharacterEntity) getEntity();  //TODO Throw VoogaException here if not CharacterEntity
		//Decrement lives
		CharacterEntity entity = (CharacterEntity) tempEntity;
		entity.setLives(entity.getLives() - 1);
		System.out.println("Die action triggered");
		
		//Call transitionToNextLevel(boolean gameOver) (never called from step()!) -> how get the GameLoop/Screen/LevelStepStrategy object? 
		levelStepStrategy.endLevel(entity.getLives() <= 0);
	}

}
