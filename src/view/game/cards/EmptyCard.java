package view.game.cards;

import model.cards.Card;
import model.player.Player;

/**
 * An empty card, without color and zero points.
 * It has been put in this view folder because it is only needed here, and has no influence of the model.
 * This class has been designed according to the singleton pattern.
 * @author pierp
 *
 */
public final class EmptyCard extends Card {
	/**
	 * The only instance needed.
	 */
	public final static EmptyCard instance = new EmptyCard();
	/**
	 * A private constructor, acccording to the singleton pattern.
	 */
	private EmptyCard() {
		super(null, 0);
	}
	/**
	 * This method doesn't require an actual implementation.
	 */
	@Override
	public void effect(Player p) {}
}
