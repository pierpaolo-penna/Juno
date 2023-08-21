package controller.buttons;

import java.awt.event.MouseEvent;

import view.window.JunoWindow;
/**
 * This class models the behavior of the "register" button that appears on the <code>SelectorMenu</code>.
 * @author pierp
 *
 */
final class SelectorMenuRegisterButton extends ButtonWithIconHandler {
	/**
	 * The constructor is protected, according to the Factory pattern.
	 */
	protected SelectorMenuRegisterButton() {}
	/**
	 * This method loads the <code>RegisterScreen</code>.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		JunoWindow.getInstance().getUnoBackground().loadRegisterScreen();
	}
}
