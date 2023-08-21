package controller.buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.components.ButtonWithIcon;
import view.game.UnoButton;
/**
 * This abstract class has been designed to avoid the implementation of the {@link MouseListener} methods for all its subclasses.
 * @author pierp
 *
 */
public abstract class ButtonWithIconHandler implements MouseListener {
	/**
	 * This method has been chosen to be abstract to force its implementation in all the subclasses.
	 */
	@Override
	public abstract void mouseClicked(MouseEvent e);
	/**
	 * This method doesn't need to be implemented.
	 */
	@Override
	public void mousePressed(MouseEvent e) {}
	/**
	 * This method doesn't need to be implemented.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}
	/**
	 * This method is responsible for switching the icon of the button when it gets hovered.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() instanceof ButtonWithIcon) {
			ButtonWithIcon b = (ButtonWithIcon)(e.getSource());
			b.switchIcon(b.getIcon());
		}
		else {
			UnoButton b = (UnoButton)(e.getSource());
			b.switchIcon(b.getIcon());
		}
	}
	/**
	 * This method is responsible for switching the icon of the button when it gets hovered.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		mouseEntered(e);
	}
}
