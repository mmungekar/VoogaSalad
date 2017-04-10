package engine.game.gameloop;

import engine.game.LevelManager;
import engine.graphics.GraphicsEngine;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public interface StepStrategy {
	/**
	 * Called from Screen's constructor. Safest to put preliminary setup code rather than
	 * subclass's constructor since do not know when it is instantiated, but more predictable
	 * when Screen's constructor is called. 
	 */
	public void setup(ObservableBundle newObservableBundle, LevelManager levelManager, Scene gameScene, Screen screen, GraphicsEngine graphicsEngine);
	
	/**
	 * Called from Screen's step() method.
	 */
	public void step();
}