package controller.game;

import java.awt.event.MouseEvent;

import controller.GameController;
import controller.buttons.ButtonWithIconHandler;
import model.game.Game;
import model.player.User;
/**
 * This class is a <code>MouseListener</code> for the "Uno" button that appears on the <code>GameView</code>.
 * @author pierp
 *
 */
public final class UnoButtonClicked extends ButtonWithIconHandler {
	/**
	 * This method checks if the <code>User</code> has all the requirements to say "Uno", and sets the corresponding boolean value.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		GameController gc = GameController.getInstance();
		Game g = gc.getGame();
		User u = User.getLoggedInUser();
		if((g.getCurrentPlayer().equals(u) || g.getCurrentPlayer().equals(g.pickNextPlayer(u)))&&u.getHand().getSize()==1) u.setHasSaidUno(true,u);
		else return;	
	}
}
