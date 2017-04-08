package testers;

import engine.game.Level;
import engine.game.LevelManager;
import engine.game.gameloop.OldGameLoopBackup;
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
		OldGameLoopBackup gameLoop = new OldGameLoopBackup("dummyGameMatthew.txt");
		gameLoop.startTimeline();
	}
}
