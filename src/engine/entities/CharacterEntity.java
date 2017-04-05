package engine.entities;

import java.util.ArrayList;
import java.util.List;

import engine.Entity;
import engine.Parameter;

public class CharacterEntity extends Entity {

	public CharacterEntity(String name, String imagePath){
		super(name, imagePath);
		Parameter lives = new Parameter("Lives", Integer.class, 5);
		List<Parameter> params = new ArrayList<Parameter>();
		params.add(lives);
		setParams(params);
	}
}
