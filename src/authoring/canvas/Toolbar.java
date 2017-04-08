package authoring.canvas;

import authoring.components.Thumbnail;
import authoring.views.View;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * @author Elliott Bolzan
 *
 */
public class Toolbar extends View {

	private Thumbnail thumbnail;
	private Label instructions;
	
	/**
	 * @param title
	 */
	public Toolbar() {
		super("");
		setup();
	}
	
	private void setup() {
		ToolBar bar = new ToolBar();
		bar.setMaxHeight(30);
		setMaxHeight(30);
		instructions = new Label("Select an entity to add it to your canvas.");
		thumbnail = new Thumbnail(new SimpleStringProperty("/resources/images/mario.png"), 20, 20);
		HBox box = new HBox(instructions, thumbnail);
		HBox.setHgrow(box, Priority.ALWAYS);
		box.setAlignment(Pos.CENTER);
		bar.getItems().addAll(box);
		setCenter(instructions);
	}
	
	public void setImage(String imagePath) {
		instructions.setText("Control-click to add");
	}

}
