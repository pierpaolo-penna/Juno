package view.components;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.buttons.ButtonFactory;
import controller.buttons.ButtonWithIconHandler;
import controller.buttons.ButtonsVariations;
/**
 * This class is a JLabel with an icon, and it is used as a button.
 * It has a secondary icon that can be switched thanks to the interface IconSwitcher.
 * @author pierp
 *
 */
public final class ButtonWithIcon extends JLabel implements IconSwitcher{	
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * One of the two different icons of the button.
	 */
	private Icon defaultIcon, alternativeIcon;
	/**
	 * The MouseListener of the button.
	 */
	private ButtonWithIconHandler behavior;
	/**
	 * The builder of the button.
	 * @param filename1 The String containing the file path of one of the two icons.
	 * @param filename2 The String containing the file path of one of the two icons.
	 * @param small A boolean that determines the size of the button.
	 * @param buttonBehavior The name of the MouseListener to be applied to the button. Check {@link ButtonFactory} for more details.
	 */
	public ButtonWithIcon(String filename1, String filename2, boolean small, ButtonsVariations buttonBehavior) {
		setOpaque(false);
		try {
			if(small) {
				if(filename1.equals("browse.png")) {
					defaultIcon = new ImageIcon(ImageIO.read(new File("src/resources/buttons/"+filename1)).getScaledInstance(78, 38, 0));
					alternativeIcon = new ImageIcon(ImageIO.read(new File("src/resources/buttons/"+filename2)).getScaledInstance(78, 38, 0));
				}
				else {
					defaultIcon = new ImageIcon(ImageIO.read(new File("src/resources/buttons/"+filename1)).getScaledInstance(94, 46, 0));
					alternativeIcon = new ImageIcon(ImageIO.read(new File("src/resources/buttons/"+filename2)).getScaledInstance(94, 46, 0));
				}
			}
			else {
				defaultIcon = new ImageIcon(ImageIO.read(new File("src/resources/buttons/"+filename1)));
				alternativeIcon = new ImageIcon(ImageIO.read(new File("src/resources/buttons/"+filename2)));
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		setIcon(defaultIcon);
		if(small) {
			if(filename1.equals("browse.png")) setSize(78,38);
			else setSize(94,46);
		}
		else setSize(156, 76);
		
		behavior = ButtonFactory.newButton(buttonBehavior);
		addMouseListener(behavior);
	}
	/**
	 * Getter for the default icon of the button.
	 * @return The icon.
	 */
	public Icon getDefaultIcon() {
		return defaultIcon;
	}
	/**
	 * Getter for the alternative icon of the button.
	 * @return The icon.
	 */
	public Icon getAlternativeIcon() {
		return alternativeIcon;
	}
	/**
	 * This method switches between the two icons.
	 */
	@Override
	public void switchIcon(Icon i) {
		setIcon(i.equals(defaultIcon)?alternativeIcon:defaultIcon);
	}
}
