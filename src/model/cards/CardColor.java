package model.cards;
/**
 * All the colors that the cards can assume.
 * @author pierp
 *
 */
public enum CardColor {
	/**
	 * For the red cards.
	 */
	RED(0), 
	/**
	 * For the green cards.
	 */
	GREEN(1),
	/**
	 * For the yellow cards.
	 */
	YELLOW(2),
	/**
	 * For the blue cards.
	 */
	BLUE(3),
	/**
	 * For the black cards.
	 */
	BLACK(4);
	/**
	 * The numerical value assigned with each color.
	 */
	private int value;
	/**
	 * Private constructor for the enum.
	 * @param value The numerical value associated with each <code>CardColor</code>.
	 */
	private CardColor(int value) {
		this.value = value;
	}
	/**
	 * Getter for the numerical value of the color.
	 * @return The numerical value.
	 */
	public int getValue() {
		return this.value;
	}
}
