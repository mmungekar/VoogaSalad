package engine.graphics.cameras;

public class ScrollingCamera implements Camera {
	private double positionX;
	private double positionY;
	private double scrollSpeedX;
	private double scrollSpeedY;
	
	public ScrollingCamera(double speedX, double speedY) {
		positionX = 0;
		positionY = 0;
		scrollSpeedX = speedX;
		scrollSpeedY = speedY;
	}

	@Override
	public void update() {
		this.positionX += scrollSpeedX;
		this.positionY += scrollSpeedY;
	}

	@Override
	public void setPosition(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}

	@Override
	public double getX() {
		return positionX;
	}

	@Override
	public double getY() {
		return positionY;
	}
}
