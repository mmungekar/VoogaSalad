package player;

import java.util.ResourceBundle;

import authoring.utils.Factory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayerMenu {
	private ResourceBundle resources = ResourceBundle.getBundle("resources/Player");
	private BorderPane root;
	private Stage stage;
	
	public PlayerMenu(){
		setupStage();
		setupScene();
	}
	
	public PlayerMenu(Stage stage){
		this.stage = stage;
		stage.setScene(createScene());
		setupScene();
	}
	
	private void setupStage(){
		stage = new Stage();
		stage.setTitle(resources.getString("MainTitle"));
		stage.setScene(createScene());
		stage.show();
	}
	
	private Scene createScene(){
		root = new BorderPane();
		Scene scene = new Scene(root, 1000, 600);
		scene.getStylesheets().add(resources.getString("StylesheetPath"));
		return scene;	
	}
	
	private void setupScene(){
		Factory factory = new Factory(resources);
		VBox menu = new VBox(8);
		menu.getChildren().addAll(factory.makeButton("StartButton", e -> new Player(), true),
				factory.makeButton("AchievementsButton", e -> stage.setScene(new AchievementsMenu().display()), true),
				factory.makeButton("OptionsButton", e -> stage.setScene(new OptionsMenu().display()), true),
				factory.makeButton("InfoButton", e -> stage.setScene(new InfoMenu(stage).display()), true),
				factory.makeButton("ExitButton", e -> stage.close(), true));
		
		root.setCenter(menu);
		menu.setAlignment(Pos.CENTER);
		menu.maxWidthProperty().bind(stage.widthProperty().multiply(0.3));
	}
}
