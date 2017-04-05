package engine.graphics;

import java.util.ArrayList;
import java.util.Collection;

import engine.EntityInterface;
import javafx.scene.Group;

/**
 * @author Jay Doherty
 * 
 * This class handles the graphics display for the GamePlayer. The GameEngine can set
 * the Entities to display, and then call update() every frame to animate the graphics.
 */
public class GraphicsEngine {

	private Collection<EntityInterface> entities;
	private Group root;
	
	public GraphicsEngine() {
		entities = new ArrayList<EntityInterface>();
		root = new Group();
	}
	
	/**
	 * @return the graphical display for the game
	 */
	public Group getView() {
		return root;
	}
	
	/**
	 * Updates the display. This method just redraws all of the JavaFX Nodes for the
	 * current entities.
	 */
	public void update() {
		this.clearView();
		NodeFactory factory = new NodeFactory();
		for(EntityInterface e : entities) {
			root.getChildren().add(factory.getNodeFromEntity(e));	
		}
	}
	
	/**
	 * Sets the collection of entities that will be drawn on every call to update.
	 * @param entities : current entities to draw on screen
	 */
	public void setEntitiesCollection(Collection<EntityInterface> entities) {
		this.entities = entities;
	}
	
	private void clearView() {
		root.getChildren().clear();
	}
}
