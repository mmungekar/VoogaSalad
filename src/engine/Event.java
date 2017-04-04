package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import engine.game.eventobserver.EventObservable;

public abstract class Event implements EventInterface {
	
	private List<Action> actions;
	private Map<String, Object> params;
	private ResourceBundle resources;
	private EventObservable observable;

	public Event(){
		actions = new ArrayList<Action>();
		params = new HashMap<String, Object>();
		resources = ResourceBundle.getBundle("Events");
	}
	
	@Override
	public void addAction(Action action){
		actions.add(action);
	}

	@Override
	public Map<String, Object> getParams(){
		return params;
	}

	@Override
	public void setParams(Map<String, Object> params){
		this.params = params;
	}

	@Override
	public abstract void act();
	
	protected void trigger(){
		for (Action action: actions)
			action.act();
	}
	@Override
	public String getDisplayName() {
		return resources.getString(getClass().toString());
	}

	@Override
	public String getDisplayDescription() {
		return resources.getString(getClass() + "Description");
	}
	
	public void addEventObservable(EventObservable observable){
		this.observable = observable;
	}
}
