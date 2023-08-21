package view.components;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

import controller.player.PlayerRecorder;
/**
 * This class opens a new <code>JFrame</code> containing the form for changing the password.
 * @author pierp
 *
 */
public final class ChangePasswordForm extends JFrame {
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * One of the labels of the form.
	 */
	private JLabel oldPasswordLabel, newPasswordLabel, confirmPasswordLabel;
	/**
	 * One of the password fields of the form.
	 */
	private JPasswordField oldPassword, newPassword, confirmPassword;
	/**
	 * The builder of this class. The new instance is immediately open.
	 * It contains a simple form and two buttons, one for closing and one for submitting.
	 */
	public ChangePasswordForm() {
		super("Change password");
		setLayout(null);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		Font verdana = new Font("Verdana", 1, 15);
		
		oldPasswordLabel = new JLabel("Old password: ");
		oldPasswordLabel.setBounds(150, 50, 150, 30);
		oldPasswordLabel.setFont(verdana);
		
		add(oldPasswordLabel);
		
		newPasswordLabel = new JLabel("New password: ");
		newPasswordLabel.setBounds(150, 100, 150, 30);
		newPasswordLabel.setFont(verdana);
		
		add(newPasswordLabel);
		
		confirmPasswordLabel = new JLabel("New password: ");
		confirmPasswordLabel.setBounds(150, 150, 150, 30);
		confirmPasswordLabel.setFont(verdana);
		
		add(confirmPasswordLabel);
		
		oldPassword = new JPasswordField(20);
		oldPassword.setBounds(300, 50, 200, 30);
		
		add(oldPassword);
		
		newPassword = new JPasswordField(20);
		newPassword.setBounds(300, 100, 200, 30);
		
		add(newPassword);
		
		confirmPassword = new JPasswordField(20);
		confirmPassword.setBounds(300, 150, 200, 30);
		
		add(confirmPassword);
		
		JButton quit = new JButton("Quit");
		quit.setFont(verdana);
		quit.setBounds(125, 225, 150, 50);
		
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		add(quit);
		
		JButton edit = new JButton("Edit");
		edit.setBounds(325, 225, 150, 50);
		edit.setFont(verdana);
		
		edit.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if(PlayerRecorder.changePassword(oldPassword.getText(), newPassword.getText(), confirmPassword.getText())) dispose();
			}
		});
		
		add(edit);
		
		repaint();
	}
}
