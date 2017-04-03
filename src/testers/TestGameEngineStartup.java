package testers;

import engine.game.GameLoop;
import engine.game.Level;
import engine.game.LevelManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Note to self: Use case 1.
 * @author Matthew Barbano
 *
 */
public class TestGameEngineStartup extends Application{
	public static void main(String[] args){
		launch(args);
	}
	public void start(Stage stage) throws Exception{
		//Editing phase (the following code is in the GAE)
		LevelManager levelManager = new LevelManager();
		Level level1 = new Level();
		//Add Entities here
		levelManager.addLevel(level1);
		levelManager.saveAllLevels();
		//----------------------PRETEND ARE IN ENTIRELY SEPARATE MAIN METHODS---------------
		//Playing phase (the following code is in the Game Player)
		GameLoop gameLoop = new GameLoop("dummyGameMatthew.txt");
		gameLoop.startTimeline();
	}
}
