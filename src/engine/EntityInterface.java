package engine;

import java.util.List;

public interface EntityInterface
{
	// external
	void setEvent(EventInterface event);

	// internal
	void update();
	List<Event> getEvents();

	void setYAcceleration(double accel);

	void setXAcceleration(double accel);

	void setYSpeed(double speed);

	void setXSpeed(double speed);

	void setY(double y);

	void setX(double x);
	
	void setHeight(double height);
	
	void setWidth(double width);
	
	String getImagePath();
	double getWidth();
	double getHeight();
	double getX();
	double getY();
}