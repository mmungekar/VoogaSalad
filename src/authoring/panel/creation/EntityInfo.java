/**
 * 
 */
package authoring.panel.creation;

import java.io.File;

import authoring.Workspace;
import authoring.panel.creation.editors.EntityEditor;
import authoring.utils.ComponentMaker;
import authoring.utils.Thumbnail;
import authoring.views.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * @author Elliott Bolzan
 *
 */
public class EntityInfo extends View {

	private Workspace workspace;
	private EntityEditor editor;
	private ComponentMaker componentMaker;
	private TextField nameField;
	private Thumbnail thumbnail;

	/**
	 * @param title
	 */
	public EntityInfo(Workspace workspace, EntityEditor editor) {
		super("Info");
		this.workspace = workspace;
		this.editor = editor;
		setup();
	}

	private void setup() {

		componentMaker = new ComponentMaker(workspace.getResources());

		VBox box = new VBox(20);

		HBox nameBox = new HBox(8);
		Label nameLabel = new Label(workspace.getResources().getString("TitlePrompt"));
		nameField = new TextField();
		nameField.setPrefWidth(100);
		nameField.setText(editor.getEntity().getName().get());
		nameBox.getChildren().addAll(nameLabel, nameField);
		nameBox.setAlignment(Pos.CENTER);

		VBox imageBox = new VBox(20);
		thumbnail = new Thumbnail(editor.getEntity().getImagePath(), 50, 50);
		Button pickButton = componentMaker.makeButton("PickButton", e -> pickImage(), true);
		imageBox.getChildren().addAll(thumbnail, pickButton);
		imageBox.setAlignment(Pos.CENTER);

		HBox buttonBar = new HBox();
		Button saveButton = componentMaker.makeButton("SaveButtonEditor", e -> editor.save(), true);
		Button cancelButton = componentMaker.makeButton("CancelButton", e -> editor.dismiss(), true);
		buttonBar.getChildren().addAll(saveButton, cancelButton);

		box.getChildren().addAll(nameBox, new Separator(), imageBox, new Separator(), buttonBar);
		box.setPadding(new Insets(20));

		setCenter(box);

	}

	private void pickImage() {
		FileChooser imageChooser = componentMaker.makeFileChooser(System.getProperty("user.home"), "Images", "*.png", "*.jpg",
				"*.gif");
		File file = imageChooser.showOpenDialog(getScene().getWindow());
		if (file != null) {
			thumbnail.setImage(file.toURI().toString());
		}
	}

	public String getName() {
		return nameField.getText();
	}

	public String getImagePath() {
		return thumbnail.getImagePath();
	}

}
