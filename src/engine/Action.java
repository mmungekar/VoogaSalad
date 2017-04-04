package engine;

import java.util.Map;
import java.util.ResourceBundle;

public abstract class Action implements ActionInterface {

	private Map<String, Object> params;
	private ResourceBundle resources;

	public Action() {
		resources = ResourceBundle.getBundle("Actions");
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

	@Override
	public String getDisplayName() {
		return resources.getString(getClass().toString());
	}

	@Override
	public String getDisplayDescription() {
		return resources.getString(getClass() + "Description");
	}
}
