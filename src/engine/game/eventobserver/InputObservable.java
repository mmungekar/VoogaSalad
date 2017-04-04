package engine.game.eventobserver;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

/**
 * Part of the Observable Design Pattern for detecting and responding to Events.
 * 
 * @author Matthew Barbano
 *
 */
public class InputObservable extends EventObservable{
	private KeyCode lastPressedKey;
	private MouseButton lastPressedMouseButton;
	private Point2D lastPressedCoordinates;
	private boolean inputToProcess;
	
	public InputObservable(){
		super();
		lastPressedKey = null;
		lastPressedMouseButton = null;
		lastPressedCoordinates = null;
		inputToProcess = false;
	}

	//For Nikita to call in InputEvent's act()
	public boolean getInputToProcess(){
		return inputToProcess;
	}
	
	//For Nikita to call in InputEvent's act() - for key input
	public KeyCode getLastPressedKey(){
		return lastPressedKey;
	}
	
	//For Nikita to call in InputEvent's act() - for mouse input
	public Point2D getLastPressedCoordinates(){
		return lastPressedCoordinates;
	}
	
	//For Nikita to call in InputEvent's act() - for mouse input - see JavaFX's MouseButton documentation for how to use
	public MouseButton getLastPressedMouseButton(){
		return lastPressedMouseButton;
	}
	
	//Matthew calls this from game loop (at end, AFTER update Entities/act Events)
	public void setInputToProcess(boolean state){
		inputToProcess = state;
	}
	
	public void setupInputListeners(Scene gameScene){
		gameScene.setOnKeyPressed(event -> { lastPressedKey = event.getCode(); inputToProcess = true;});
		gameScene.setOnMouseClicked(event -> { lastPressedMouseButton = event.getButton(); lastPressedCoordinates = new Point2D(event.getX(), event.getY()); inputToProcess = true;});
	}
	
	public void updateObservers(){
		//Intentionally left blank.
	}
}
