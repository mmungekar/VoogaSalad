package starter;

import java.net.URL;

import javax.swing.ImageIcon;

import com.apple.eawt.Application;

/**
 * @author Elliott Bolzan
 *
 */
public class OSXIconLoader {

	/**
	 * 
	 */
	public OSXIconLoader(URL path) {
		Application.getApplication().setDockIconImage(new ImageIcon(path).getImage());
	}

}
