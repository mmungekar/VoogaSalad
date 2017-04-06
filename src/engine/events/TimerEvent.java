package engine.events;

import engine.Event;
import engine.Parameter;
import engine.game.eventobserver.TimerObservable;

public class TimerEvent extends Event {

	public TimerEvent() {
		addParam(new Parameter("Time", Integer.class, 0));
	}

	@Override
	public boolean act() {
		int time = ((TimerObservable)getEventObservable()).getTimeInMilliseconds();
		return time >= (Integer)getParam("Time");
	}
}
