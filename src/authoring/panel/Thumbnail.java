/**
 * 
 */
package authoring.panel;

import authoring.views.View;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * @author Elliott Bolzan
 *
 */
public class Thumbnail extends View {

	private String imagePath;
	private ImageView imageView = new ImageView();

	public Thumbnail(String path, int width, int height) {
		super("Thumbmnail");
		setImage(path);
		DropShadow shadow = new DropShadow(20, Color.LIGHTGRAY);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		imageView.setPreserveRatio(true);
		imageView.setEffect(shadow);
		setCenter(imageView);
		setMinSize(0, 0);
	}
	
	public void setImage(String path) {
		this.imagePath = path;
		imageView.setImage(new Image(imagePath));
	}
	
	public String getImagepath() {
		return imagePath;
	}

}
