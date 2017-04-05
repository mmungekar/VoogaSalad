package engine;

import java.util.Map;
import java.util.ResourceBundle;

public abstract class GameObject {
	private ResourceBundle resources;
	private Map<String, Object> params;

	public GameObject(String name){
		resources = ResourceBundle.getBundle("resources/" + name);
	}
	public String getDisplayName() {
		return resources.getString(getClass().getSimpleName().toString());
	}

	public String getDisplayDescription() {
		return resources.getString(getClass().getSimpleName() + "Description");
	}
	
	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}
