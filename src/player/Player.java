package player;

import java.util.ResourceBundle;

import authoring.components.ComponentMaker;
import engine.game.gameloop.GameLoop;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * 
 * Environment for playing the game
 *
 */
public class Player extends BorderPane {
	
	private Stage stage;
	private Scene scene;
	private GameLoop gameLoop;
	private ResourceBundle resources = ResourceBundle.getBundle("resources/Player");
	private String stylesheetPath = resources.getString("StylesheetPath");
	
	public Player() {
		this.stage = new Stage();
		this.buildStage();
		
		//TODO: pass in the file name of the game/level you want to play
		gameLoop = new GameLoop(scene, "FIXME");
		this.setCenter(gameLoop.getGameView());
		
		this.setTop(this.buildControlBar());
		
		gameLoop.startTimeline();
	}
	
	private void buildStage() {
		stage.setTitle(resources.getString("PlayerTitle"));
		stage.setMinWidth(600);
		stage.setMinHeight(600);
		
		stage.setScene(this.buildScene());
		stage.show();
	}
	
	private Scene buildScene() {
		scene = new Scene(this, 600, 600);
		scene.getStylesheets().add(stylesheetPath);
		return scene;
	}
	
	private HBox buildControlBar() {
		HBox controls = new HBox();
		
		ImageView playImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(resources.getString("PlayPausePath"))));
		Button playButton = new Button("", playImage);
		playButton.setPrefSize(50, 50);
		
		Separator s = new Separator();
		HBox.setHgrow(s, Priority.ALWAYS);
		
		Label scorebar = gameLoop.getGameScorebar();
		
		controls.getChildren().addAll(playButton, s, scorebar);
		
		return controls;
	}
}
