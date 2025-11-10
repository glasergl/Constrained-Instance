package todo.constrained.instance;

/**
 * Wrapper class for a string that matches a given regular expression
 */
public class RegExString extends Constrained<String> {
	public RegExString(String initialValue, final String regEx) {
		super(initialValue, s -> s.matches(regEx));
	}
}
