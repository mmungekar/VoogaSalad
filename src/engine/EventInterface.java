package engine;

import java.util.List;

public interface EventInterface {
	//external
	void addAction(Action action);
	List<Parameter> getParams(); // will use reflection
	void setParams(List<Parameter> params); // will use reflection
	String getDisplayName();
	String getDisplayDescription();
	
	//internal
	boolean act();// check if event is triggered
	void trigger();// tell all actions to act
}
