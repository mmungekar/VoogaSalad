package authoring;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoring.canvas.LevelEditor;
import authoring.components.ComponentMaker;
import authoring.panel.Panel;
import authoring.utils.EntityWrapper;
import authoring.views.View;
import game_data.Game;
import game_data.GameData;
import javafx.geometry.Insets;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SplitPane;

/**
 * @author Elliott Bolzan Modified by Mina Mungekar, Jimmy Shackford
 *
 */
public class Workspace extends View {

	private ResourceBundle resources;
	private LevelEditor levelEditor;
	private Panel panel;
	private SplitPane pane;
	private Game game;
	private GameData data;

	/**
	 * 
	 */
	public Workspace(ResourceBundle resources, String path) {
		super("Workspace");
		this.resources = resources;
		setup();
		game = new Game();
		if (!path.equals("")) {
			load(path);
		}
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
	
	private void load(String path) {
		game = data.loadGame(path);
		// load defaults entities
		// load levels into canvas
		// load settings: game name, music
	}
	
	public void save() {
		// things will have been updating Game already
		// get folder path
		// use game name
		String path = "";
		data.saveGame(game, path);
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

	public void setNewLayer(String newLayer) {
		panel.updateLayerPanel(newLayer);
	}

	public void addLayer() {
		levelEditor.getCurrentLevel().makeNewTab();
	}

	public void selectLayer(int arg2) {
		levelEditor.getCurrentLevel().selectNewLayer(arg2);
	}
	
}