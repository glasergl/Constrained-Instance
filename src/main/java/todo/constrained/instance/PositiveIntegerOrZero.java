package todo.constrained.instance;

/**
 * Wrapper class for an integer that can be only positive or 0
 */
public class PositiveIntegerOrZero extends Constrained<Integer> {
	public PositiveIntegerOrZero(final Integer initialValue) {
		super(initialValue, i -> i >= 0);
	}
}
