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
	 * PROERTIES ARE SET BUT NOT ENTITIES
	 */
	public EntityWrapper(Entity entity) {
		this.entity = entity;
		name = new SimpleStringProperty(entity.getName());
		imagePath = new SimpleStringProperty(entity.getImagePath());
	}
	
	public SimpleStringProperty getName() {
		return name;
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
		//entity.setName(name.get());
	}

	public SimpleStringProperty getImagePath() {
		return imagePath;
	}

	public void setImagePath(SimpleStringProperty imagePath) {
		this.imagePath = imagePath;
		//entity.setImagePath(imagePath.get());
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

}
