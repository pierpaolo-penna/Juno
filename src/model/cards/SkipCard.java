package model.cards;

import controller.GameController;
import model.game.Game;
import model.player.Player;
/**
 * This class models the Skip card.
 * @author pierp
 *
 */
public final class SkipCard extends Card {
	/**
	 * The points value is 25 (arbitrary).
	 * @param color The color of the cards.
	 */
	protected SkipCard(CardColor color) {
		super(color, 25);
	}
	/**
	 * Check {@link Card} toString() method for more details.
	 */
	public String toString() {
		return super.toString()+" SKIP";
	}
	/**
	 * This method models the behavior of the Skip card: the next player gets stopped.
	 */
	public void effect(Player p) {
		Game g = GameController.getInstance().getGame();
		g.pickNextPlayer(p).setIsStopped(true);
	}
}
