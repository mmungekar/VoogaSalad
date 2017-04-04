package engine;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleDoubleProperty;

public abstract class Entity implements EntityInterface {

	private SimpleDoubleProperty x, y, width, height;
	private double xSpeed, ySpeed, xAcceleration, yAcceleration;
	private List<Event> events;
	String name, imagePath;

	public Entity(String name, String imagePath) {
		events = new ArrayList<Event>();
		this.name = name;
		this.imagePath = imagePath;
	}

	/**
	 * make sure to check state and set new state before acting.
	 */
	@Override
	public abstract void update();

	public double getX() {
		return x.doubleValue();
	}

	public void setX(double x) {
		this.x.set(x);
	}

	public double getY() {
		return y.doubleValue();
	}

	public void setY(double y) {
		this.y.set(y);
	}

	public double getWidth() {
		return width.doubleValue();
	}

	public void setWidth(double width) {
		this.width.set(width);
	}

	public double getHeight() {
		return height.doubleValue();
	}

	public void setHeight(double height) {
		this.height.set(height);
	}

	public double getXSpeed() {
		return xSpeed;
	}

	public void setXSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	public double getYSpeed() {
		return ySpeed;
	}

	public void setYSpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}

	public double getXAcceleration() {
		return xAcceleration;
	}

	public void setXAcceleration(double xAcceleration) {
		this.xAcceleration = xAcceleration;
	}

	public double getYAcceleration() {
		return yAcceleration;
	}

	public void setYAcceleration(double yAcceleration) {
		this.yAcceleration = yAcceleration;
	}
	
	public double getMinX(){
		return getX()-getWidth()/2;
	}
	
	public double getMaxX(){
		return getX()+getWidth()/2;
	}
	
	public double getMinY(){
		return getY()-getHeight()/2;
	}
	
	public double getMaxY(){
		return getY()+getHeight()/2;
	}
	
	public List<Event> getEvents(){
		return events;
	}
}