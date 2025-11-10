package todo.constrained.instance;

/**
 * Wrapper class for a character that may be a digit or a alphabetic character
 */
public final class AlphaNumericCharacter extends Constrained<Character> {
	public AlphaNumericCharacter(final Character initialValue) {
		super(initialValue, c -> DigitCharacter.DIGIT_CHARACTERS.contains(c)
				|| AlphabeticalCharacter.ALPHABETICAL_CHARACTERS.contains(c));
	}
}
