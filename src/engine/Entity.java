package engine;

public interface Entity {
	//external
	void setEvent(Event event);
	
	//internal
	void checkEvents(); // will call act on each event it has, if the event is taking place
}	
