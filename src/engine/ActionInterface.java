package engine;

import java.util.List;

public interface ActionInterface {
	//external
	List<Parameter> getParams(); // will use reflection
	void setParams(List<Parameter> params); // will use reflection
	String getDisplayName();
	String getDisplayDescription();
	
	//internal
	void act(); // carry out the action triggered by the event. Will need a reference to game
}
