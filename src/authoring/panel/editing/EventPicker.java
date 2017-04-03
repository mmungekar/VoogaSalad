/**
 * 
 */
package authoring.panel.editing;

import authoring.Workspace;
import authoring.utils.Factory;
import authoring.views.View;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
	private Factory factory;
	
	/**
	 * @param title
	 */
	public EventPicker(Workspace workspace, EntityEditor editor) {
		super("Event Picker");
		this.workspace = workspace;
		this.editor = editor;
		setup();
	}
	
	private void setup() {
		factory = new Factory(workspace.getResources());
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
		buttonBox.getChildren().addAll(factory.makeButton("NewEvent", e -> newEvent(), true), factory.makeButton("DeleteSelected", e -> deleteEvent(list), true));
		setBottom(buttonBox);
	}
	
	private void newEvent() {
		
	}
	
	private void deleteEvent(ListView<String> list) {
		
	}

}
