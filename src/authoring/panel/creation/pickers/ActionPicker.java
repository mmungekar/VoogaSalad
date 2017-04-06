/**
 * 
 */
package authoring.panel.creation.pickers;

import java.util.ArrayList;
import java.util.List;

import authoring.Workspace;
import authoring.panel.creation.editors.ActionEditor;
import authoring.panel.creation.editors.EntityEditor;
import authoring.utils.ComponentMaker;
import authoring.views.View;
import engine.Action;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Elliott Bolzan
 *
 */
public class ActionPicker extends View {

	private Workspace workspace;
	private EntityEditor editor;
	private ComponentMaker componentMaker;
	private List<Action> actions;
	
	/**
	 * @param title
	 */
	public ActionPicker(Workspace workspace, EntityEditor editor) {
		super("Action Picker");
		this.workspace = workspace;
		this.editor = editor;
		setup();
	}
	
	public List<Action> getActions() {
		return actions;
	}
	
	public void addAction(Action action) {
		actions.add(action);
	}
	
	private void setup() {
		actions = new ArrayList<Action>();
		componentMaker = new ComponentMaker(workspace.getResources());
		setPadding(new Insets(15));
		Label label = new Label(workspace.getResources().getString("ActionPickerTitle"));
		label.setPadding(new Insets(5));
		HBox box = new HBox();
		box.getChildren().add(label);
		box.setAlignment(Pos.CENTER);
		setTop(box);
		ListProperty<String> listProperty = new SimpleListProperty<>();
		ListView<String> list = new ListView<String>();
		list.itemsProperty().bind(listProperty);
		list.setPlaceholder(new Label(workspace.getResources().getString("EmptyActions")));
		list.setEditable(false);
		list.prefHeightProperty().bind(heightProperty());
		setCenter(list);
		VBox buttonBox = new VBox();
		Button newButton = componentMaker.makeButton("NewAction", e -> newAction(), true);
		Button editButton = componentMaker.makeButton("Edit", e -> edit(list), true);
		Button deleteButton = componentMaker.makeButton("Delete", e -> delete(list), true);
		HBox modificationButtons = new HBox(editButton, deleteButton);
		buttonBox.getChildren().addAll(newButton, modificationButtons);
		setBottom(buttonBox);
	}
	
	private void newAction() {
		new ActionEditor(workspace, this);
	}
	
	private void edit(ListView<String> list) {
		
	}
	
	private void delete(ListView<String> list) {
		
	}

}
