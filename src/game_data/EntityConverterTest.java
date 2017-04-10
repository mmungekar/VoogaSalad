package game_data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import engine.Entity;
import engine.Event;
import engine.entities.CharacterEntity;
import engine.events.AlwaysEvent;

public class EntityConverterTest {
	public static void main(String[] args) {
		
		Entity entity = new CharacterEntity();//new CharacterEntity("Mario","src/resources/images/mario.png");
		entity.setName("Guilherme");
		entity.setX(100);
		entity.setY(55);
		entity.setImagePath("test");
		Event event = new AlwaysEvent();
		entity.addEvent(event);
		XStream xStream = new XStream(new DomDriver());
		xStream.registerConverter(new EntityConverter());
		//xStream.alias("person", Person.class);
		//System.out.println(xStream.toXML(entity));
		
		entity = (Entity) xStream.fromXML(xStream.toXML(entity));
		System.out.println(entity.getEvents());
		System.out.println(entity.getClass());
		
		//System.out.println(entity.getX());
		
	}
}
