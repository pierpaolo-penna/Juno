package view.components;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.buttons.ButtonsVariations;
import model.player.User;
/**
 * This class displays all the player stats.
 * @author pierp
 *
 */
public final class StatsPanel extends JPanel {
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * One of the buttons appearing on this panel.
	 */
	private ButtonWithIcon quit, editAvatar, editPassword;
	/**
	 * The file chooser used to change the avatar.
	 */
	private JFileChooser avatarChooser = new JFileChooser();
	/**
	 * One of the labels that appear on the panel.
	 */
	private JLabel gamesPlayed, gamesWon, maxScore, editPasswordLabel, username;
	/**
	 * The JLabel displaying the user's avatar.
	 */
	private JLabel avatar;
	/**
	 * The constructor for the class.
	 * @param u The user needed to gather the informations.
	 */
	public StatsPanel(User u) {
		setOpaque(true);
		setBackground(Color.LIGHT_GRAY);
		setBounds(200, 300, 600, 350);
		setBorder(new LineBorder(Color.BLACK, 2));
		setLayout(null);
		
		Font verdana = new Font("Verdana", 1, 17);
		
		username = new JLabel("Username: "+u.getName(), SwingConstants.CENTER);
		username.setBounds(30, 20, 250, 30);
		username.setFont(verdana);
		
		add(username);
		
		gamesPlayed = new JLabel("Games played: "+u.getGamesPlayed(), SwingConstants.CENTER);
		gamesPlayed.setBounds(30, 60, 250, 30);
		gamesPlayed.setFont(verdana);
		
		add(gamesPlayed);
		
		gamesWon = new JLabel("Games won: "+u.getGamesWon(), SwingConstants.CENTER);
		gamesWon.setBounds(30, 100, 250, 30);
		gamesWon.setFont(verdana);
		
		add(gamesWon);
		
		maxScore = new JLabel("Max score: "+u.getMaxPoints(), SwingConstants.CENTER);
		maxScore.setBounds(30, 140, 250, 30);
		maxScore.setFont(verdana);
		
		add(maxScore);
		
		editPasswordLabel = new JLabel("Edit password?", SwingConstants.LEFT);
		editPasswordLabel.setBounds(50, 200, 250, 30);
		editPasswordLabel.setFont(verdana);
		
		add(editPasswordLabel);
		
		try {
			avatar = new JLabel(new ImageIcon(ImageIO.read(u.getAvatar()).getScaledInstance(150, 150, 0)));
			avatar.setOpaque(true);
			avatar.setBackground(new Color(225, 225, 225));
			avatar.setBorder(new LineBorder(Color.BLACK, 1));
			avatar.setBounds(420, 29, 150, 150);
			add(avatar);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		quit = new ButtonWithIcon("quit.png", "quit2.png", false, ButtonsVariations.STATS_PANEL_QUIT);
		quit.setLocation(222, 250);
		
		add(quit);
		
		editAvatar = new ButtonWithIcon("edit.png", "edit2.png", true, ButtonsVariations.STATS_PANEL_EDIT_AVATAR);
		editAvatar.setLocation(448, 200);
		
		add(editAvatar);
		
		editPassword = new ButtonWithIcon("edit.png", "edit2.png", true, ButtonsVariations.STATS_PANEL_EDIT_PASSWORD);
		editPassword.setLocation(220, 192);
		
		add(editPassword);
		
		setVisible(true);
	}
	/**
	 * Getter for the avatar label.
	 * @return The avatar label.
	 */
	public JLabel getAvatar() {
		return avatar;
	}
	/**
	 * Getter for the avatar file chooser
	 * @return The avatar file chooser.
	 */
	public JFileChooser getAvatarChooser() {
		return avatarChooser;
	}
	/**
	 * The content of this panel gets updated after a game ends.
	 */
	public void update() {
		User u = User.getLoggedInUser();
		gamesPlayed.setText("Games played: "+u.getGamesPlayed());
		gamesWon.setText("Games won: "+u.getGamesWon());
		maxScore.setText("Max score: "+u.getMaxPoints());
	}
}
