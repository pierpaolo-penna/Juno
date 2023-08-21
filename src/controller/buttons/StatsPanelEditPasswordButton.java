package controller.buttons;

import java.awt.event.MouseEvent;

import view.components.ChangePasswordForm;
/**
 * This class models the behavior of the "edit" button responsible for changing the password of the <code>User</code>.
 * @author pierp
 *
 */
final class StatsPanelEditPasswordButton extends ButtonWithIconHandler {
	/**
	 * The constructor is protected, according to the Factory pattern.
	 */
	protected StatsPanelEditPasswordButton() {}
	/**
	 * This method loads a new <code>ChangePasswordForm</code>. 
	 * Check {@link ChangePasswordForm} for more details.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		ChangePasswordForm passwordChanger = new ChangePasswordForm();
		passwordChanger.setVisible(true);
	}
}
