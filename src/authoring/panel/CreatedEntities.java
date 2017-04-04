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
	
	private ObservableList<EntityWrapper> entities;
	private EntityWrapper selected;

	/**
	 * 
	 */
	public CreatedEntities() {
		entities = FXCollections.observableArrayList(new ArrayList<EntityWrapper>());
		entities.add(new EntityWrapper(new ConcreteEntity("Mario", "resources/images/mario.png")));
		entities.add(new EntityWrapper(new ConcreteEntity("Luigi", "resources/images/luigi.png")));
	}
	
	public ObservableList<EntityWrapper> getEntities() {
		return entities;
	}
	
	public EntityWrapper getSelectedEntity() {
		return selected;
	}
	
	public void setSelectedEntity(EntityWrapper entity) {
		this.selected = entity;
	}

}
