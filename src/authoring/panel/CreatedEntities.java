/**
 * 
 */
package authoring.panel;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Elliott Bolzan
 *
 */
public class CreatedEntities {
	
	private ObservableList<Entity> entities;
	private Entity selected;

	/**
	 * 
	 */
	public CreatedEntities() {
		entities = FXCollections.observableArrayList(new ArrayList<Entity>());
		entities.add(new Entity("Mario", "resources/images/mario.png"));
		entities.add(new Entity("Luigi", "resources/images/luigi.png"));
	}
	
	public ObservableList<Entity> getEntities() {
		return entities;
	}
	
	public Entity getSelectedEntity() {
		return selected;
	}
	
	public void setSelectedEntity(Entity entity) {
		this.selected = entity;
	}

}
