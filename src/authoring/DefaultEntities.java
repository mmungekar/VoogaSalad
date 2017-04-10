package authoring;

import java.util.ArrayList;
import java.util.List;

import engine.Entity;
import engine.entities.CharacterEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Elliott Bolzan
 *
 */
public class DefaultEntities {
	
	private ObservableList<Entity> entities;
	private Entity selected;

	/**
	 * 
	 */
	public DefaultEntities() {
		setEntities(new ArrayList<Entity>());
		entities.add(new CharacterEntity());
	}
	
	public ObservableList<Entity> getEntities() {
		return entities;
	}
	
	public void setEntities(List<Entity> entities) {
		this.entities = FXCollections.observableArrayList(entities);
	}
	
	public Entity getSelectedEntity() {
		return selected;
	}
	
	public void setSelectedEntity(Entity entity) {
		this.selected = entity;
	}

}
