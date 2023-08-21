package controller.buttons;

import java.awt.event.*;

import view.window.JunoWindow;
/**
 * This class models the behavior of the "login" button that appears on the <code>SelectorMenu</code>.
 * @author pierp
 *
 */
final class SelectorMenuLoginButton extends ButtonWithIconHandler {
	/**
	 * The constructor is protected, according to the Factory pattern.
	 */
	protected SelectorMenuLoginButton() {}
	/**
	 * This method loads the <code>LoginScreen</code>.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		JunoWindow.getInstance().getUnoBackground().loadLoginScreen();
	}
}
