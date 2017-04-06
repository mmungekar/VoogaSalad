/**
 * 
 */
package authoring.panel.editing;

import java.util.Map;

import authoring.Workspace;
import engine.Action;
import engine.game.EngineController;

/**
 * @author Elliott Bolzan
 *
 */
public class ActionEditor extends Editor {

	private Workspace workspace;
	private ActionPicker actionPicker;
	private TableEditor editor;
	private EngineController engine;
	private Action action;

	/**
	 * @param title
	 */
	public ActionEditor(Workspace workspace, ActionPicker actionPicker) {
		this.workspace = workspace;
		this.actionPicker = actionPicker;
		setup();
	}
	
	private void setup() {
		engine = new EngineController();
		editor = new TableEditor(workspace, this, "NewActionTitle", engine.getAllActions());
	}
	
	@Override
	public void selected(String string) {
		action = engine.createAction(string);
		editor.update(action.getParams());
	}

	@Override
	public void save(Map<String, Object> data) {
		action.setParams(data);
	}

}
