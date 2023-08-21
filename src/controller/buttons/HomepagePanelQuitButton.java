package controller.buttons;

import java.awt.event.MouseEvent;

import controller.player.LogoutException;
import model.player.*;
import view.window.UnoBackground;
import view.components.HomepagePanel;
/**
 * This class implements the behavior of the "quit" button that appears on the <code>HomepagePanel</code>.
 * Check {@link HomepagePanel} for more details.
 * @author pierp
 *
 */
final class HomepagePanelQuitButton extends ButtonWithIconHandler {
	/**
	 * The constructor is protected, according to the Factory pattern.
	 */
	protected HomepagePanelQuitButton() {}
	/**
	 * This method logs out the <code>User</code>, reloading the <code>SelectorMenu</code>.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			User.logoutUser();
		} catch (LogoutException e1) {
			UnoBackground b = UnoBackground.getInstance();
			b.logout();
			b.loadSelectorMenu();
		}
	}
}
