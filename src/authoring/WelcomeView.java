package authoring;

import authoring.components.ComponentMaker;
import authoring.views.View;
import javafx.scene.control.Button;

/**
 * @author Elliott Bolzan
 *
 */
public class WelcomeView extends View {
	
	private Workspace workspace;

	/**
	 * 
	 */
	public WelcomeView(Workspace workspace) {
		super("Welcome");
		this.workspace = workspace;
		setup();
	}
	
	private void setup() {
		ComponentMaker maker = new ComponentMaker(workspace.getResources());
		// intro
		// game name
		Button saveButton = maker.makeButton("SaveButtonSettings", e -> workspace.save(), true);
		setBottom(saveButton);
	}

}
