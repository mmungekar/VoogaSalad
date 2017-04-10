/**
 * 
 */
package authoring.components;

import authoring.Workspace;
import authoring.views.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * @author Elliott Bolzan
 *
 */
public class LabeledField extends View {

	private TextField field;

	/**
	 * 
	 */
	public LabeledField(Workspace workspace, String labelProperty, String text, boolean editable) {
		super("");
		Label label = new Label(workspace.getResources().getString(labelProperty));
		label.setMinWidth(Region.USE_PREF_SIZE);
		label.setPadding(new Insets(5));
		field = new TextField(text);
		field.setEditable(editable);
		HBox box = new HBox(label, field);
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(2));
		HBox.setHgrow(field, Priority.ALWAYS);
		setCenter(box);
	}

	public void setText(String text) {
		field.setText(text);
	}

	public String getText() {
		return field.getText();
	}

}
