package game_data;

import java.util.ArrayList;
import java.util.List;

import engine.Entity;
import engine.entities.CharacterEntity;
import engine.game.Level;

public class LevelSaverTest {
	public static void main(String[] args) {
GameSaver gs = new GameSaver("");
	
	Entity entity = new CharacterEntity();
	
	
	
	entity.setName("Guilherme");
	entity.setX(100);
	entity.setY(55);
	entity.setImagePath("src/resources/images/mario.png");
	Entity luigi = new CharacterEntity();
	luigi.setName("Luigi");
	luigi.setX(100);
	luigi.setY(55);
	luigi.setImagePath("src/resources/images/luigi.png");
	//C:/Users/Michael8417/workspace/voogasalad_duwaldorf/
	String filepath="data/EntityTesting";
	
	Level l = new Level();
	l.addEntity(entity);
	l.addEntity(luigi);
	
	List<Level> levels= new ArrayList<Level>();
	levels.add(l);
	gs.saveGame(levels, filepath);
	
	
	
	}
}
