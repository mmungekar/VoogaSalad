package engine.events;

import java.util.Map;

import engine.Event;

public class AlwaysEvent extends Event{
	@Override
	public boolean act(){
		return true;
	}

}
