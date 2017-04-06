/**
 * 
 */
package authoring.panel.creation.pickers;

import java.util.ArrayList;
import java.util.List;

import authoring.Workspace;
import authoring.panel.creation.editors.EntityEditor;
import authoring.panel.creation.editors.EventEditor;
import authoring.utils.ComponentMaker;
import authoring.views.View;
import engine.Event;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Elliott Bolzan
 *
 */
public class EventPicker extends View {

	private Workspace workspace;
	private EntityEditor editor;
	private ComponentMaker componentMaker;
	private List<Event> events;
	private ListView<Event> list;
	
	/**
	 * @param title
	 */
	public EventPicker(Workspace workspace, EntityEditor editor) {
		super("Event Picker");
		this.workspace = workspace;
		this.editor = editor;
		setup();
	}
	
	public List<Event> getEvents() {
		return events;
	}

	private void setup() {
		events = new ArrayList<Event>();
		componentMaker = new ComponentMaker(workspace.getResources());
		setPadding(new Insets(15));
		createTypeBox();
		createList();
		createButtons();
	}
	
	private void createTypeBox() {
		Label label = new Label(workspace.getResources().getString("EventPickerTitle"));
		label.setPadding(new Insets(5));
		HBox box = new HBox();
		box.getChildren().add(label);
		box.setAlignment(Pos.CENTER);
		setTop(box);
	}
	
	private void createList() {
		list = new ListView<Event>();
		list.setPlaceholder(new Label(workspace.getResources().getString("EmptyEvents")));
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
		setCenter(list);
	}
	
	private void createButtons() {
		VBox buttonBox = new VBox();
		Button newButton = componentMaker.makeButton("NewEvent", e -> createNewEvent(), true);
		Button editButton = componentMaker.makeButton("Edit", e -> editEvent(list), true);
		Button deleteButton = componentMaker.makeButton("Delete", e -> deleteEvent(list), true);
		HBox modificationButtons = new HBox(editButton, deleteButton);
		buttonBox.getChildren().addAll(newButton, modificationButtons);
		setBottom(buttonBox);
	}
	
	public void addEvent(Event event) {
		events.add(event);
		updateList();
	}
	
	public void removeEvent(Event event) {
		events.remove(event);
		updateList();
	}
	
	public void updateList() {
		list.setItems(FXCollections.observableArrayList(events));
	}
	
	private void createNewEvent() {
		new EventEditor(workspace, this);
	}
	
	private void editEvent(ListView<Event> list) {
		
	}
	
	private void deleteEvent(ListView<Event> list) {
		removeEvent(list.getSelectionModel().getSelectedItem());
	}
	
}
