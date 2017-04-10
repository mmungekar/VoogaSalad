package engine.events;

import engine.Event;
import engine.Parameter;
import engine.game.eventobserver.InputObservable;
import javafx.scene.input.KeyCode;

public class KeyPressEvent extends Event {
	
	public KeyPressEvent() {
		addParam(new Parameter("Key", KeyCode.class, KeyCode.UNDEFINED));
	}

	@Override
	public boolean act() {
		return (((InputObservable) getEventObservable()).isKeyPressToProcess()
				&& getParam("Key").equals(((InputObservable) getEventObservable()).getLastPressedKey()));
	}
}