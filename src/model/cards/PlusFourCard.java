package model.cards;

import controller.GameController;
import model.game.Game;
import model.player.*;
/**
 * This class models a Plus Four card.
 * @author pierp
 *
 */
public final class PlusFourCard extends Card {
	/**
	 * The color must be black, while the point value is 40 (arbitrary).
	 */
	protected PlusFourCard() {
		super(CardColor.BLACK, 40);
	}
	/**
	 * Check {@link Card} toString() method for further details.
	 */
	public String toString() {
		return super.toString()+" PLUS FOUR";
	}
	/**
	 * This method models the behavior of the Plus Four card: after the target draws four cards,
	 * if the player is computer-controlled, the method pickNewColor() from {@link Player} is called,
	 * while if the player is the user a new {@link UserNeedsToPickColorException} gets thrown.
	 */
	public void effect(Player p) throws UserNeedsToPickColorException {
		Game g = GameController.getInstance().getGame();
		Player target = g.pickNextPlayer(p);
		target.draw(g.getDeck(),4);
		if(!(p instanceof User)) {
			CardColor c = p.getHand().pickNewColor();
			setColor(c);
		}
		else {
			throw new UserNeedsToPickColorException(this);
		}
	}
	/**
	 * Plus Four cards need their color to be set after they are played.
	 * @param c The new color of the card.
	 */
	public void setColor(CardColor c) {
		color = c;
	}
	/**
	 * The <code>PlusFourCard</code> cannot be played in some circumstances. However, since they are
	 * fairly difficult to implement in the checkIfPlayable method from the {@link Card} class,
	 * I decided to manually set the card as not playable after checking the requirements. 
	 * Check {@link PlayerHand} for more details.
	 * @param tf True/false.
	 */
	public void setPlayable(boolean tf) {
		playable = tf;
	}
}
