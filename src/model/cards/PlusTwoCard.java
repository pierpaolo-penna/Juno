package model.cards;

import controller.GameController;
import model.game.Game;
import model.player.Player;
/**
 * This class models a plus two card.
 * @author pierp
 *
 */
public final class PlusTwoCard extends Card {
	/**
	 * The point value is 25 (arbitrary).
	 * @param color The color of the card.
	 */
	protected PlusTwoCard(CardColor color) {
		super(color, 25);
	}
	/**
	 * Check {@link Card} toString() method for further details.
	 */
	public String toString() {
		return super.toString()+" PLUS TWO";
	}
	/**
	 * This method models the behavior of the Plus Two card: the target has to draw two cards.
	 */
	@Override
	public void effect(Player p)  {
		Game g = GameController.getInstance().getGame();
		Player target = g.pickNextPlayer(p);
		target.draw(g.getDeck(),2);
	}
}
