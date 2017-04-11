package game_data;
import java.util.*;
public class LevelPathSaverTest {

	
	
	public static void main(String[] args) {
		List<String> a = new ArrayList<String>();
		a.add("a");
		a.add("b");
		a.add("c");
		a.add("d");
		
		String filepath = "C:/Users/Michael8417/workspace/voogasalad_duwaldorf/data";
		
		LevelPathSaver lps = new LevelPathSaver(a,filepath);
		
		lps.saveLevelPaths();
		
	}
	
}
