package model.cards;

import model.player.*;
/**
 * This class models a Jolly card.
 * @author pierp
 *
 */
public final class JollyCard extends Card {
	/**
	 * The color must be black, while the point value is 30 (arbitrary).
	 */
	protected JollyCard() {
		super(CardColor.BLACK, 30);
	}
	/**
	 * Check {@link Card} toString() method for more details.
	 */
	public String toString() {
		return super.toString()+" JOLLY";
	}
	/**
	 * This method models the behavior of the Jolly card: if the player is computer-controlled,
	 * the method pickNewColor() from {@link Player} is called, while if the player is the user a new
	 * {@link UserNeedsToPickColorException} gets thrown.
	 */
	public void effect(Player p) throws UserNeedsToPickColorException {
		if(p instanceof User) throw new UserNeedsToPickColorException(this);
		else {
			CardColor c = p.getHand().pickNewColor();
			setColor(c);
		}
	}
	/**
	 * Jolly cards need their color to be set after they are played.
	 * @param c The new color of the card.
	 */
	public void setColor(CardColor c) {
		color = c;
	}
}
