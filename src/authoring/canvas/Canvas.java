package authoring.canvas;

import authoring.Workspace;
import authoring.views.View;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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

	// I recommend having a tabbed structure in here, to account for multiple
	// levels.

	private Workspace workspace;
	private final int TILE_SIZE = 25;

	private TabPane layers;
	private ScrollPane scrollScreen;
	private Pane layer;
	private int width;
	private int height;

	public Canvas(Workspace workspace)
	{
		super(workspace.getResources().getString("CanvasTitle"));
		height = 900;
		width = 900;
		this.workspace = workspace;
		setup();
	}

	private void setup()
	{
		layers = new TabPane();
		newTab();
		this.setCenter(layers);
	}

	private void newTab()
	{
		Tab newTab = new Tab();
		scrollScreen = new ScrollPane();
		layer = new Pane();
		layer.setPrefHeight(height);
		layer.setPrefWidth(width);
		layer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

		Rectangle rect = new Rectangle();
		rect.setWidth(100);
		rect.setHeight(100);
		rect.setFill(Color.CORAL);
		makeDraggable(rect);
		layer.getChildren().add(rect);

		scrollScreen.setContent(layer);
		setupGrid();
		newTab.setContent(scrollScreen);
		layers.getTabs().add(newTab);
	}

	private void setupGrid()
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
		layer.getChildren().add(gridMarker);
	}

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
				Bounds scrollViewportBounds = scrollScreen.getViewportBounds();

				double settingsWidth = workspace.getPane().getChildrenUnmodifiable().get(0).getBoundsInParent()
						.getWidth();

				double horizontalScrollAmount = scrollScreen.getHvalue() * (width - (scrollViewportBounds.getWidth()));
				double verticalScrollAmount = scrollScreen.getVvalue() * (height - (scrollViewportBounds.getHeight()));

				double nodeWidth = node.getBoundsInParent().getWidth();
				double nodeHeight = node.getBoundsInParent().getHeight();

				double tabHeaderHeight = layers.getTabMaxHeight();

				double newX = event.getSceneX() - settingsWidth + horizontalScrollAmount;
				double newY = event.getSceneY() - tabHeaderHeight + verticalScrollAmount;

				newX = putWithinBounds(newX, 0, width - nodeWidth);
				newY = putWithinBounds(newY, 0, height - nodeHeight);

				node.setTranslateX(((int) newX / TILE_SIZE) * TILE_SIZE);
				node.setTranslateY(((int) newY / TILE_SIZE) * TILE_SIZE);

				if (width > scrollViewportBounds.getMaxX()) {
					scrollScreen.setHvalue(newX / (width - nodeWidth));
				}
				if (height > scrollViewportBounds.getMaxY()) {
					scrollScreen.setVvalue(newY / (height - nodeHeight));
				}

			}

		});
	}

	private double putWithinBounds(double value, double minValue, double maxValue)
	{
		if (value < minValue) {
			return minValue;
		} else if (value > maxValue) {
			return maxValue;
		}
		return value;
	}

}
