package authoring.canvas;

import authoring.Workspace;
import authoring.views.View;
import javafx.event.EventHandler;
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
		ScrollPane scrollScreen = new ScrollPane();
		layer = new Pane();
		layer.setPrefHeight(height);
		layer.setPrefWidth(width);
		layer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		Rectangle add = new Rectangle();
		add.setHeight(100);
		add.setWidth(100);
		add.setFill(Color.RED);
		layer.getChildren().add(add);
		scrollScreen.setContent(layer);
//		setupGrid();
		this.setCenter(scrollScreen);
	}


}
