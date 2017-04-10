package game_data;

import engine.Entity;
import engine.entities.CharacterEntity;

public class EntitySaverTest {

	
	public static void main(String[] args) {
	
	GameSaver gs = new GameSaver();
	
	Entity entity = new CharacterEntity("Guilherme","src/resources/images/mario.png");
	
	
	
	
	entity.setX(100);
	entity.setY(55);

	//C:/Users/Michael8417/workspace/voogasalad_duwaldorf/
	String filepath="data/EntityTesting";
	gs.saveEntity(entity, filepath);
	
	}
	
	
}
