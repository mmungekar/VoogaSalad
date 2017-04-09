package authoring.canvas;

import authoring.views.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * @author Elliott Bolzan
 *
 */
public class HelpBar extends View {
	
	/**
	 * @param title
	 */
	public HelpBar() {
		super("");
		setup();
	}
	
	private void setup() {
		ToolBar bar = new ToolBar();
		Label instructions = new Label("Control-click to add the selected entity to the canvas.");
		instructions.setPadding(new Insets(4));
		HBox box = new HBox(instructions);
		HBox.setHgrow(box, Priority.ALWAYS);
		box.setAlignment(Pos.CENTER);
		bar.getItems().addAll(box);
		setCenter(instructions);
	}

}
