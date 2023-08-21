package view.game.player;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
/**
 * This class creates an image for the avatar of a player.
 * @author pierp
 *
 */
final class AvatarView extends JLabel {
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The constructor of the class.
	 * @param avatar The file containing the avatar.
	 * @throws IOException Thrown when the file is not found.
	 */
	protected AvatarView(File avatar) throws IOException {
		super(new ImageIcon(ImageIO.read(avatar).getScaledInstance(76, 76, 0)));
		
		setSize(80,80);
		setBorder(new EmptyBorder(2, 2, 2, 2));
		setBackground(Color.BLACK);
		setOpaque(true);
	}
}
