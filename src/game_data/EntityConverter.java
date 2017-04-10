package game_data;

import java.lang.reflect.Field;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import engine.Entity;
import engine.game.EngineController;
import javafx.beans.property.SimpleDoubleProperty;

public class EntityConverter implements Converter {

	@Override
	public boolean canConvert(Class arg0) {
		return arg0.equals(Entity.class) || arg0.getSuperclass().equals(Entity.class);
	}

	@Override
	public void marshal(Object arg0, HierarchicalStreamWriter writer, MarshallingContext context) {
		Entity entity = (Entity) arg0;
		writer.startNode("EntityType");
		writer.setValue(entity.getDisplayName());
		writer.endNode();
		
		Class<?> objClass = entity.getClass();
		Field[] fields = objClass.getDeclaredFields();
		writeFields(entity, fields, writer, context);
		
		objClass = objClass.getSuperclass();
		fields = objClass.getDeclaredFields();
		writeFields(entity, fields, writer, context);
		
		writer.close();
	}

	private void writeFields(Object entity, Field[] fields, HierarchicalStreamWriter writer, MarshallingContext context){
		for (Field field : fields) {
			if (java.lang.reflect.Modifier.isStatic(field.getModifiers()))
				continue;
			field.setAccessible(true);
			String name = field.getName();
			Object value = null;
			try {
				value = field.get(entity);
			} catch (Exception e) {
				// TODO remove print stack trace
				e.printStackTrace();
			}
			if (value != null) {
				writer.startNode(name);
				context.convertAnother(value);
				writer.endNode();
			}
		}
	}
	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		EngineController controller = new EngineController();
		
		reader.moveDown();
		System.out.println(reader.getValue());
		Entity entity = controller.createEntity(reader.getValue());
		reader.moveUp();
	
		while (reader.hasMoreChildren()) {
			reader.moveDown();
			Field field = null;
			try {
			//	System.out.println("reader"+reader.toString());
			//	System.out.println("readernamenode"+reader.getNodeName());
				
				//System.out.println(entity);//
				//System.out.println("getclass"+ entity.getClass());
			//	System.out.println("field"+entity.getClass().getDeclaredField(reader.getNodeName()));
				
				field = entity.getClass().getDeclaredField(reader.getNodeName());
			} catch (NoSuchFieldException | SecurityException e) {
				try{
					System.out.println("error here");
				field = entity.getClass().getSuperclass().getDeclaredField(reader.getNodeName());
				}
				catch (Exception e1){
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			if (field == null)
				break;
			field.setAccessible(true);
			Object value;
			System.out.println("Value: " + reader.getValue());
			if (field.getType().equals(SimpleDoubleProperty.class))
				value = (SimpleDoubleProperty)context.convertAnother(entity, SimpleDoubleProperty.class);
				//value = new SimpleDoubleProperty(Double.parseDouble(reader.getValue()));
			
			else
				value = context.convertAnother(entity, field.getType());
			try {
				field.set(entity, value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reader.moveUp();
		}
		
		
		return entity;
	}

}
