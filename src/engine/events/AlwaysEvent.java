package engine.events;

import engine.Event;

public class AlwaysEvent extends Event{
	
	@Override
	public boolean act() {
		return true;
	}

}
