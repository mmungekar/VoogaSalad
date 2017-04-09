package engine.events;

import engine.Event;
import engine.Parameter;
import engine.game.eventobserver.InputObservable;
import javafx.scene.input.KeyCode;

public class InputEvent extends Event {

	/**
	 * need to initialize me with a string, not a keycode (can't instantiate
	 * KeyCode object)
	 */
	public InputEvent(KeyCode keyToListenFor) {
		addParam(new Parameter("Key", KeyCode.class, keyToListenFor));
	}

	@Override	
	public boolean act(){
		if (((InputObservable)getEventObservable()).getInputToProcess()){
			if (getParam("Key").equals(((InputObservable) getEventObservable()).getLastPressedKey()))
				return true;
		}
		return false;
		//NOTES TO NIKITA (from Matthew):
		/*
		 * To see if there is new input (keyboard or mouse), call inputObservable.getInputToProcess()"
		 * To see if the input matches the input that i react to:
		 * 	- For keyboard: inputObservable.getLastPressedKey();
		 *  - For mouse coordinates: inputObserable.getLastPressedCoordinates();
		 *  - For mouse button: inputObservable.getLastPressedMouseButton(); (see JavaFX's MouseButton documentation)
		 *  Also I am assuming you will take care of getting the targetKey without me because that is set by the Authoring Environment,
		 *  not the Game Loop.
		 */
	}
}