package todo.constrained.instance;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Base class for all constrained instances. The idea is to pass a predicate
 * that defines the allowed values of type T for the specific instance. Thus,
 * the predicate can be evaluated every time the value changes and it is ensured
 * that the instance never contains an invalid value.
 * 
 * @param <T> any
 */
public class Constrained<T> {
	private T value;
	private Predicate<T> validation;

	/**
	 * @param initialValue which must be valid according to the given predicate
	 * @param validation   - function that defines "valid"
	 * @throws IllegalArgumentException If initialValue is not valid
	 */
	public Constrained(final T initialValue, final Predicate<T> validation) {
		super();
		this.validation = validation;
		set(initialValue);
	}

	/**
	 * @return Current value
	 */
	public final T get() {
		return value;
	}

	/**
	 * @param nextValue which must be valid according to the initially configured
	 *                  predicate
	 * @throws IllegalArgumentException If nextValue is not valid
	 */
	public final void set(final T nextValue) {
		if (!validation.test(nextValue)) {
			throw new IllegalArgumentException("Initial value invalid");
		}
		value = nextValue;
	}

	/**
	 * Updates the current value according to the function, i.e., the function is
	 * applied on the current value and the result is set as the current value, if
	 * valid
	 * 
	 * @param update - function given the current value, maps to a new value
	 * @throws IllegalArgumentException If the result of the given function with the
	 *                                  current value as parameter produces an
	 *                                  invalid value
	 */
	public final void update(final Function<T, T> update) {
		final T nextValue = update.apply(value);
		set(nextValue);
	}

	/**
	 * @return String representation of current value
	 */
	@Override
	public String toString() {
		return value.toString();
	}

	/**
	 * @return Hash code of current value
	 */
	@Override
	public final int hashCode() {
		return value.hashCode();
	}

	/**
	 * @param other
	 * @return Whether the value of this constrained equals the value of the other
	 *         constrained, else false
	 */
	@Override
	public final boolean equals(final Object other) {
		if (!(other instanceof Constrained)) {
			return false;
		}
		final Constrained<?> otherConstrained = (Constrained<?>) other;
		return value.equals(otherConstrained.value);
	}
}
