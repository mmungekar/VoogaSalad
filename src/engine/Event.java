package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Event implements EventInterface {

	private List<Action> actions;
	private Map<String, Object> params;

	public Event() {
		actions = new ArrayList<Action>();
		params = new HashMap<String, Object>();
	}

	@Override
	public void addAction(Action action) {
		actions.add(action);
	}

	@Override
	public Map<String, Object> getParams() {
		return params;
	}

	@Override
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	@Override
	public abstract void act();

	protected void trigger() {
		for (Action action : actions)
			action.act();
	}
}
