/**
 * 
 */
package authoring;

import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Elliott Bolzan
 *
 */
public class AuthoringEnvironment {

	private ResourceBundle resources = ResourceBundle.getBundle("resources/AuthoringEnvironment");
	
	/**
	 * 
	 */
	public AuthoringEnvironment() {
		setupStage();
	}
	
	private void setupStage() {
		Stage stage = new Stage();
		stage.setTitle(resources.getString("Title"));
		stage.setMinWidth(600);
		stage.setMinHeight(300);
		stage.setScene(createScene());
		stage.show();
	}
	
	private Scene createScene() {
		Scene scene = new Scene(new Workspace(resources), 1000, 600);
		scene.getStylesheets().add(resources.getString("StylesheetPath"));
		return scene;
	}

}
