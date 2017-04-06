package engine;

public class Parameter {
	private String name;
	private Class<?> clazz;
	private Object obj;

	public Parameter(String name, Class<?> clazz) {
		this.name = name;
		this.clazz = clazz;
	}

	public Parameter(String name, Class<?> clazz, Object def) {
		this(name, clazz);
		this.obj = def;
	}

	public Class<?> getParameterClass() {
		return this.clazz;
	}

	public String getName() {
		return name;
	}

	public Object getObject() {
		return obj;
	}

	public void setObject(Object value) {
		this.obj = value;
	}
}
