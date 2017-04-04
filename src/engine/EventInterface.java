package engine;

import java.util.Map;

public interface EventInterface {
	//external
	void addAction(Action action);
	Map<String, Object> getParams(); // will use reflection
	void setParams(Map<String, Object> params); // will use reflection
	
	//internal
	void act();// tell its action to act
}
