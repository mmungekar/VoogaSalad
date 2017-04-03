package engine;

import java.util.Collection;

public interface LevelInterface {
	//external
	Collection<Entity> getEntities(); //needed for gameplay
	void addEntity(Entity entity); //needed for authoring

}
