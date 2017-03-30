package engine;

import java.util.List;

public interface Entity {
	//external
	void setEvent(Event event);
	
	//internal
	List<Event> getEvents();

	void setXSpeed(int i);

	void setY(int i);

	void setX(int i);
}	
