package controller.buttons;

import java.awt.event.MouseEvent;

import controller.GameController;
import model.player.User;
import view.components.HomepagePanel;
/**
 * This class models the beavior of the "play" button that appears on the <code>HomepagePanel</code>.
 * Check {@link HomepagePanel} for more details.
 * @author pierp
 *
 */
final class HomepagePanelPlayButton extends ButtonWithIconHandler {
	/**
	 * The constructor is protected, according to the Factory pattern.
	 */
	protected HomepagePanelPlayButton() {}
	/**
	 * This method starts a new <code>Game</code>.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		GameController.getInstance().newGame(User.getLoggedInUser());
	}
}
