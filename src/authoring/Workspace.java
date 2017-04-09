/**
 * 
 */
package authoring;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoring.canvas.LayerEditor;
import authoring.canvas.LevelEditor;
import authoring.components.ComponentMaker;
import authoring.panel.Panel;
import authoring.utils.EntityWrapper;
import authoring.views.View;
import javafx.geometry.Insets;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;

/**
 * @author Elliott Bolzan Modified by Mina Mungekar, Jimmy Shackford
 *
 */
public class Workspace extends View {

	private ResourceBundle resources;
	private LevelEditor levelEditor;
	private Panel panel;
	private SplitPane pane;

	/**
	 * 
	 */
	public Workspace(ResourceBundle resources) {
		super("Workspace");
		this.resources = resources;
		setup();
	}

	/**
	 * Initializes the Workspace's components.
	 */
	private void setup() {
		pane = new SplitPane();
		panel = new Panel(this, 0);
		levelEditor = new LevelEditor(this);
		pane.getItems().addAll(panel, levelEditor);
		pane.setDividerPositions(Double.parseDouble(resources.getString("DividerPosition")));
		setPadding(new Insets(Integer.parseInt(resources.getString("WorkSpaceInsets"))));
		setCenter(pane);
	}

	public ResourceBundle getResources() {
		return resources;
	}

	public SplitPane getPane() {
		return pane;
	}

	public EntityWrapper getSelectedEntity() {
		return panel.getEntityDisplay().getSelectedEntity();
	}

	public void showMessage(String message) {
		ComponentMaker componentMaker = new ComponentMaker(resources);
		componentMaker.makeAlert(AlertType.ERROR, "ErrorTitle", "ErrorHeader", message).showAndWait();
	}

	public List getEntities() {
		// return canvas's entities (i.e. canvas.getLevel())
		return new ArrayList<>();
	}
	
	public void setNewLayer(String newLayer){
		panel.updateLayerPanel(newLayer);
	}
	
	public void addLayer(){
	levelEditor.getCurrentLevel().makeNewTab();
	}

	public void selectLayer(int arg2) {
		levelEditor.getCurrentLevel().selectNewLayer(arg2);
	}

}