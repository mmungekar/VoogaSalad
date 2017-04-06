package authoring;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
/**
 * @author Mina
 * 
 * The Action Button allows the event handler to be set in the constructor itself
 *
 */

public class ActionButton extends Button{
	
	public ActionButton(String title){
		super(title);
	}
	
	public ActionButton(EventHandler<ActionEvent> eventHandler){
		this.setOnAction(eventHandler);
	}
	
	public ActionButton(String title, EventHandler<ActionEvent> eventHandler){
		this(eventHandler);
		this.setText(title);
		this.setMaxWidth(Double.MAX_VALUE);
	}
}

