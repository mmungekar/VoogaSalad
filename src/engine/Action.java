package engine;

import java.util.Map;

public abstract class Action implements ActionInterface {

	private Map<String, Object> params;
	
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

}
