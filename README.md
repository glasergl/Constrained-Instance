# Constrained Instance
Often, when programming a new class, there are object variables which have a type that represents a wider range of values than allowed.
For example, storing the amount of money for a transaction in an integer technically also allows negative values or 0.
Another example would be strings that should only contain alphabetical characters and not be empty, e.g., storing names.
Usually, one would need to check at every position in the code where a new assignment happens whether the constraint holds.
Even if there is just one method that does the assignment, i.e., a setter, this is usually not close to the variable declaration.
Therefore, the constraint checked in the setter is easily missed by readers.

Fortunately, this library contains the wrapper type `Constrained` which can be used to wrap an instance of a class and pass a predicate at construction time.
The predicate is added right at the instantiation which means it is written directly at the variable that is constrained.
This makes it very clear what values a variable can take.
Note that every instance of `Constrained` must be final in order to ensure manipulation only happens through the `Constrained` class.

The predicate is evaluted for the initial and every new value and throws an exception in the invalid case.
Furthermore, commonly needed types are directly given, e.g., `PositiveInteger` (only integer > 0), `DigitCharacter` (only digits of the decimal system) or `RegExString` (stored string must match a given regular expression).
Lastly, there is a method called update which makes it easy to update the value without using the getter and setter.
Instead of
```java
final PositiveInteger i = new PositiveInteger(10);
i.set(i.get() + 10);
```
one can do
```java
final PositiveInteger i = new PositiveInteger(10);
i.update(j -> j + 10);
```
which looks cleaner (especially for more sophisticated updates).

# Examples
```java
public class Transaction {
	private final PositiveInteger amount;
	private final RegExString from;
	private final RegExString to;
	
	public Transaction(final int amount, final String from, final String to) {
		this.amount = new PositiveInteger(amount); // throws IllegalArgumentException if amount <= 0
		this.from = new RegExString(from, "^[a-zA-Z0-9]$"); // throws IllegalArgumentException if from is not alphanumeric
		this.to = new RegExString(to, "^[a-zA-Z0-9]$"); // throws IllegalArgumentException if from is not alphanumeric
	}
}
```

One could also do
```java
public class Transaction {
	private final int amount;
	private final String from;
	private final String to;
	
	public Transaction(final int amount, final String from, final String to) {
		this.amount = amount;
		this.from = from;
		this.to = to;
	}
	
	public static void main(final String[] commandLineArguments) {
		final Transaction firstTransaction = getFirstTransaction();
		final Constrained<Transaction> currentTransaction = new Constrained<Transaction>(firstTransaction, t -> t.amount > 0 && t.from.matches("^[a-zA-Z0-9]$") && t.to.matches("^[a-zA-Z0-9]$"));
		// When only using the Transaction instance from currentTransaction, it is ensured that the specified predicate is fulfilled
		
		currentTransaction.set(new Transaction(1000, "abcd", "xyz1"));
		currentTransaction.set(new Transaction(-1, "abcd", "xyz1")); // throws IllegalArgumentException, because the given amount is not positive
	}
}
```

# Maven Central
Currently, this library is not in any dependency repository.
Nevertheless, it could be done eventually if me or some other people find good use.
In order to try the library, clone this repository, run `mvn install` and then the import
```xml
<dependency>
  <groupId>todo</groupId>
  <artifactId>constrained-instance</artifactId>
  <version>1.0.0</version>
</dependency>
```
is resolved locally.
