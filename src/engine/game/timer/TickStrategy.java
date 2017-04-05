package engine.game.timer;

public interface TickStrategy {
	public int tick(int milliseconds);
	public boolean timeIsUp(int milliseconds);
}
