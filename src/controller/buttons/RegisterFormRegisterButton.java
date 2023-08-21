package controller.buttons;

import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.*;

import controller.player.DuplicateUsernameException;
import controller.player.PlayerRecorder;
import controller.player.RegisterException;
import view.components.RegisterScreen;
import view.window.UnoBackground;
/**
 * This class models the behavior of the "register" button that appears on the <code>RegisterScreen</code>.
 * Check {@link RegisterScreen} for more details.
 * @author pierp
 *
 */
final class RegisterFormRegisterButton extends ButtonWithIconHandler {
	/**
	 * The constructor is protected, according to the Factory pattern.
	 */
	protected RegisterFormRegisterButton() {}
	/**
	 * This method attempts to register a new <code>User</code>. If the attempt isn't succesful,
	 * error messages will appear, otherwise the data for the new <code>User</code> will be recorded.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void mouseClicked(MouseEvent e) {
		RegisterScreen r = RegisterScreen.getInstance();
		String name = r.getNameField().getText();
		String password = r.getPassword().getText();
		String confirmPassword = r.getConfirmPassword().getText();
		File avatar = r.getAvatarChooser().getSelectedFile();
		
		if(name.contains(" ") || name.contains("\\")) {
			JOptionPane.showMessageDialog(null, "Username must not contain characters ' ' or '\\'", "WARNING", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(name.length()>12) {
			JOptionPane.showMessageDialog(null, "Username is too long", "WARNING", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(name.equals("Player 1")||name.equals("Player 2")||name.equals("Player 3")) {
			JOptionPane.showMessageDialog(null, "You can't use this username", "WARNING", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(name == null || name.equals("") || password == null ||
				password.equals("") || confirmPassword == null ||
				confirmPassword.equals("") || avatar == null) {
			JOptionPane.showMessageDialog(null, "Fill all the empty fields and select an avatar", "WARNING", JOptionPane.WARNING_MESSAGE);
			return;
		}
		else {
			if(!password.equals(confirmPassword)) {
				JOptionPane.showMessageDialog(null, "Passwords do not match", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else {
				try {
					PlayerRecorder.register(name, confirmPassword, avatar.toString());
				} catch (DuplicateUsernameException f) {
					JOptionPane.showMessageDialog(null, "Username already in use", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				} catch (RegisterException f) {
					JOptionPane.showMessageDialog(null, "Succesfully registered", "SUCCESS", JOptionPane.WARNING_MESSAGE);
					UnoBackground.getInstance().loadLoginScreen();
				}
			}
		}
	}
}
