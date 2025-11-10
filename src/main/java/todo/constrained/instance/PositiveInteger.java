package todo.constrained.instance;

/**
 * Wrapper class for an integer that can only be positive
 */
public final class PositiveInteger extends Constrained<Integer> {
	public PositiveInteger(Integer initialValue) {
		super(initialValue, i -> i > 0);
	}
}
