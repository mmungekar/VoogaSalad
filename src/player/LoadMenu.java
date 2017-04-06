package player;

import javafx.stage.Stage;

public class LoadMenu extends AbstractMenu{
	
	public LoadMenu(Stage stage){
		backButton().setOnAction(e -> back(stage));
	}

}
