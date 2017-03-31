package authoring.canvas;

import authoring.Workspace;
import authoring.views.View;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Canvas extends View {
	
	// I recommend having a tabbed structure in here, to account for multiple levels.

	private Workspace workspace;
	
	public Canvas(Workspace workspace) {
		super(workspace.getResources().getString("CanvasTitle"));
		this.workspace = workspace;
		setup();
	}
	
	private void setup() {
		setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	}

}
