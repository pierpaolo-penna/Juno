package view.game;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.game.UnoButtonClicked;
import view.components.IconSwitcher;
/**
 * This class is a JLabel with an icon, and it is used as the button to press and say uno.
 * It has a secondary icon that can be switched thanks to the interface IconSwitcher.
 * @author pierp
 *
 */
public final class UnoButton extends JLabel implements IconSwitcher {
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * One of the two icons of the button.
	 */
	private Icon defaultIcon, alternativeIcon;
	/**
	 * The <code>MouseListener</code> of this class.
	 */
	private UnoButtonClicked buttonListener = new UnoButtonClicked();
	/**
	 * The constructor for this class. 
	 */
	public UnoButton() {
		setSize(101,101);
		setOpaque(false);
		
		try {
			defaultIcon = new ImageIcon(ImageIO.read(new File("src/resources/buttons/uno.png")));
			alternativeIcon = new ImageIcon(ImageIO.read(new File("src/resources/buttons/uno2.png")));
		}catch(IOException e) {
			e.printStackTrace();
		}
		setIcon(defaultIcon);
		this.addMouseListener(buttonListener);
	}
	/**
	 * Getter for the default icon.
	 * @return The default icon.
	 */
	public Icon getDefaultIcon() {
		return defaultIcon;
	}
	/**
	 * Getter for the alternative icon.
	 * @return The alternative icon.
	 */
	public Icon getAlternativeIcon() {
		return alternativeIcon;
	}
	/**
	 * This method switches between the two icons.
	 */
	public void switchIcon(Icon i) {
		setIcon(i.equals(defaultIcon)?alternativeIcon:defaultIcon);
	}
}
