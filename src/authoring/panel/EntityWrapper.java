/**
 * 
 */
package authoring.panel;

import engine.Entity;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Elliott Bolzan
 *
 */
public class EntityWrapper {

	private SimpleStringProperty name;
	private SimpleStringProperty imagePath;
	private Entity entity;

	/**
	 * 
	 */
	public EntityWrapper(Entity entity) {
		this.entity = entity;
		name = new SimpleStringProperty(entity.getName());
		imagePath = new SimpleStringProperty(entity.getImagePath());
		name.addListener((observable, oldValue, newValue) -> this.entity.setName(newValue));
		imagePath.addListener((observable, oldValue, newValue) -> this.entity.setImagePath(newValue));
	}

	public SimpleStringProperty getName() {
		return name;
	}

	public SimpleStringProperty getImagePath() {
		return imagePath;
	}
	
	public Entity getEntity() {
		return entity;
	}

}
