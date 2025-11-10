package todo.constrained.instance;

import java.util.List;

/**
 * Wrapper class for a character that can only be a decimal digit
 */
public final class DigitCharacter extends Constrained<Character> {
	public static final List<Character> DIGIT_CHARACTERS = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

	public DigitCharacter(final Character initialValue) {
		super(initialValue, c -> DIGIT_CHARACTERS.contains(c));
	}
}
