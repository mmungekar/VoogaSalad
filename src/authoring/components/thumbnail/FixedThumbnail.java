package authoring.components.thumbnail;

import javafx.scene.image.Image;

/**
 * @author Elliott Bolzan
 *
 */
public class FixedThumbnail extends Thumbnail {

	private String path;
	
	/**
	 * 
	 */
	public FixedThumbnail(String path, int width, int height) {
		super(width, height);
		setImage(path);
	}
	
	public void setImage(String path) {
		this.path = path;
		getImage().set(new Image(path));
	}
	
	public String getImagePath() {
		return path;
	}

}
