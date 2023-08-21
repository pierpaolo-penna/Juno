package controller.buttons;

import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFileChooser;

import view.components.RegisterScreen;
/**
 * This class models the behavior of the "browse" button that appears on the <code>RegisterScreen</code>.
 * Check {@link RegisterScreen} for more details.
 * @author pierp
 *
 */
final class RegisterFormBrowseButton extends ButtonWithIconHandler {
	/**
	 * The constructor is protected, according to the Factory pattern.
	 */
	protected RegisterFormBrowseButton() {}
	/**
	 * This method opens the <code>JFileChooser</code> for selecting the avatar of the <code>User</code>.
	 * The file chooser will be set on the avatars folder.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		JFileChooser aC = RegisterScreen.getInstance().getAvatarChooser(new File("src/resources/avatars"));
		aC.showOpenDialog(null);
	}
}
