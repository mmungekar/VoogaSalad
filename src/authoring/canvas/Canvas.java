package authoring.canvas;

import authoring.Workspace;
import authoring.views.View;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Canvas extends View
{

	// I recommend having a tabbed structure in here, to account for multiple
	// levels.

	private Workspace workspace;
	private final int TILE_SIZE = 25;

	private ScrollPane scrollScreen;
	private Pane layer;
	private int width;
	private int height;

	public Canvas(Workspace workspace)
	{
		super(workspace.getResources().getString("CanvasTitle"));
		height = 1000;
		width = 1000;
		this.workspace = workspace;
		setup();
	}

	private void setup()
	{
		setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
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
		this.setCenter(scrollScreen);
	}

	private void setupGrid()
	{
		for (int i = 0; i < width / TILE_SIZE; i++) {
			for (int j = 0; j < height / TILE_SIZE; j++) {
				Circle gridMarker = new Circle();
				gridMarker.setCenterX(i * TILE_SIZE);
				gridMarker.setCenterY(j * TILE_SIZE);
				gridMarker.setRadius(1);
				gridMarker.setFill(Color.GREY);
				layer.getChildren().add(gridMarker);
			}
		}
	}

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
				double horizontalScrollAmount = scrollScreen.getHvalue() * (width - scrollViewportBounds.getMaxX());
				double verticalScrollAmount = scrollScreen.getVvalue() * (height - scrollViewportBounds.getMaxY());
				node.setTranslateX(
						((int) (event.getSceneX() - settingsWidth + horizontalScrollAmount) / TILE_SIZE) * TILE_SIZE);
				node.setTranslateY(((int) (event.getSceneY() + verticalScrollAmount) / TILE_SIZE) * TILE_SIZE);
			}

		});
	}

}
