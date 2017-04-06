package authoring.canvas;

import javafx.scene.Node;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class EntityDisplay extends Pane
{
	private Node image;

	public EntityDisplay(Node image)
	{
		this.image = image;
		setup();
	}

	private void setup()
	{
		this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(0))));
		this.getChildren().add(image);
		DragResizer.makeResizable(this);
	}

}