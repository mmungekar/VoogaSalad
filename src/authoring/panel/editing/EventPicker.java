/**
 * 
 */
package authoring.panel.editing;

import java.util.ArrayList;
import java.util.List;

import authoring.Workspace;
import authoring.utils.ComponentMaker;
import authoring.views.View;
import engine.Event;
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
public class EventPicker extends View {

	private Workspace workspace;
	private EntityEditor editor;
	private ComponentMaker componentMaker;
	private List<Event> events;
	
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
	
	public void addEvent(Event event) {
		events.add(event);
	}

	private void setup() {
		events = new ArrayList<Event>();
		componentMaker = new ComponentMaker(workspace.getResources());
		setPadding(new Insets(15));
		Label label = new Label(workspace.getResources().getString("EventPickerTitle"));
		label.setPadding(new Insets(5));
		HBox box = new HBox();
		box.getChildren().add(label);
		box.setAlignment(Pos.CENTER);
		setTop(box);
		ListProperty<String> listProperty = new SimpleListProperty<>();
		ListView<String> list = new ListView<String>();
		list.itemsProperty().bind(listProperty);
		list.setPlaceholder(new Label(workspace.getResources().getString("EmptyEvents")));
		list.setEditable(false);
		list.prefHeightProperty().bind(heightProperty());
		setCenter(list);
		VBox buttonBox = new VBox();
		Button newButton = componentMaker.makeButton("NewEvent", e -> newEvent(), true);
		Button editButton = componentMaker.makeButton("Edit", e -> editEvent(list), true);
		Button deleteButton = componentMaker.makeButton("Delete", e -> deleteEvent(list), true);
		HBox modificationButtons = new HBox(editButton, deleteButton);
		buttonBox.getChildren().addAll(newButton, modificationButtons);
		setBottom(buttonBox);
	}
	
	private void newEvent() {
		new EventEditor(workspace, this);
	}
	
	private void editEvent(ListView<String> list) {
		
	}
	
	private void deleteEvent(ListView<String> list) {
		
	}
	
}
