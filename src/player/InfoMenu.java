package player;

import javafx.stage.Stage;

public class InfoMenu extends AbstractMenu{
	private Stage stage;

	public InfoMenu(Stage stage){
		super();
		setupView();
		this.stage = stage;
	}
	
	private void setupView(){
		backButton().setOnAction(e -> back(stage));
		getRoot().setBottom(backButton());
	}

}
