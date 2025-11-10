package todo.constrained.instance.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import todo.constrained.instance.PositiveInteger;

class PositiveIntegerTest {
	@Test
	void testInitialValueNormalCase() {
		final PositiveInteger i = new PositiveInteger(3);
		assertEquals(3, i.get());

		final PositiveInteger j = new PositiveInteger(16);
		assertEquals(16, j.get());
	}

	@Test
	void testInitialValueExtremeCase() {
		final PositiveInteger i = new PositiveInteger(1);
		assertEquals(1, i.get());

		final PositiveInteger j = new PositiveInteger(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, j.get());
	}

	@Test
	void testInvalidInitialValue() {
		assertThrows(IllegalArgumentException.class, () -> {
			new PositiveInteger(0);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new PositiveInteger(-1);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new PositiveInteger(-10);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new PositiveInteger(Integer.MIN_VALUE);
		});
	}

	@Test
	void testValidValues() {
		final PositiveInteger i = new PositiveInteger(3);
		i.set(5);
		assertEquals(5, i.get());
		i.set(1);
		assertEquals(1, i.get());
		i.set(288);
		assertEquals(288, i.get());

	}

	@Test
	void testInvalidValues() {
		final PositiveInteger i = new PositiveInteger(7);
		i.set(5);
		i.set(28);
		i.set(1);
		assertThrows(IllegalArgumentException.class, () -> {
			i.set(-1);
		});
	}

	@Test
	void testValidUpdate() {
		final PositiveInteger i = new PositiveInteger(7);
		i.update(j -> j + 5);
		assertEquals(12, i.get());
		i.update(j -> j / 3);
		assertEquals(4, i.get());
	}

	@Test
	void testInvalidUpdateNormalCase() {
		final PositiveInteger i = new PositiveInteger(7);
		assertThrows(IllegalArgumentException.class, () -> {
			i.update(j -> j - 9);
		});
	}

	@Test
	void testInvalidUpdateExtremeCase() {
		final PositiveInteger i = new PositiveInteger(4);
		assertThrows(IllegalArgumentException.class, () -> {
			i.update(j -> (j / 4) - 1);
		});
	}

	@Test
	void testEquals() {
		final PositiveInteger i = new PositiveInteger(8);
		final PositiveInteger j = new PositiveInteger(12);
		final PositiveInteger k = new PositiveInteger(8);
		
		assertNotEquals(i, j);
		assertNotEquals(j, i);
		assertEquals(i, k);
		assertEquals(i, i);
	}
}
