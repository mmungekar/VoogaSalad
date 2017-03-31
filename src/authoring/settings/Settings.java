package authoring.settings;

import authoring.Workspace;
import authoring.utils.Direction;
import authoring.views.CollapsibleView;

public class Settings extends CollapsibleView {

	private Workspace workspace;
	
	public Settings(Workspace workspace, int index) {
		super(workspace, workspace.getPane(), workspace.getResources().getString("SettingsTitle"), index, Direction.LEFT, true);
		this.workspace = workspace;
	}

}
