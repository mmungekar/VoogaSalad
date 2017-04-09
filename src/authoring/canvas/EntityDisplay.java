package authoring.canvas;

import engine.Entity;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class EntityDisplay extends VBox
{
	private Entity entity;
	private ImageView image;
	private int tileSize;

	public EntityDisplay(Entity entity, int gridSize, double x, double y)
	{
		// TODO: Instead of this.entity = entity, need this.entity =
		// entity.clone();
		this.entity = entity;
		this.image = new ImageView(new Image(entity.getImagePath()));
		this.tileSize = gridSize;

		setup(gridSize, x, y);
	}

	private void setup(int gridSize, double x, double y) {
		this.setPrefHeight(10);
		this.setPrefWidth(10);
		Color borderColor = new Color(0, 0, 0, 0.2);
		this.setBorder(new Border(new BorderStroke(borderColor, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		image.fitWidthProperty().bind(this.minWidthProperty());
		image.fitHeightProperty().bind(this.minHeightProperty());

		entity.xProperty().bind(this.translateXProperty());
		entity.yProperty().bind(this.translateYProperty());
		entity.widthProperty().bind(image.fitWidthProperty());
		entity.heightProperty().bind(image.fitHeightProperty());

		this.getChildren().add(image);
		this.setMinWidth(getTiledCoordinate(image.getBoundsInLocal().getWidth()));
		this.setMinHeight(getTiledCoordinate(image.getBoundsInLocal().getHeight()));

		DragResizer.makeResizable(this, gridSize);
	}

	public Entity getEntity()
	{
		return entity;
	}

	// TODO: This method is repeated in DragResizer
	private double getTiledCoordinate(double coordinate) {
		double gridCoordinate = ((int) coordinate / tileSize) * tileSize;
		if (coordinate % tileSize > tileSize / 2) {
			return gridCoordinate + tileSize;
		}
		return gridCoordinate;
	}

}