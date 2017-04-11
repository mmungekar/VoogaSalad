package authoring.panel.creation.pickers;

import authoring.Workspace;
import authoring.components.ComponentMaker;
import authoring.panel.creation.EntityMaker;
import authoring.panel.creation.editors.EventEditor;
import engine.Event;
import engine.game.EngineController;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;

/**
 * @author Elliott Bolzan
 *
 */
public class EventPicker extends Picker {

	private EntityMaker editor;
	private EngineController engine = new EngineController();
	private ListView<Event> list;
	private Event currentlyEditing;
	private ComponentMaker maker;

	public EventPicker(Workspace workspace, EntityMaker editor) {
		super(workspace, "EventPickerTitle", editor);
		this.editor = editor;
		maker = new ComponentMaker(workspace.getResources());
		update();
	}

	@Override
	public void createContainer() {
		list = new ListView<Event>();
		list.setPlaceholder(new Label(getWorkspace().getResources().getString("EmptyEvents")));
		list.setEditable(false);
		list.prefHeightProperty().bind(heightProperty());
		list.setCellFactory(param -> new ListCell<Event>() {
			@Override
			protected void updateItem(Event event, boolean empty) {
				super.updateItem(event, empty);
				if (empty || event == null || event.getDisplayName() == null) {
					setText(null);
				} else {
					setText(event.getDisplayName());
				}
			}
		});
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				editor.setSelectedEvent(list.getSelectionModel().getSelectedItem());
			}
		});
		setCenter(list);
	}

	@Override
	public void createNew() {
		if (editor.getEntity() != null) {
			currentlyEditing = null;
			showEditor();
		}
	}

	@Override
	public <E> void add(E element) {
		if (currentlyEditing != null) {
			remove(currentlyEditing);
		}
		Event event = (Event) element;
		event.setEntity(editor.getEntity());
		editor.getEntity().getEvents().add(event);
		update();
	}

	@Override
	public <E> void remove(E element) {
		editor.getEntity().getEvents().remove((Event) element);
		update();
	}

	@Override
	public void delete() {
		if (selectionExists(list.getSelectionModel().getSelectedItem())) {
			remove(list.getSelectionModel().getSelectedItem());
			editor.setSelectedEvent(null);
		}
	}

	@Override
	public void edit() {
		if (selectionExists(list.getSelectionModel().getSelectedItem())) {
			currentlyEditing = list.getSelectionModel().getSelectedItem();
			showEditor();
		}
	}

	@Override
	public void update() {
		list.setItems(FXCollections.observableArrayList(editor.getEntity().getEvents()));
	}
	
	@Override
	public void showEditor() {
		EventEditor editor = new EventEditor(getWorkspace(), this, currentlyEditing, engine.getAllEvents());
		maker.display("NewEventTitle", 300, 400, editor, Modality.APPLICATION_MODAL);
	}

}
