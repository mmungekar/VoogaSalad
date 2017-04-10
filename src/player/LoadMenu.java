package player;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoadMenu extends AbstractMenu{
		
	public LoadMenu(Stage stage){
		super(stage);
		
		//TODO: make a better menu for launching the game
		Button playButton = this.getFactory().makeButton("StartButton", e -> new Player(), false);
		this.getRoot().setCenter(playButton);
	}

}
