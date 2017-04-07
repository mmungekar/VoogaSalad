package engine.graphics.cameras;

public interface Camera {
	void update();
	void setPosition(double positionX, double positionY);
	double getX();
	double getY();
}
