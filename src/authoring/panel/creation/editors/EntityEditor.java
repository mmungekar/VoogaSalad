package authoring.panel.creation.editors;

import java.util.List;

import authoring.Workspace;
import authoring.utils.EntityWrapper;
import engine.Entity;
import engine.Parameter;
import engine.game.EngineController;
import javafx.collections.FXCollections;

/**
 * @author Elliott Bolzan
 *
 */
public class EntityEditor extends Editor {

	private EngineController engine;
	private Entity entity;

	/**
	 * @param workspace
	 * @param titleProperty
	 * @param elements
	 * @param object
	 */
	public EntityEditor(Workspace workspace, EntityWrapper entityWrapper, List<String> elements) {
		super(workspace, elements, entityWrapper.getEntity(), false);
		engine = new EngineController();
		update(entityWrapper.getEntity());
	}

	@Override
	public void selected(String string) {
		entity = engine.createEntity(string);
		update(entity);
	}

	@Override
	public void save(List<Parameter> data) {
	}

}
