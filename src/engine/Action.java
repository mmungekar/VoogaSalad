package engine;

public abstract class Action extends GameObject implements ActionInterface {

	private Entity entity;
	
	public Action(Entity entity) {
		super("Action");
		this.entity = entity;
	}

	@Override
	public abstract void act();

	public Entity getEntity() {
		return entity;
	}

}
