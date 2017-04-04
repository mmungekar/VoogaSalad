package engine.game.eventobserver;

import java.util.ArrayList;
import java.util.List;

import engine.Entity;
import engine.Event;
import engine.InputEvent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Part of the Observable Design Pattern for detecting and responding to Events.
 * 
 * @author Matthew Barbano
 *
 */
public class InputObservable extends EventObservable{
	
	//private List<Event> toAct;   //TODO will probably be refactored to superclass
	//private KeyCode lastPressedKey;
	
	
	public InputObservable(){
		super();
		//toAct = new ArrayList<>();
		//lastPressedKey = null;
	}
	
	

	
	/*
	public void updateObservables(){
		// if lastPressedKey != game.getInput():
			//
		for(Entity observable : observables){
			for(Event event : observable.getEvents()){
				if(event instanceof InputEvent){
					if(((InputEvent) event).getTriggerKeyCode() == lastPressedKey){
						toAct.add(event);
					}
				}
			}
		}
		
		for(Event event : toAct){
			event.act();
		}
	}
	
	public void setupInputListeners(Scene scene){
		scene.setOnKeyPressed(event -> { lastPressedKey = event.getCode(); updateObservables(); newInput = True;});
		
		//scene.setOnMouseClicked(event -> manageMouseInput(event.getX(), event.getY()));   //TODO Mouse input is more complicated!
	}
	*/
}
