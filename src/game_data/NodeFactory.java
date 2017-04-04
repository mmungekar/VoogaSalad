package game_data;

import java.util.Collection;

import engine.EntityInterface;
import javafx.scene.Node;

public interface NodeFactory{
	Collection<Node> getNodesFromEntities(Collection<EntityInterface> entities);//: takes Entity and returns Node. bind all nodes to 
}
