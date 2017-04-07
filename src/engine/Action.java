package engine;

public abstract class Action extends GameObject implements ActionInterface {

	private Entity entity;

	public Action() {
		super("Action");
	}

	@Override
	public abstract void act();

	public Entity getEntity() {
		return entity;
	}

}
