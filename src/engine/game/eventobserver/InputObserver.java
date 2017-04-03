package engine.game.eventobserver;

import java.util.ArrayList;
import java.util.List;

import engine.Entity;
import engine.Event;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

/**
 * Part of the Observable Design Pattern for detecting and responding to Events.
 * 
 * @author Matthew Barbano
 *
 */
public class InputObserver extends EventObserver{
	
	public InputObserver(){
		super();
	}
	
	
	/*
	 * Argument for casting here:
	 * 1. Casting here
	 * 2. Have separate lists of InputEvent, CollisionEvent, TimerEvent in Entity
	 * We would like Entity to be closed (in case add new types of Events), so rather have casting here.
	 */
	public void updateObservables(){
		for(ObservableEntity observable : observables){
			for(Event event : observable.getEvents()){
				if(event instanceof InputEvent){
					if((InputEvent)event.getTriggerKeyCode() == pressedKey){    //get pressedKey from another method in this class!
						event.act();
					}
				}
				else{
					throw new Exception("Casting problem");
				}
			}
		}
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
