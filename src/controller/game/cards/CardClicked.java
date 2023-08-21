package controller.game.cards;

import java.awt.event.MouseEvent;

import controller.GameController;
import model.cards.Card;
import model.cards.UserNeedsToPickColorException;
import model.game.Game;
import model.player.User;
import view.game.cards.CardInHandView;
/**
 * This class is a <code>MouseListener</code> for the <code>User</code> to be able to play a card from his hand.
 * @author pierp
 *
 */
public final class CardClicked extends CardHandler {
	/**
	 * This method is used to play a <code>Card</code> from the <code>User</code>'s hand.
	 * It checks if it is the <code>User</code>'s turn, if he is not stopped and if the card is playable. 
	 * If successful, the selected card will be played. Then, if the <code>User</code> has no more cards,
	 * the game ends, otherwise the next computer-controlled player starts his turn.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		GameController gc = GameController.getInstance();
		Game g = gc.getGame();
		User u = User.getLoggedInUser();
		if(g.getCurrentPlayer().equals(u)) {
			if(u.isStopped()) return;
			else {
				Card c = ((CardInHandView)e.getSource()).getCard();
				if(!c.isPlayable()) return;
				else {
					try {
						g.playCard(c, u);
					} catch (UserNeedsToPickColorException e1) {
						ColorPicker.chooseColor(e1.getCard());
						return;
					}
					if(u.getHand().isEmpty()) {
						g.gameHasEnded(0);
					}
					g.computerPlayerPlayGame(g.pickNextPlayer(u));
				}
			}
		}
		else return;
	}
}
