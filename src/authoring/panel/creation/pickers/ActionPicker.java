package authoring.panel.creation.pickers;

import authoring.Workspace;
import authoring.panel.creation.EntityMaker;
import authoring.panel.creation.editors.ActionEditor;
import engine.Action;
import engine.game.EngineController;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * @author Elliott Bolzan
 *
 */
public class ActionPicker extends Picker {

	private EntityMaker editor;
	private EngineController engine = new EngineController();
	private ListView<Action> list;

	public ActionPicker(Workspace workspace, EntityMaker editor) {
		super(workspace, "ActionPickerTitle");
		this.editor = editor;
	}
	
	@Override
	public void createContainer() {
		list = new ListView<Action>();
		list.setPlaceholder(new Label(getWorkspace().getResources().getString("EmptyActions")));
		list.setEditable(false);
		list.prefHeightProperty().bind(heightProperty());
		list.setCellFactory(param -> new ListCell<Action>() {
		    @Override
		    protected void updateItem(Action action, boolean empty) {
		        super.updateItem(action, empty);
		        if (empty || action == null || action.getDisplayName() == null) {
		            setText(null);
		        } else {
		            setText(action.getDisplayName());
		        }
		    }
		});            
		setCenter(list);
	}

	@Override
	public void createNew() {
		if (editor.getSelectedEvent() != null) {
			new ActionEditor(getWorkspace(), this, "NewActionTitle", engine.getAllActions());
		}
		else {
			editor.showMessage(getWorkspace().getResources().getString("NoEventSelected"));
		}		
	}

	@Override
	public <E> void add(E element) {
		editor.getSelectedEvent().getActions().add((Action) element);
		update();
	}

	@Override
	public <E> void remove(E element) {
		editor.getSelectedEvent().getActions().remove((Action) element);
		update();	
	}

	@Override
	public void delete() {
		remove(list.getSelectionModel().getSelectedItem());		
	}

	@Override
	public void edit() {
		
	}

	@Override
	public void update() {
		if (editor.getSelectedEvent() != null)
			list.setItems(FXCollections.observableArrayList(editor.getSelectedEvent().getActions()));
		else
			list.setItems(FXCollections.emptyObservableList());
	}

}
