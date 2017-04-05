package engine.graphics;

import engine.EntityInterface;
import javafx.scene.Node;

public interface NodeFactoryInterface{
	public Node getNodeFromEntity(EntityInterface entity); //: takes Entity and returns Node. 
}
