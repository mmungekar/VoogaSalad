package player;

import javafx.stage.Stage;

public class OptionsMenu extends AbstractMenu{
	
	public OptionsMenu(Stage stage){
		backButton().setOnAction(e -> back(stage));
	}



}
