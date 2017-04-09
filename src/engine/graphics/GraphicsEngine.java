package engine.graphics;

import java.util.ArrayList;
import java.util.Collection;

import engine.graphics.cameras.Camera;
import engine.graphics.cameras.ScrollingCamera;
import engine.Entity;
import engine.EntityInterface;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * @author Jay Doherty
 * 
 * This class handles the graphics display for the GamePlayer. The GameEngine can set
 * the Entities to display, and then call update() every frame to animate the graphics.
 */
public class GraphicsEngine {

	private Camera camera;
	private Collection<Entity> entities;
	private Pane displayArea;
	
	public GraphicsEngine(Camera camera) {
		this.camera = camera;
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
		this.updateCamera();
	}
	
	/**
	 * Sets the collection of entities that will be drawn on every call to update.
	 * @param entities : current entities to draw on screen
	 */
	public void setEntitiesCollection(Collection<Entity> entities) {
		this.entities = entities;
		this.clearView();
		this.drawAllEntities();
	}
	
	private void clearView() {
		displayArea.getChildren().clear();
	}
	
	private void drawAllEntities() {
		NodeFactory factory = new NodeFactory();
		for(Entity e : entities) {
			ImageView node = (ImageView)factory.getNodeFromEntity(e);
			node.xProperty().bind(e.xProperty());
			node.yProperty().bind(e.yProperty());
			displayArea.getChildren().add(node);	
		}
	}
	
	private void setupView() {
		displayArea = new Pane();
		displayArea.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.clipAtEdges(displayArea);
		this.drawAllEntities();
	}
	
	private void clipAtEdges(Pane pane) {
		Rectangle clipBoundaries = new Rectangle();
		clipBoundaries.widthProperty().bind(pane.widthProperty());
		clipBoundaries.heightProperty().bind(pane.heightProperty());
		pane.setClip(clipBoundaries);
	}
	
	private void updateCamera() {
		camera.update();
		displayArea.setTranslateX(-camera.getX());
		displayArea.setTranslateY(-camera.getY());
	}
}
