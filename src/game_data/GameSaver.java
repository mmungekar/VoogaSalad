package game_data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import engine.game.Level;

public class GameSaver {

	
	String folderpath;
	
	public GameSaver(String inputfolderpath){
		
		folderpath=inputfolderpath;
		
		
	}
	
	
	/*MenuItem cmItem2 = new MenuItem("Save Image");
    cmItem2.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Image");
            System.out.println(pic.getId());
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(pic.getImage(),
                        null), "png", file);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
);
*/
	
	public void saveGame(List<Level> levels,String filepath){
		
		try{
		FileOutputStream fileOut = new FileOutputStream(filepath);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(levels);
		out.close();
		fileOut.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}
	

	
	
	
	
	
	
	
	
}
