package model.cards;
/**
 * All the number values for the {@link NumberedCard} class.
 * @author pierp
 *
 */
public enum CardNumber {
	/**
	 * The number 0.
	 */
	ZERO(0),
	/**
	 * The number 1.
	 */
	ONE(1),
	/**
	 * The number 2. 
	 */
	TWO(2),
	/**
	 * The number 3.
	 */
	THREE(3),
	/**
	 * The number 4.
	 */
	FOUR(4),
	/**
	 * The number 5.
	 */
	FIVE(5),
	/**
	 * The number 6.
	 */
	SIX(6),
	/**
	 * The number 7.
	 */
	SEVEN(7),
	/**
	 * The number 8.
	 */
	EIGHT(8),
	/**
	 * The number 9.
	 */
	NINE(9);
	/**
	 * The numeric value of the card.
	 */
	private int value;
	/**
	 * Private constructor for the enum.
	 * @param value The numeric value.
	 */
	private CardNumber(int value) {
		this.value = value;
	}
	/**
	 * Getter for the numeric value.
	 * @return The numeric value.
	 */
	int getValue() {
		return this.value;
	}
}
