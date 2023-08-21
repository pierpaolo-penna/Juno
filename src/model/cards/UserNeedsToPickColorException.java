package model.cards;

import model.player.User;
/**
 * This exception gets thrown when the {@link User} plays a {@link JollyCard} or a {@link PlusFourCard}.
 * @author pierp
 *
 */
public final class UserNeedsToPickColorException extends Exception {
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The card whose method "effect" threw the exception.
	 */
	private Card card;
	/**
	 * The constructor of the exception.
	 * @param c The card that threw the exception.
	 */
	public UserNeedsToPickColorException(Card c) {
		card = c;
	}
	/**
	 * Getter for the card stored in this class.
	 * @return The card.
	 */
	public Card getCard() {
		return card;
	}
}
