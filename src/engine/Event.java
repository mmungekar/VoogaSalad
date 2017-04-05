package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import engine.game.eventobserver.EventObservable;

public abstract class Event extends GameObject implements EventInterface {
	
	private List<Action> actions;
	private EventObservable observable;

	public Event(){
		super("Event");
		actions = new ArrayList<Action>();
	}

	@Override
	public void addAction(Action action) {
		actions.add(action);
	}

	@Override
	public abstract void act();

	protected void trigger() {
		for (Action action : actions)
			action.act();
	}
	
	public void addEventObservable(EventObservable observable){
		this.observable = observable;
	}
	
	protected EventObservable getEventObservable(){
		return observable;
	}
}