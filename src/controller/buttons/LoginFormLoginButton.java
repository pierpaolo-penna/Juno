package controller.buttons;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import controller.player.LoginFailedException;
import controller.player.UserNotFoundException;
import model.player.User;
import view.components.HomepagePanel;
import view.components.LoginScreen;
import view.components.StatsPanel;
import view.window.UnoBackground;
/**
 * This class models the behavior of the "login" button that appears on the <code>LoginScreen</code>
 * Check {@link LoginScreen} for more details.
 * @author pierp
 *
 */
final class LoginFormLoginButton extends ButtonWithIconHandler {
	/**
	 * The constructor is protected, according to the Factory pattern.
	 */
	protected LoginFormLoginButton() {}
	/**
	 * This method attempts to log in a <code>User</code>. If the attempt is successful,
	 * it proceeds to load the <code>HomepagePanel</code>, otherwise error messages appear.
	 * Check {@link User} for more details.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void mouseClicked(MouseEvent e) {
		LoginScreen l = LoginScreen.getInstance();
		
		String username = l.getNameField().getText();
		String password = l.getPassword().getText();
		
		if(username == null || username.equals("") || password == null || password.equals("")) {
			JOptionPane.showMessageDialog(null, "Fill all the empty fields", "WARNING", JOptionPane.WARNING_MESSAGE);
			return;
		}
		else {
			try {
				User user = User.loginUser(username, password);
				UnoBackground b = UnoBackground.getInstance();
				b.setHomepagePanel(HomepagePanel.getInstance(user));
				b.setStatsPanel(new StatsPanel(user));
				b.loadHomepagePanel();
			} catch (LoginFailedException f) {
				JOptionPane.showMessageDialog(null, "Wrong password", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (UserNotFoundException f) {
				JOptionPane.showMessageDialog(null, "User not found", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
