package engine.game.gameloop;

import engine.game.LevelManager;

import java.util.Collection;

import engine.Entity;
import engine.entities.CharacterEntity;

public class Scorebar {

	private LevelManager levelManager;
	
	public Scorebar(LevelManager manager) {
		levelManager = manager;
	}
	
	public String getTime() {
		if(levelManager == null) {
			return "";
		}
		return levelManager.getCurrentLevel().getTimerManager().toString();
	}
	
	public String getLives() {
		if(levelManager == null) {
			return "";
		}
		Collection<Entity> entities = levelManager.getCurrentLevel().getEntities();
		for(Entity entity : entities) {
			if(entity instanceof CharacterEntity) {
				return Integer.toString(((CharacterEntity) entity).getLives());
			}
		}
		return "";
	}
	
	public String getScore() {
		if(levelManager == null) {
			return "";
		}
		return "0";
	}
}
