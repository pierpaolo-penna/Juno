package view.components;

import javax.swing.JPanel;

import controller.buttons.ButtonsVariations;
/**
 * This is the screen visualized when the application gets started. 
 * It has been developed according to the sigleton pattern.
 * @author pierp
 *
 */
public final class SelectorMenu extends JPanel {
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The only instance of this class.
	 */
	private static SelectorMenu selectorMenuInstance;
	/**
	 * One of the two buttons that appear on this panel.
	 */
	private ButtonWithIcon login, register;
	/**
	 * A private constructor, according to the singleton pattern.
	 */
	private SelectorMenu() {
		setBounds(280, 350, 440, 350);
		setOpaque(false);
		setLayout(null);
		
		
		login = new ButtonWithIcon("login.png", "login2.png", false, ButtonsVariations.SELECTOR_MENU_LOGIN);
		login.setLocation(44, 84);
			
		add(login);
			
		
		register = new ButtonWithIcon("register.png", "register2.png", false, ButtonsVariations.SELECTOR_MENU_REGISTER);
		register.setLocation(240, 84);
			
		add(register);
		
		this.setVisible(true);
	}
	/**
	 * Getter for the instance of this class.
	 * @return The instance.
	 */
	public static SelectorMenu getInstance() {
		if(selectorMenuInstance == null) selectorMenuInstance = new SelectorMenu();
		return selectorMenuInstance;
	}
}
