package model.cards;

import model.player.Player;
import controller.GameController;
import model.game.Game;
/**
 * This class models a Reverse card.
 * @author pierp
 *
 */
public final class ReverseCard extends Card {
	/**
	 * The points value is 20 (arbitrary).
	 * @param color The color of the card.
	 */
	protected ReverseCard(CardColor color) {
		super(color, 20);
	}
	/**
	 * Check {@link Card} toString() method for more details.
	 */
	public String toString() {
		return super.toString()+" REVERSE";
	}
	/**
	 * This particular method models the behavior of the Reverse card: the direction of the game gets reversed
	 * Check {@link Game} for more details.
	 */
	public void effect(Player p) {
		GameController.getInstance().getGame().toggleClockwise();
	}
}
