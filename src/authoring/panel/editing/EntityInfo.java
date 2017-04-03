/**
 * 
 */
package authoring.panel.editing;

import java.io.File;

import authoring.Workspace;
import authoring.utils.Factory;
import authoring.views.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	private Factory factory;
	private TextField nameField;

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
	
		factory = new Factory(workspace.getResources());
		
		VBox box = new VBox(20);
		
		HBox nameBox = new HBox(8);
		Label nameLabel = new Label(workspace.getResources().getString("TitlePrompt"));
		// bind to entity name
		nameField = new TextField();
		nameField.setPrefWidth(100);
		nameBox.getChildren().addAll(nameLabel, nameField);
		nameBox.setAlignment(Pos.CENTER);
		
		VBox imageBox = new VBox(8);
		Image image = new Image("resources/images/mario.png");
		// bind image to entity's path
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(50);
		imageView.setFitWidth(50);
		imageView.setId("thumbnail");
		Button pickButton = factory.makeButton("PickButton", e -> pickImage(), true);
		imageBox.getChildren().addAll(imageView, pickButton);
		imageBox.setAlignment(Pos.CENTER);
		
		HBox buttonBar = new HBox();
		Button saveButton = factory.makeButton("SaveButtonEditor", e -> save(), true);
		Button cancelButton = factory.makeButton("CancelButton", e -> cancel(), true);
		buttonBar.getChildren().addAll(saveButton, cancelButton);
		
		box.getChildren().addAll(nameBox, imageBox, buttonBar);
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(20));
		
		setCenter(box);

	}
	
	private void pickImage() {
		FileChooser imageChooser = factory.makeFileChooser(System.getProperty("user.home"), "Image", "*.png", "*.jpg", "*.gif");
		File file = imageChooser.showOpenDialog(getScene().getWindow());
		if (file != null) {
		}
	}
	
	private void save() {
		System.out.println("save");
	}
	
	private void cancel() {
		editor.dismiss();
	}

}
