package engine;

import java.util.List;
import java.util.ResourceBundle;

public abstract class GameObject {
	private ResourceBundle resources;
	private List<Parameter> params;

	public GameObject(String name) {
		resources = ResourceBundle.getBundle("resources/" + name);
	}

	public String getDisplayName() {
		return resources.getString(getClass().getSimpleName().toString());
	}

	public String getDisplayDescription() {
		return resources.getString(getClass().getSimpleName() + "Description");
	}

	public List<Parameter> getParams() {
		return params;
	}

	public void setParams(List<Parameter> params) {
		this.params = params;
	}

	public Object getParam(String name) {
		for (Parameter param : params) {
			if (param.getName().equals(name))
				return param;
		}
		return null;
	}
}
