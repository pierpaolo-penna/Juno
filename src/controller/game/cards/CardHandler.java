package controller.game.cards;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * This abstract class has been designed to avoid the implementation of the {@link MouseListener} methods for all its subclasses.
 * @author pierp
 *
 */
public abstract class CardHandler implements MouseListener {
	/**
	 * This method doesn't need to be implemented.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}
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
	 * This method doesn't need to be implemented.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}
	/**
	 * This method doesn't need to be implemented.
	 */
	@Override
	public void mouseExited(MouseEvent e) {}
}
