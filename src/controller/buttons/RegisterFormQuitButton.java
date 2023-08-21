package controller.buttons;

import java.awt.event.MouseEvent;

import view.window.UnoBackground;
/**
 * This class models the behavior of the "quit" button that appears on the <code>RegisterScreen</code>.
 * Check {@link RegisterScreen} for more details.
 * @author pierp
 *
 */
final class RegisterFormQuitButton extends ButtonWithIconHandler {
	/**
	 * The constructor is protected, according to the Factory pattern.
	 */
	protected RegisterFormQuitButton() {}
	/**
	 * This method reloads the <code>SelectorMenu</code>.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		UnoBackground.getInstance().loadSelectorMenu();
	}
}
