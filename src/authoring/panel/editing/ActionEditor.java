/**
 * 
 */
package authoring.panel.editing;

import authoring.Workspace;
import authoring.views.View;

/**
 * @author Elliott Bolzan
 *
 */
public class ActionEditor extends View {

	private Workspace workspace;
	private ActionPicker actionPicker;

	/**
	 * @param title
	 */
	public ActionEditor(Workspace workspace, ActionPicker actionPicker) {
		super("");
		this.workspace = workspace;
		this.actionPicker = actionPicker;
		setup();
	}
	
	private void setup() {
		new TableEditor(workspace, "NewActionTitle", null, e -> save());
	}
	
	private void save() {
		
	}

}
