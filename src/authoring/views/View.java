package authoring.views;

import javafx.scene.layout.BorderPane;

/**
 * @author Elliott Bolzan
 *
 *         This class represents a View in the program. Specific kinds of views
 *         can extend this class, like CollapsibleView. Every View must have a
 *         title.
 * 
 *         This code's purpose: providing a base GUI class for all views in the
 *         project.
 * 
 *         Why I think this code is well designed: this code is as minimal as it
 *         should be. A View, in our project, is characterized by the fact it
 *         has a title and by the fact it can have subcomponents. This class
 *         provides this capability without providing any extra bells and
 *         whistles.
 *
 */
public abstract class View extends BorderPane {

	private String title;

	/**
	 * Create a View and initialize its label.
	 */
	public View(String title) {
		this.title = title;
	}

	/**
	 * @return a String representing the View's title.
	 */
	public String getTitle() {
		return title;
	}
	
}
