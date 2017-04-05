package engine;

import java.util.Map;

public interface EventInterface {
	//external
	void addAction(Action action);
	Map<String, Object> getParams(); // will use reflection
	void setParams(Map<String, Object> params); // will use reflection
	String getDisplayName();
	String getDisplayDescription();
	
	//internal
	boolean act();// check if event is triggered
	void trigger();// tell all actions to act
}
