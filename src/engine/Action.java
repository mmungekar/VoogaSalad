package engine;

public abstract class Action extends GameObject implements ActionInterface {

	public Action() {
		super("Acion");
	}

	@Override
	public abstract void act();
}
