package model.cards;

import model.player.Player;
/**
 * This class models a card with a number on it.
 * @author pierp
 *
 */
public final class NumberedCard extends Card {
	/**
	 * The number on the card.
	 */
	private CardNumber number;
	/**
	 * @param color The color of the card
	 * @param number The number on the card
	 */
	protected NumberedCard(CardColor color, CardNumber number) {
		super(color, number.getValue());
		this.number = number;
	}
	/**
	 * Getter for the number of the card.
	 * @return The number.
	 */
	public CardNumber getNumber() {
		return this.number;
	}
	/**
	 * Check {@link Card} toString() method for further details.
	 */
	public String toString() {
		return super.toString()+" "+this.number;
	}
	/**
	 * This card has no effect, thus, no actual implementation is required
	 */
	public void effect(Player p) {}
}
