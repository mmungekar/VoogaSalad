package engine.events;

import java.util.HashMap;
import java.util.Map;

import engine.Event;

public class InputEvent extends Event {

	/**
	 * need to initialize me with a string, not a keycode (can't instantiate
	 * KeyCode object)
	 */
	public InputEvent() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Key", "");
		setParams(params);
	}

	@Override
	public void act() {
		if (game.getNewInput() && game.getInput().equals(getParams().get("Key")))
			trigger();
	}
}
