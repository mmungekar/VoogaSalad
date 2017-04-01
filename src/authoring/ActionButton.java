package authoring;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
/**
 * @author Mina
 * 
 * The Action Button allows the event handler to be set in the constructor itself
 *
 */

public class ActionButton extends Button{
	
	public ActionButton(EventHandler<ActionEvent> eventHandler){
		this.setOnAction(eventHandler);
	}
	
	public ActionButton(String title, EventHandler<ActionEvent> eventHandler){
		this(eventHandler);
		this.setText(title);
	}
}

