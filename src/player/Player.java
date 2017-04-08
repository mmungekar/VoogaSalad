package player;

import java.util.ResourceBundle;

import engine.game.GameLoop;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
}
