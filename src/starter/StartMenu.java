package starter;

import java.util.ResourceBundle;

import authoring.AuthoringEnvironment;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import player.PlayerMenu;

public class StartMenu extends BorderPane {

	private Stage stage;
	private ResourceBundle resources = ResourceBundle.getBundle("resources/StarterUI");
	private String stylesheetPath = resources.getString("StylesheetPath");
	
	protected StartMenu(Stage primaryStage) {
		this.stage = primaryStage;
		this.buildStage();
	}
	
	private void buildStage() {
		stage.setTitle(resources.getString("Title"));
		stage.setMinWidth(300);
		stage.setMinHeight(300);
		stage.setOnCloseRequest(e -> System.exit(0));
		
		stage.setScene(this.buildScene());
		this.setCenter(this.buildView());
		stage.show();
	}
	
	private Scene buildScene() {
		Scene scene = new Scene(this, 400, 400);
		scene.getStylesheets().add(stylesheetPath);
		return scene;
	}
	
	private VBox buildView() {
		Button newButton = makeButton("NewButton", e -> this.newGame());
		Button chooseButton = makeButton("ChooseButton", e -> this.chooseGame());
		Button editButton = makeButton("EditButton", e -> this.editGame());
		Button playButton = makeButton("PlayButton", e -> this.playGame());
		Button exitButton = makeButton("ExitButton", e -> System.exit(0));
		
		HBox editOrPlayButtons = new HBox(0);
		editOrPlayButtons.getChildren().addAll(editButton, playButton);
		
		VBox chooseGameButtons = new VBox(0);
		chooseGameButtons.getChildren().addAll(chooseButton, editOrPlayButtons);
		
		VBox buttons = new VBox(50);
		buttons.setAlignment(Pos.CENTER);
		buttons.maxWidthProperty().bind(stage.widthProperty().multiply(0.8));
		buttons.getChildren().addAll(newButton, chooseGameButtons, exitButton);
		
		return buttons;
	}
	
	private void newGame() {
		new AuthoringEnvironment();
	}
	
	private void chooseGame() {
		//TODO: make file chooser
	}
	
	private void editGame() {
		//TODO: make it so that it only works if game chosen
		new AuthoringEnvironment();
	}
	
	private void playGame() {
		//TODO: make it so that it only works if game chosen
		new PlayerMenu();
	}
	
	private Button makeButton(String label, EventHandler<ActionEvent> handler) {
		Button button = new Button(resources.getString(label));
		button.setOnAction(handler);
		HBox.setHgrow(button, Priority.ALWAYS);
		button.setMaxWidth(Double.MAX_VALUE);
		return button;
	}
}
