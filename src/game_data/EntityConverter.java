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
		for (Field field : fields) {
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
		writer.close();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		EngineController controller = new EngineController();
		reader.moveDown();
		Entity entity = controller.createEntity(reader.getValue());
		reader.moveDown();

		while (reader.hasMoreChildren()) {
			Field field = null;
			try {
				field = entity.getClass().getField(reader.getNodeName());
			} catch (NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Object value;
			if (field.getType().equals(SimpleDoubleProperty.class))
				value = new SimpleDoubleProperty(Double.parseDouble(reader.getValue()));
			else
				value = context.convertAnother(entity, field.getType());
			try {
				field.set(entity, value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return entity;
	}

}
