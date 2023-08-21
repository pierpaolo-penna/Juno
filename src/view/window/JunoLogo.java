package view.window;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * This class is the logo of the game.
 * It has been developed using the singleton pattern.
 * @author pierp
 *
 */
final class JunoLogo extends JLabel {
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The only instance of this class.
	 */
	private static JunoLogo junoLogoInstance = new JunoLogo();
	/**
	 * A private constructor, according to the singleton pattern.
	 * @param i The icon of this component.
	 */
	private JunoLogo() {
		setVisible(true);
	}
	/**
	 * Getter for the instance of this class. It also adds the icon.
	 * @return The instance.
	 */
	public static JunoLogo getInstance() {
		Image junoIcon;
		try {
			junoIcon = ImageIO.read(new File("src/resources/Uno logo.png"));
			junoLogoInstance.setIcon(new ImageIcon(junoIcon.getScaledInstance(283, 251, 0)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return junoLogoInstance;
	}
}
