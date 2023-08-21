package view.components;

import javax.swing.Icon;
/**
 * This interface allows for some graphical components with two icons to switch said icons.
 * @author pierp
 *
 */
public interface IconSwitcher {
	/**
	 * This method is used for switching between the two icons.
	 * @param i The currently active icon.
	 */
	public abstract void switchIcon(Icon i);
}
