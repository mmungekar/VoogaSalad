package engine;

/**
 * Parses code in scripting languages such as Groovy which game designers use to add custom features,
 * such as Events or Actions. Should be instantiated in the GAE every time a new Groovy line(s) is input.
 * @author Matthew Barbano
 *
 */
public interface ScriptInterpreter {
	/**
	 * External Engine API. Sets the programming language to newLanguage, for example, Groovy.
	 * @param newLanguage
	 */
	public void changeLanguage(String newLanguage);
	/**
	 * Executes the code via a ScriptEngine passed in via this object's constructor.
	 */
	public void executeCode();

}
