package engine.game.eventobserver;

import java.util.ArrayList;
import java.util.List;

import engine.Entity;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

/**
 * Part of the Observable Design Pattern for detecting and responding to Events.
 * 
 * @author Matthew Barbano
 *
 */
public class InputObserver extends EventObserver {
	
	public InputObserver(){
		super();
	}
	
	public void updateObservables(){
		
	}
	/*
	public void setupInputListeners(Scene scene){
		scene.setOnKeyPressed(event -> manageKeyInput(event.getCode()));
		scene.setOnMouseClicked(event -> manageMouseInput(event.getX(), event.getY()));
	}
	
	private void manageKeyInput(KeyCode pressedKey){   //TODO IN PROGRESS
		//Reduces code duplication - note: need a new event for every KeyCode
		/*
		if(key observable is listening to is pressed){
			update that observable
		}
		*/
	/*
		for(ObservableEntity observable : observables){
			 if(observable.getEvent().getTriggerKeyCode() == pressedKey){
				 observable.update();
			 }
		}
	}
	
	private void manageMouseInput(int mouseX, int mouseY){
		 
	}
	*/
}
