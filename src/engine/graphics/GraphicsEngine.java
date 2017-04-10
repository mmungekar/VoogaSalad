package engine.graphics;

import java.util.ArrayList;
import java.util.Collection;

import engine.graphics.cameras.Camera;
import engine.graphics.cameras.ScrollingCamera;
import engine.Entity;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

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
	
	public GraphicsEngine() {
		this.camera = new ScrollingCamera(0,0);
		entities = new ArrayList<Entity>();
		this.setupView();
	}
	
	/**
	 * @return the graphical display for the game
	 */
	public Pane getView() {
		return displayArea;
	}
	
	public void setCamera(Camera newCamera) {
		this.camera = newCamera;
	}
	
	public void fillScreenWithText(String text) {
		this.clearView();
		Label label = new Label(text);
		label.setPrefSize(displayArea.getWidth(), displayArea.getHeight());
		label.setFont(new Font(displayArea.getWidth()/text.length()));
		displayArea.getChildren().add(label);
	}
	
	/*
	public void setScoreBar(ScoreBar currentManager) {
		this.scorebar = currentManager;
	}
	*/
	
	/**
	 * Sets the collection of entities that will be drawn on every call to update.
	 * @param entities : current entities to draw on screen
	 */
	public void setEntitiesCollection(Collection<Entity> entities) {
		this.entities = entities;
		this.redrawView();
	}
	
	/**
	 * Call this every frame to animate the camera.
	 */
	public void updateFrame() {
		camera.update();
		displayArea.setTranslateX(-camera.getX());
		displayArea.setTranslateY(-camera.getY());
	}
	
	/**
	 * Call this conservatively, it seems to require a lot of memory.
	 */
	public void redrawView() {
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
			node.xProperty().bind(e.xReadOnlyProperty());
			node.yProperty().bind(e.yReadOnlyProperty());
			displayArea.getChildren().add(node);	
		}
	}
	
	private void setupView() {
		displayArea = new Pane();
		displayArea.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		HBox.setHgrow(displayArea, Priority.ALWAYS);
		VBox.setVgrow(displayArea, Priority.ALWAYS);
		this.clipAtEdges(displayArea);
		this.drawAllEntities();
	}
	
	private void clipAtEdges(Pane pane) {
		Rectangle clipBoundaries = new Rectangle();
		clipBoundaries.widthProperty().bind(pane.widthProperty());
		clipBoundaries.heightProperty().bind(pane.heightProperty());
		pane.setClip(clipBoundaries);
	}
}
