package engine.entities;

import engine.Entity;
import engine.Parameter;

public class CharacterEntity extends Entity {
	private int lives; // TODO Nikita, I added this field temporarily since I don't fully understand how your 'GameObject' and 'Parameter' classes work
										//Please fix this as you see fit. - Matthew

	public CharacterEntity() {
		this(null, null);
		lives = 5;
	}

	public CharacterEntity(String name, String imagePath) {
		super(name, imagePath);
		addParam(new Parameter("Lives", Integer.class, 5));
	}
	
	//Temporary, for DieAction testing - Matthew
	public int getLives(){
		 return lives;
	}
	
	//Temporary, for DieAction testing - Matthew
	public void setLives(int lives){
		this.lives = lives;
	}
}
