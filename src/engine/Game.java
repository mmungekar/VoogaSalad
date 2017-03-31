package engine;

public interface Game {
	//external
	void addLevel(Level level); //needed for authoring
	Level getCurrentLevel(); //needed for gameplay
	Entity getPowerup();
}
