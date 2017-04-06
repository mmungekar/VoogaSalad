package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javafx.beans.property.SimpleDoubleProperty;

public abstract class Entity extends GameObject implements EntityInterface {
	
	public static final Integer ACCELERATION = -10;
	private SimpleDoubleProperty x, y, width, height, zIndex;
	private double xSpeed, ySpeed, xAcceleration, yAcceleration;
	private List<Event> events;
	private String name, imagePath;

	public Entity(String name, String imagePath) {
		super("Entity");
		x = new SimpleDoubleProperty();
		y = new SimpleDoubleProperty();
		width = new SimpleDoubleProperty();
		height = new SimpleDoubleProperty();
		zIndex = new SimpleDoubleProperty();
		events = new ArrayList<Event>();
		this.name = name;
		this.imagePath = imagePath;
		//TODO: initialize these values to something other than 0,0,0,0
		this.x = new SimpleDoubleProperty();
		this.y = new SimpleDoubleProperty();
		this.width = new SimpleDoubleProperty();
		this.height = new SimpleDoubleProperty();
		addParam(new Parameter("Time Step", Double.class, 0));
	} 

	/**
	 * TODO: make sure to check state and set new state before acting.
	 */
	@Override
	public void update(){
		List<Event> actions = events.stream().filter(s -> s.act()).collect(Collectors.toList());
		actions.forEach(event -> event.trigger());
	}
	
	@Override
	public void addEvent(Event event){
		this.events.add(event);
	}

	@Override
	public double getX() {
		return this.x.get();
	}

	@Override
	public void setX(double x) {
		this.x.set(x);;
	}

	@Override
	public double getY() {
		return this.y.get();
	}

	@Override
	public void setY(double y) {
		this.y.set(y);;
	}

	@Override
	public double getWidth() {
		return this.width.get();
	}

	@Override
	public void setWidth(double width) {
		this.width.set(width);
	}

	@Override
	public double getHeight() {
		return this.height.get();
	}

	@Override
	public void setHeight(double height) {
		this.height.set(height);;
	}

	public double getXSpeed() {
		return xSpeed;
	}

	@Override
	public void setXSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	public double getYSpeed() {
		return ySpeed;
	}

	@Override
	public void setYSpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}

	public double getXAcceleration() {
		return xAcceleration;
	}

	@Override
	public void setXAcceleration(double xAcceleration) {
		this.xAcceleration = xAcceleration;
	}

	public double getYAcceleration() {
		return yAcceleration;
	}

	@Override
	public void setYAcceleration(double yAcceleration) {
		this.yAcceleration = yAcceleration;
	}

	public double getMinX() {
		return getX() - getWidth() / 2;
	}

	public double getMaxX() {
		return getX() + getWidth() / 2;
	}

	public double getMinY() {
		return getY() - getHeight() / 2;
	}

	public double getMaxY() {
		return getY() + getHeight() / 2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public List<Event> getEvents() {
		return events;
	}
	
}