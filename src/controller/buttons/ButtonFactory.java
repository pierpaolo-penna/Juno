package controller.buttons;

import java.awt.event.MouseListener;
/**
 * This class is the root of the buttons package.
 * The whole package has been designed according to the Factory pattern.
 * The package contains all the customized {@link MouseListener} that are used throughout the application.
 * @author pierp
 *
 */
public final class ButtonFactory {
	/**
	 * This method is responsible for the construction of the needed {@link MouseListener}.
	 * @param button The name of the button that needs to be created.
	 * @return The button created.
	 */
	public static ButtonWithIconHandler newButton(ButtonsVariations button) {
		ButtonWithIconHandler b = null;

		if(button.equals(ButtonsVariations.HOMEPAGE_PANEL_PLAY)) b = new HomepagePanelPlayButton();
		else if(button.equals(ButtonsVariations.HOMEPAGE_PANEL_QUIT)) b = new HomepagePanelQuitButton();
		else if(button.equals(ButtonsVariations.HOMEPAGE_PANEL_STATS)) b = new HomepagePanelStatsButton();
		else if(button.equals(ButtonsVariations.GAME_STATS_QUIT)) b = new GameStatsQuitButton();
		else if(button.equals(ButtonsVariations.LOGIN_FORM_LOGIN)) b = new LoginFormLoginButton();
		else if(button.equals(ButtonsVariations.LOGIN_FORM_QUIT)) b = new LoginFormQuitButton();
		else if(button.equals(ButtonsVariations.REGISTER_FORM_BROWSE)) b = new RegisterFormBrowseButton();
		else if(button.equals(ButtonsVariations.REGISTER_FORM_QUIT)) b = new RegisterFormQuitButton();
		else if(button.equals(ButtonsVariations.REGISTER_FORM_REGISTER)) b = new RegisterFormRegisterButton();
		else if(button.equals(ButtonsVariations.SELECTOR_MENU_LOGIN)) b = new SelectorMenuLoginButton();
		else if(button.equals(ButtonsVariations.SELECTOR_MENU_REGISTER)) b = new SelectorMenuRegisterButton();
		else if(button.equals(ButtonsVariations.STATS_PANEL_EDIT_AVATAR)) b = new StatsPanelEditAvatarButton();
		else if(button.equals(ButtonsVariations.STATS_PANEL_EDIT_PASSWORD)) b = new StatsPanelEditPasswordButton();
		else if(button.equals(ButtonsVariations.STATS_PANEL_QUIT)) b = new StatsPanelQuitButton();
		return b;
	}
}
