package game_data;

import java.util.Collection;

import engine.Entity;
import javafx.scene.Node;

public interface NodeFactory{
	Collection<Node> getNodesFromEntities(Collection<Entity> entities);//: takes Entity and returns Node.
}
