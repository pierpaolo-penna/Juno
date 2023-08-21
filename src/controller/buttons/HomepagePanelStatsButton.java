package controller.buttons;

import java.awt.event.MouseEvent;

import view.window.UnoBackground;
import view.components.HomepagePanel;
/**
 * This class models the behavior of the "stats" button that appears on the <code>HomepagePanel</code>.
 * Check {@link HomepagePanel} for more details.
 * @author pierp
 *
 */
final class HomepagePanelStatsButton extends ButtonWithIconHandler {
	/**
	 * The constructor is protected, according to the Factory pattern.
	 */
	protected HomepagePanelStatsButton() {}
	/**
	 * This method opens the <code>StatsPanel</code>.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		UnoBackground b = UnoBackground.getInstance();
		b.loadStatsPanel();
	}
}
