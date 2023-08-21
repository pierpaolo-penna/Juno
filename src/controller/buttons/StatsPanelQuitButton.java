package controller.buttons;

import java.awt.event.MouseEvent;

import view.window.UnoBackground;
/**
 * This class models the behavior of the "quit" button that appears on the <code>StatsPanel</code>.
 * @author pierp
 *
 */
final class StatsPanelQuitButton extends ButtonWithIconHandler {
	/**
	 * The constructor is protected, according to the Factory pattern.
	 */
	protected StatsPanelQuitButton() {}
	/**
	 * This method loads the <code>HomepagePanel</code>.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		UnoBackground b = UnoBackground.getInstance();
		b.getStatsPanel().setVisible(false);
		b.getHomepagePanel().setVisible(true);
	}
}
