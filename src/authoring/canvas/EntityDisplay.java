package authoring.canvas;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class EntityDisplay extends VBox {
	private ImageView image;
	private int tileSize;

	public EntityDisplay(ImageView image, int gridSize, double x, double y) {
		this.image = image;
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

		this.getChildren().add(image);
		this.setMinWidth(getTiledCoordinate(image.getBoundsInLocal().getWidth()));
		this.setMinHeight(getTiledCoordinate(image.getBoundsInLocal().getHeight()));

		DragResizer.makeResizable(this, gridSize);
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