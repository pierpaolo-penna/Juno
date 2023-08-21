package controller.buttons;

import java.awt.event.MouseEvent;

import controller.GameController;
import controller.player.PlayerRecorder;
import view.window.JunoWindow;
/**
 * This class models the behavior of the button quit that appears on the <code>GameStats</code> screen.
 * @author pierp
 *
 */
final class GameStatsQuitButton extends ButtonWithIconHandler {
	/**
	 * The constructor is protected, according to the Factory pattern.
	 */
	protected GameStatsQuitButton() {}
	/**
	 * This method is used for closing the <code>GameStats</code> screen. Before doing so, it records the result of the game that just finished.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int winner = GameController.getInstance().getGame().getWinner();
		PlayerRecorder.gameEnded(winner);
		JunoWindow.getInstance().switchToHomepage();
	}
}
