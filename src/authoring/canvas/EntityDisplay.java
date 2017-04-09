package authoring.canvas;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class EntityDisplay extends VBox
{
	private ImageView image;

	public EntityDisplay(ImageView image, double x, double y)
	{
		this.image = image;
		setup(x, y);
	}

	private void setup(double x, double y)
	{
		this.setPrefHeight(10);
		this.setPrefWidth(10);
		this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
		image.fitWidthProperty().bind(this.minWidthProperty());
		image.fitHeightProperty().bind(this.minHeightProperty());
		this.getChildren().add(image);
		// this.getChildren().add(border);
		DragResizer.makeResizable(this);
		// DragMover.makeMoveable(this);
	}

}