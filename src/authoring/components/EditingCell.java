package authoring.components;

import engine.Parameter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Elliott Bolzan
 *
 */
public class EditingCell extends TableCell<Parameter, Object> {

	private TextField textField;
	private KeyCodeField keyCodeField;

	@Override
	public void startEdit() {
		if (!isEmpty()) {
			super.startEdit();
			if (getItem() == null || getItem() instanceof KeyCode) {
				createKeyCodeField();
				setText(null);
				setGraphic(keyCodeField);
				keyCodeField.selectAll();
				keyCodeField.requestFocus();
			} else {
				createTextField();
				setText(null);
				setGraphic(textField);
				textField.selectAll();
			}
		}
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();
		setText(getItem() == null ? "" : getItem().toString());
		setGraphic(null);
	}

	@Override
	public void updateItem(Object item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			if (isEditing()) {
				if (getItem() instanceof KeyCode) {
					if (keyCodeField != null) {
						keyCodeField.setText(getKeyCode().toString());
					}
					setText(null);
					setGraphic(keyCodeField);
				} else {
					if (textField != null) {
						textField.setText(getString());
					}
					setText(null);
					setGraphic(textField);
				}
			} else {
				setText(getString());
				setGraphic(null);
			}
		}
	}

	private void createTextField() {
		textField = new TextField(getString());
		textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
		textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					commitEdit(textField.getText());
				}
			}
		});
		textField.setOnKeyPressed(e -> keyPressed(e));
	}

	private void createKeyCodeField() {
		keyCodeField = new KeyCodeField();
		keyCodeField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
		keyCodeField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					commitEdit(keyCodeField.getKeyCode());
				}
			}
		});
	}
	
	private void keyPressed(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			textField.parentProperty().get().requestFocus();
		}
	}

	private String getString() {
		return getItem() == null ? "" : getItem().toString();
	}

	private KeyCode getKeyCode() {
		return getItem() == null ? null : (KeyCode) getItem();
	}

}
