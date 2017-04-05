package authoring.canvas;

import java.util.ArrayList;
import java.util.List;

import authoring.Workspace;
import authoring.views.View;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * 
 * The Canvas is where you can drag Entities to create your own level. It can
 * contain multiple layers for backgrounds and foregrounds. Each background and
 * foreground can move at their own respective velocities. The Canvas is located
 * in the center of the workspace.
 * 
 * @author jimmy
 *
 */
public class Canvas extends View
{

	private Workspace workspace;
	private final int TILE_SIZE = 25;

	private Group gridNodes;
	private ScrollPane scrollScreen;
	private List<Node> entities;
	private Pane layer;
	private double width;
	private double height;

	public Canvas(Workspace workspace)
	{
		super(workspace.getResources().getString("CanvasTitle"));
		this.workspace = workspace;
		setup();
	}

	private void setup()
	{
		gridNodes = new Group();
		entities = new ArrayList<Node>();
		scrollScreen = createLayer();
		this.setCenter(scrollScreen);
	}

	private ScrollPane createLayer()
	{
		scrollScreen = new ScrollPane();
		layer = new Pane();
		layer.setPrefHeight(height);
		layer.setPrefWidth(width);
		layer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

		layer.getChildren().add(gridNodes);
		scrollScreen.setContent(layer);
		// clickToAddEntity();
		updateDisplay();
		return scrollScreen;
	}

	public void addEntity(Node entity)
	{
		this.addEntity(entity, 0, 0);
	}

	public void addEntity(Node entity, double x, double y)
	{
		Point2D tiledCoordinate = getTiledCoordinate(x, y);
		entity.setTranslateX(tiledCoordinate.getX());
		entity.setTranslateY(tiledCoordinate.getY());
		entities.add(entity);
		layer.getChildren().add(entity);
		entity.setCursor(Cursor.CLOSED_HAND);
		makeDraggable(entity);
		updateLayerBounds();
	}

	private void drawGrid()
	{
		for (int i = 0; i < width / TILE_SIZE; i++) {
			for (int j = 0; j < height / TILE_SIZE; j++) {
				drawGridDot(i, j);
			}
		}
	}

	private void drawGridDot(double tileX, double tileY)
	{
		Circle gridMarker = new Circle();
		gridMarker.setCenterX(tileX * TILE_SIZE);
		gridMarker.setCenterY(tileY * TILE_SIZE);
		gridMarker.setRadius(1);
		gridMarker.setFill(Color.GREY);
		gridNodes.getChildren().add(gridMarker);
	}
	//
	// private void clickToAddEntity()
	// {
	// layer.setOnMouseClicked(e -> {
	// if (e.isShiftDown()) {
	// Rectangle rect = new Rectangle();
	// rect.setWidth(100);
	// rect.setHeight(100);
	// rect.setFill(Color.CORAL);
	// this.addEntity(rect, e.getX(), e.getY());
	// }
	// });
	// }

	/**
	 * Makes the given node draggable so that you can move it around on the
	 * canvas by dragging it. Moreover, dragging the node snaps it to the grid.
	 * 
	 * @param node
	 *            Node to be made draggable
	 */
	private void makeDraggable(Node node)
	{
		node.setOnMouseDragged(new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event)
			{
				node.setCursor(Cursor.NONE);
				Bounds scrollViewportBounds = scrollScreen.getViewportBounds();

				double settingsWidth = workspace.getPane().getChildrenUnmodifiable().get(0).getBoundsInParent()
						.getWidth();

				double horizontalScrollAmount = scrollScreen.getHvalue() * (width - (scrollViewportBounds.getWidth()));
				double verticalScrollAmount = scrollScreen.getVvalue() * (height - (scrollViewportBounds.getHeight()));

				double nodeWidth = node.getBoundsInParent().getWidth();
				double nodeHeight = node.getBoundsInParent().getHeight();

				// double tabHeaderHeight = levelsPane.getTabMaxHeight();

				double newX = event.getSceneX() - settingsWidth + horizontalScrollAmount - (nodeWidth / 2);
				// double newY = event.getSceneY() - tabHeaderHeight +
				// verticalScrollAmount - (nodeHeight / 2);
				double newY = event.getSceneY() + verticalScrollAmount - (nodeHeight / 2);

				if (newX < 0) {
					newX = 0;
				}
				if (newY < 0) {
					newY = 0;
				}

				Point2D translateAmount = getTiledCoordinate(newX, newY);
				node.setTranslateX(translateAmount.getX());
				node.setTranslateY(translateAmount.getY());

				updateDisplay();

				if (width > scrollViewportBounds.getMaxX()) {
					scrollScreen.setHvalue(newX / (width - nodeWidth));
				}
				if (height > scrollViewportBounds.getMaxY()) {
					scrollScreen.setVvalue(newY / (height - nodeHeight));
				}

			}
		});

		node.setOnMouseReleased(e -> {
			node.setCursor(Cursor.CLOSED_HAND);
		});
	}

	private void updateDisplay()
	{
		updateLayerBounds();
		layer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		gridNodes.getChildren().clear();
		drawGrid();
		layer.setPrefHeight(height);
		layer.setPrefWidth(width);
	}

	private void updateLayerBounds()
	{
		double maxX = 0;
		double maxY = 0;
		for (Node entity : entities) {
			double nodeMaxX = entity.getTranslateX() + entity.getBoundsInParent().getWidth();
			double nodeMaxY = entity.getTranslateY() + entity.getBoundsInParent().getHeight();
			if (nodeMaxX > maxX) {
				maxX = nodeMaxX;
			}
			if (nodeMaxY > maxY) {
				maxY = nodeMaxY;
			}
		}
		this.width = maxX;
		this.height = maxY;
	}

	private Point2D getTiledCoordinate(double x, double y)
	{
		double gridX = ((int) x / TILE_SIZE) * TILE_SIZE;
		double gridY = ((int) y / TILE_SIZE) * TILE_SIZE;
		return new Point2D(gridX, gridY);
	}
}
