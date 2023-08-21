package view.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.buttons.ButtonsVariations;
/**
 * This class contains a form for logging in.
 * It has been developed according to the singleton pattern.
 * @author pierp
 *
 */
public final class LoginScreen extends JPanel {
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The only instance of this class.
	 */
	private static LoginScreen loginScreenInstance = new LoginScreen();
	/**
	 * One of the labels of the form.
	 */
	private JLabel formLabel, nameLabel, passwordLabel;
	/**
	 * One of the buttons of the form.
	 */
	private ButtonWithIcon loginButton, quitButton;
	/**
	 * The text field for the name.
	 */
	private JTextField name;
	/**
	 * The text field for the password.
	 */
	private JPasswordField password;
	/**
	 * A private constructor, according to the singleton pattern.
	 */
	private LoginScreen() {
		setOpaque(true);
		setBackground(Color.LIGHT_GRAY);
		setBorder(new LineBorder(Color.BLACK, 2));
		setBounds(300, 350, 400, 250);
		setLayout(null);
		
		formLabel = new JLabel("LOGIN", SwingConstants.CENTER);
		formLabel.setBounds(130, 20, 140, 40);
		formLabel.setFont(new Font("Verdana", 1, 25));
		
		add(formLabel);
		
		nameLabel = new JLabel("Username: ");
		nameLabel.setBounds(80,70,110,30);
		nameLabel.setFont(new Font("Verdana", 1, 15));
		
		name = new JTextField(20);
		name.setBounds(210, 70, 130,30);
		
		add(nameLabel);
		add(name);
		
		passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(80, 110, 110, 30);
		passwordLabel.setFont(new Font("Verdana", 1, 15));
		
		password = new JPasswordField(20);
		password.setBounds(210, 110, 130, 30);
		
		add(passwordLabel);
		add(password);
		
		
		loginButton = new ButtonWithIcon("login.png", "login2.png", true, ButtonsVariations.LOGIN_FORM_LOGIN);
		quitButton = new ButtonWithIcon("quit.png", "quit2.png", true, ButtonsVariations.LOGIN_FORM_QUIT);
		
		loginButton.setLocation(96, 170);
		quitButton.setLocation(210, 170);
		
		add(loginButton);
		add(quitButton);
		
		setVisible(true);
	}
	/**
	 * Getter for the name text field.
	 * @return The name text field.
	 */
	public JTextField getNameField() {
		return name;
	}
	/**
	 * Getter for the password field.
	 * @return The password field.
	 */
	public JPasswordField getPassword() {
		return password;
	}
	/**
	 * Getter of the instance of the login screen.
	 * @return The instance.
	 */
	public static LoginScreen getInstance() {
		return loginScreenInstance;
	}
	/**
	 * This method empties the fields of the form.
	 */
	public void load() {
		name.setText("");
		password.setText("");
		setVisible(true);
	}
}
