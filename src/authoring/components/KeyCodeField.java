/**
 * 
 */
package authoring.components;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Elliott Bolzan
 *
 */
public class KeyCodeField extends TextField {

	private boolean editing;
	private KeyCode keyCode;

	/**
	 * 
	 */
	public KeyCodeField() {
		focusedProperty().addListener((arg0, oldValue, newValue) -> {
			editing = newValue;
		});
		setOnKeyPressed(e -> keyPressed(e));
	}

	/**
	 * @param text
	 */
	public KeyCodeField(String text) {
		super(text);
	}
	
	public KeyCode getKeyCode() {
		return keyCode;
	}

	private void keyPressed(KeyEvent e) {
		if (editing) {
			e.consume();
			keyCode = e.getCode();
			setText(e.getText());
			setFocused(false);
		}
	}

}
