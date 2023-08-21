package view.components;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.buttons.ButtonsVariations;
/**
 * This class contains a form for registering a new <code>User</code>.
 * It has been developed according to the singleton pattern.
 * @author pierp
 *
 */
public final class RegisterScreen extends JPanel {
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The only instance of this class.
	 */
	private static RegisterScreen registerScreenInstance = new RegisterScreen();
	/**
	 * One of the labels of the form.
	 */
	private JLabel formLabel, nameLabel, passwordLabel, confirmPasswordLabel, avatarLabel;
	/**
	 * One of the buttons of the form.
	 */
	private ButtonWithIcon registerButton, quitButton, browseButton;
	/**
	 * The text field for the username.
	 */
	private JTextField nameField;
	/**
	 * One of the two password fields.
	 */
	private JPasswordField password, confirmPassword;
	/**
	 * A file chooser for picking an avatar.
	 */
	private JFileChooser avatarChooser = new JFileChooser();
	/**
	 * A private constructor, according to the singleton pattern.
	 */
	private RegisterScreen() {
		setOpaque(true);
		setBackground(Color.LIGHT_GRAY);
		setBorder(new LineBorder(Color.BLACK, 2));
		setBounds(200, 325, 600, 300);
		setLayout(null);
		
		formLabel = new JLabel("REGISTER", SwingConstants.CENTER);
		formLabel.setBounds(200, 20, 200, 40);
		formLabel.setFont(new Font("Verdana", 1, 30));
		
		add(formLabel);
		
		
		nameLabel = new JLabel("Username: ");
		nameLabel.setBounds(40, 90, 110, 30);
		nameLabel.setFont(new Font("Verdana", 1, 15));
		
		add(nameLabel);

		nameField = new JTextField(20);
		nameField.setBounds(155, 90, 125,30);
		
		add(nameField);
		
		
		avatarLabel = new JLabel("Choose avatar: ");
		avatarLabel.setBounds(300, 90, 140, 30);
		avatarLabel.setFont(new Font("Verdana", 1, 15));
		
		add(avatarLabel);
		
		browseButton = new ButtonWithIcon("browse.png", "browse2.png", true, ButtonsVariations.REGISTER_FORM_BROWSE);
		browseButton.setLocation(450, 86);
		
		add(browseButton);
		
		
		passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(40, 145, 110, 30);
		passwordLabel.setFont(new Font("Verdana", 1, 15));
		
		add(passwordLabel);
		
		password = new JPasswordField(20);
		password.setBounds(155, 145, 125, 30);
		
		add(password);
		
		
		confirmPasswordLabel = new JLabel("Repeat psw: ");
		confirmPasswordLabel.setBounds(300, 145, 130, 30);
		confirmPasswordLabel.setFont(new Font("Verdana", 1, 15));
		
		add(confirmPasswordLabel);
		
		confirmPassword = new JPasswordField(20);
		confirmPassword.setBounds(435, 145, 125, 30);
		
		add(confirmPassword);
		
		
		registerButton = new ButtonWithIcon("register.png", "register2.png", true, ButtonsVariations.REGISTER_FORM_REGISTER);
		registerButton.setLocation(176, 214);
		
		add(registerButton);
		
		quitButton = new ButtonWithIcon("quit.png", "quit2.png", true, ButtonsVariations.REGISTER_FORM_QUIT);
		quitButton.setLocation(330, 214);
		
		add(quitButton);
	}
	/**
	 * Getter for the name field.
	 * @return The name field.
	 */
	public JTextField getNameField() {
		return nameField;
	}
	/**
	 * Getter for the password field.
	 * @return The password field.
	 */
	public JPasswordField getPassword() {
		return password;
	}
	/**
	 * Getter for the confirm password field.
	 * @return The confirm password field.
	 */
	public JPasswordField getConfirmPassword() {
		return confirmPassword;
	}
	/**
	 * Getter for the avatar chooser.
	 * @return The avatar chooser.
	 */
	public JFileChooser getAvatarChooser() {
		return avatarChooser;
	}
	/**
	 * Getter for the avatar chooser. It will be set on the given folder.
	 * @param directory The folder the file chooser will be set on.
	 * @return The file chooser.
	 */
	public JFileChooser getAvatarChooser(File directory) {
		avatarChooser.setCurrentDirectory(directory);
		return avatarChooser;
	}
	/**
	 * Getter for the instance of this class.
	 * @return The instance.
	 */
	public static RegisterScreen getInstance() {
		return registerScreenInstance;
	}
	/**
	 * This method empties the text and password fields.
	 */
	public void load() {
		nameField.setText("");
		password.setText("");
		confirmPassword.setText("");
		avatarChooser = new JFileChooser();
		setVisible(true);
	}
}
