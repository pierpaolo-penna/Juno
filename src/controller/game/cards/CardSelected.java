package controller.game.cards;

import java.awt.event.MouseEvent;

import view.game.cards.CardInHandView;
/**
 * This class is a <code>MouseListener</code> for when the <code>User</code> hovers the mouse on some card on his hand.
 * @author pierp
 *
 */
public final class CardSelected extends CardHandler {
	/**
	 * This method will enlarge the selected card when the mouse enters it.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		((CardInHandView)e.getSource()).enlargeCard();
	}
	/**
	 * This method will shrink the selected card when the mouse exits it.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		((CardInHandView)e.getSource()).shrinkCard();
	}
}
