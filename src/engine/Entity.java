package engine;

import java.util.List;

import engine.game.eventobserver.ObservableEntity;

public interface Entity extends ObservableEntity
{
	// external
	void setEvent(Event event);

	// internal
	List<Event> getEvents();

	void setYAcceleration(double accel);

	void setXAcceleration(double accel);

	void setYSpeed(double speed);

	void setXSpeed(double speed);

	void setY(double y);

	void setX(double x);
}