package player;

import java.util.ResourceBundle;

import authoring.utils.ComponentMaker;
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
	
	private void setupStage(){
		stage = new Stage();
		stage.setTitle(resources.getString("MainTitle"));
		stage.setMinWidth(600);
		stage.setMinHeight(300);
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
		ComponentMaker componentMaker = new ComponentMaker(resources);
		VBox menu = new VBox(8);
		menu.getChildren().addAll(componentMaker.makeButton("StartButton", e -> new Player(), true),
				componentMaker.makeButton("AchievementsButton", e -> new AchievementsMenu().display(), true),
				componentMaker.makeButton("OptionsButton", e -> new OptionsMenu().display(), true),
				componentMaker.makeButton("InfoButton", e -> new InfoMenu().display(), true),
				componentMaker.makeButton("ExitButton", e -> System.exit(0), true));
		
		root.setCenter(menu);
		menu.setAlignment(Pos.CENTER);
		menu.maxWidthProperty().bind(stage.widthProperty().multiply(0.3));
	}
}
