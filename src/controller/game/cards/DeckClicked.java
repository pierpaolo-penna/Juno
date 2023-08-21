package controller.game.cards;

import java.awt.event.MouseEvent;

import controller.GameController;
import model.game.Game;
import model.player.User;
/**
 * This class is a <code>MouseListener</code> for when the <code>User</code> clicks on the deck to draw a card.
 * @author pierp
 *
 */
public final class DeckClicked extends CardHandler {
	/**
	 * This method is used to draw a <code>Card</code> from the <code>Deck</code>.
	 * It checks if it is the <code>User</code>'s turn and if he is not stopped.
	 * If successful, the <code>User</code> will draw a card, and then the next computer-controlled player starts his turn.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		GameController gc = GameController.getInstance();
		Game g = gc.getGame();
		User u = User.getLoggedInUser();
		if(g.getCurrentPlayer().equals(u)) {
			if(u.isStopped()) return;
			else {
				u.draw(g.getDeck(),1);
				g.computerPlayerPlayGame(g.pickNextPlayer(u));	
			}
		}
		else return;
	}
}
