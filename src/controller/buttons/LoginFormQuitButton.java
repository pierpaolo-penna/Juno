package controller.buttons;

import java.awt.event.MouseEvent;

import view.window.UnoBackground;
import view.components.LoginScreen;
/**
 * This class models the behavior of the "quit" button that appears on the <code>LoginScreen</code>.
 * Check {@link LoginScreen} for more details.
 * @author pierp
 *
 */
final class LoginFormQuitButton extends ButtonWithIconHandler {
	/**
	 * The constructor is protected, according to the Factory pattern.
	 */
	protected LoginFormQuitButton() {}
	/**
	 * This method reloads the <code>SelectorMenu</code>.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		UnoBackground.getInstance().loadSelectorMenu();
	}
}
