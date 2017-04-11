package authoring.components;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * @author Elliott Bolzan
 *
 *         This class serves to display an HTML-formatted file.
 */
public class HTMLDisplay extends Stage {

	/**
	 * @param resources
	 *            the ResourceBundle that contains both the path to the HTML
	 *            page and the title of the view.
	 */
	public HTMLDisplay(String filePath, String title) {
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.load(getClass().getClassLoader().getResource(filePath).toExternalForm());
		Scene scene = new Scene(browser, 800, 600);
		setTitle(title);
		setScene(scene);
	}

}
