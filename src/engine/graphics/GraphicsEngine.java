package engine.graphics;

import java.util.ArrayList;
import java.util.Collection;

import engine.Entity;
import engine.EntityInterface;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * @author Jay Doherty
 * 
 * This class handles the graphics display for the GamePlayer. The GameEngine can set
 * the Entities to display, and then call update() every frame to animate the graphics.
 */
public class GraphicsEngine {

	private Collection<Entity> entities;
	private Pane displayArea;
	
	public GraphicsEngine() {
		entities = new ArrayList<Entity>();
		this.setupView();
	}
	
	/**
	 * @return the graphical display for the game
	 */
	public Pane getView() {
		return displayArea;
	}
	
	/**
	 * Updates the display. This method just redraws all of the JavaFX Nodes for the
	 * current entities.
	 */
	public void update() {
		this.clearView();
		NodeFactory factory = new NodeFactory();
		for(EntityInterface e : entities) {
			displayArea.getChildren().add(factory.getNodeFromEntity(e));	
		}
	}
	
	/**
	 * Sets the collection of entities that will be drawn on every call to update.
	 * @param entities : current entities to draw on screen
	 */
	public void setEntitiesCollection(Collection<Entity> entities) {
		this.entities = entities;
	}
	
	private void clearView() {
		displayArea.getChildren().clear();
	}
	
	private void setupView() {
		displayArea = new Pane();
		displayArea.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.clipAtEdges(displayArea);
	}
	
	private void clipAtEdges(Pane pane) {
		Rectangle clipBoundaries = new Rectangle();
		clipBoundaries.widthProperty().bind(pane.widthProperty());
		clipBoundaries.heightProperty().bind(pane.heightProperty());
		pane.setClip(clipBoundaries);
	}
}
