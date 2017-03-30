package engine;

import java.util.List;

public interface Level {
	//external
	List<Entity> getEntities(); //needed for gameplay
	void addEntity(Entity entity); //needed for authoring

}
