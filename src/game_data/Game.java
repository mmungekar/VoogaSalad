/**
 * 
 */
package game_data;

import java.util.List;

import engine.Entity;
import engine.game.Level;

/**
 * @author Elliott Bolzan
 *
 */
public class Game {
	
	private List<Level> levels;
	private List<Entity> defaults;
	private String songPath;

	/**
	 * 
	 */
	public Game() {
		// TODO Auto-generated constructor stub
	}

	public List<Level> getLevels() {
		return levels;
	}

	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}

	public List<Entity> getDefaults() {
		return defaults;
	}

	public void setDefaults(List<Entity> defaults) {
		this.defaults = defaults;
	}

	public String getSongPath() {
		return songPath;
	}

	public void setSongPath(String songPath) {
		this.songPath = songPath;
	}

}
