package todo.constrained.instance;

import java.util.List;

/**
 * Wrapper class for a character that can only contain characters from the 26
 * letter alphabet
 */
public final class AlphabeticalCharacter extends Constrained<Character> {
	public static final List<Character> ALPHABETICAL_CHARACTERS = List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
			'Z');

	public AlphabeticalCharacter(final Character initialValue) {
		super(initialValue, c -> ALPHABETICAL_CHARACTERS.contains(c));
	}

}
