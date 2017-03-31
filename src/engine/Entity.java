package engine;

import engine.game.eventobserver.ObservableEntity;

public interface Entity extends ObservableEntity{
	//external
	void setEvent(Event event);
	
	//internal
	List<Event> getEvents();

	void setXSpeed(int i);

	void setY(int i);

	void setX(int i);
}	