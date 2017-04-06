package authoring.panel.creation.pickers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.Workspace;
import authoring.panel.creation.editors.EntityEditor;
import authoring.utils.ClassFinder;
import authoring.views.View;
import engine.Entity;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

/**
 * @author Elliott Bolzan
 *
 */
public class EntityPicker extends View {

	private static final String ENTITY_PACKAGE = "engine";

	private Workspace workspace;
	private EntityEditor editor;
	private Map<String, Class<?>> entityMap;

	/**
	 * @param title
	 */
	public EntityPicker(Workspace workspace, EntityEditor editor) {
		super("Entity Picker");
		this.workspace = workspace;
		this.editor = editor;
		setup();
	}

	private void setup() {
		entityMap = new HashMap<String, Class<?>>();
		setPadding(new Insets(15));
		Label label = new Label(workspace.getResources().getString("EntityPickerTitle"));
		label.setPadding(new Insets(5));
		HBox box = new HBox();
		box.getChildren().add(label);
		box.setAlignment(Pos.CENTER);
		setTop(box);
		ListProperty<String> listProperty = new SimpleListProperty<>();
		ListView<String> list = new ListView<String>();
		list.itemsProperty().bind(listProperty);
		list.setPlaceholder(new Label(workspace.getResources().getString("EmptyEntities")));
		list.setEditable(false);
		list.prefHeightProperty().bind(heightProperty());
		list.setOnMouseClicked(e -> mouseClicked(list));
		listProperty.set(getEntities());
		setCenter(list);
	}

	private ObservableList<String> getEntities() {
		ClassFinder finder = new ClassFinder();
		List<Class<?>> entities = finder.find(ENTITY_PACKAGE);
		for (Class<?> entity : entities) {
			entityMap.put(entity.getSimpleName(), entity);
		}
		List<String> names = new ArrayList<String>(entityMap.keySet());
		Collections.sort(names);
		return FXCollections.observableArrayList(names);
	}

	private void mouseClicked(ListView<String> list) {
		String selected = list.getSelectionModel().getSelectedItem();
		if (selected != null) {
			try {
				Class<?> c = entityMap.get(selected);
				Entity entity = (Entity) c.getConstructor().newInstance();
				//editor.setEntity(entity);
				// Fix this once Entity stabilizes.
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}

}
