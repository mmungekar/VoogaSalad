package authoring.panel.creation.pickers;

import authoring.Workspace;
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

/**
 * @author Elliott Bolzan
 *
 */
public class EventPicker extends Picker {

	private EntityMaker editor;
	private EngineController engine = new EngineController();
	private ListView<Event> list;
	
	public EventPicker(Workspace workspace, EntityMaker editor) {
		super(workspace, "EventPickerTitle");
		this.editor = editor;
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
		new EventEditor(getWorkspace(), this, "NewEventTitle", engine.getAllEvents());		
	}

	@Override
	public <E> void add(E element) {
		editor.getEntityWrapper().getEntity().getEvents().add((Event) element);
		update();		
	}

	@Override
	public <E> void remove(E element) {
		if (selectionExists()) {
			editor.getEntityWrapper().getEntity().getEvents().remove((Event) element);
			update();
		}
	}

	@Override
	public void delete() {
		remove(list.getSelectionModel().getSelectedItem());
    	editor.setSelectedEvent(null);		
	}
	
	@Override
	public void update() {
		list.setItems(FXCollections.observableArrayList(editor.getEntityWrapper().getEntity().getEvents()));
	}

	@Override
	public void edit() {
		if (selectionExists()) {
			
		}
	}
	
}
