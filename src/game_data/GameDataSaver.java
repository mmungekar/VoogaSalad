package game_data;

import java.util.List;
import engine.Level;

public class GameDataSaver{
	public GameDataSaver(){

	}

	public void saveGame(List<Level> levels){
		List<String> allXML = new List<String>();
		for(Level curr: levels){
			XStream filesaver = new XStream();
			String toXML = xstream.toXML(curr);
		}
		File addr = new File("fileaddresshere");
		
	}
}