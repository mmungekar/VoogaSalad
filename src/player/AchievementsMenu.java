package player;

import javafx.stage.Stage;

public class AchievementsMenu extends AbstractMenu{

	public AchievementsMenu(Stage stage){
		backButton().setOnAction(e -> back(stage));
	}
}
