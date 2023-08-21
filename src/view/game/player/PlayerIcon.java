package view.game.player;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.player.Player;
/**
 * This class represents a player icon, containing his avatar, his name and his score.
 * @author pierp
 *
 */
final class PlayerIcon extends JPanel {
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The player represented in this class.
	 */
	private Player player;
	/**
	 * The name of the player.
	 */
	private JLabel name;
	/**
	 * The score of the player.
	 */
	private JLabel points;
	/**
	 * The avatar of the player.
	 */
	private AvatarView avatar;
	/**
	 * The constructor for this classs.
	 * @param p The player represented in this class.
	 */
	public PlayerIcon(Player p) {
		player = p;
		
		setLayout(null);
		setSize(180, 90);
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.BLACK, 2));
		
		try {
			avatar = new AvatarView(player.getAvatar());
		} catch (IOException e) {
			e.printStackTrace();
		}
		avatar.setLocation(95, 5);
		
		add(avatar);
		
		name = new JLabel(player.getName(), SwingConstants.CENTER);
		name.setBounds(5, 12, 100, 30);
		name.setFont(new Font("Verdana", 1, 12));
		
		add(name);
		
		points = new JLabel(""+p.getPoints(), SwingConstants.CENTER);
		points.setBounds(5, 52, 100, 30);
		points.setFont(new Font("Verdana", 1, 12));
		
		add(points);
		
		setVisible(true);
	}
	/**
	 * For updating the score.
	 * @param pts The current amount of points.
	 */
	public void setPoints(int pts) {
		points.setText("" + pts);
		repaint();
	}
	/**
	 * This method changes the border of the avatar during the player turn.
	 * A red border means that the player is stopped, a green one means that the player can play.
	 * @param emptyBorder True if the border has to be removed.
	 * @param redBorder True if the border has to be red.
	 */
	protected void setAvatarBorder(boolean emptyBorder, boolean redBorder) {
		if(emptyBorder) avatar.setBorder(new EmptyBorder(2,2,2,2));
		else {
			if(redBorder) avatar.setBorder(new LineBorder(Color.RED, 2));
			else avatar.setBorder(new LineBorder(Color.GREEN, 2));
		}
	}
}
