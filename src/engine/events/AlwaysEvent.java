package engine.events;

import engine.Event;

public class AlwaysEvent extends Event{
	@Override
	public void act(){
		trigger();
	}
}
