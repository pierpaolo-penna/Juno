package view.components;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.buttons.ButtonsVariations;
import model.player.User;
/**
 * This is the screen visualized after a successful login.
 * This class has been developed according to the singleton pattern.
 * @author pierp
 *
 */
public final class HomepagePanel extends JPanel {
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The only instance of homepage panel.
	 */
	private static HomepagePanel homepagePanelInstance;
	/**
	 * The user that logged in.
	 */
	private static User loggedInPlayer;
	/**
	 * One of the buttons that appear on the screen.
	 */
	private ButtonWithIcon play, stats, quit;
	/**
	 * A simple header for the panel.
	 */
	private JLabel welcome;
	/**
	 * A private constructor, according to the singleton pattern.
	 * @param u The user needed to construct this panel.
	 */
	private HomepagePanel(User u) {
		setOpaque(false);
		setBounds(300, 320, 400, 310);
		setLayout(null);
		
		welcome = new JLabel("Welcome, "+u.getName()+"!", SwingConstants.CENTER);
		welcome.setBounds(75, 30, 250, 40);
		welcome.setFont(new Font("Verdana", 1, 25));
		
		add(welcome);
		
		
		play = new ButtonWithIcon("play.png", "play2.png", false, ButtonsVariations.HOMEPAGE_PANEL_PLAY);
		play.setLocation(25, 100);
		
		add(play);
		
		stats = new ButtonWithIcon("stats.png", "stats2.png", false, ButtonsVariations.HOMEPAGE_PANEL_STATS);
		stats.setLocation(219, 100);
		
		add(stats);
		
		quit = new ButtonWithIcon("quit.png", "quit2.png", false, ButtonsVariations.HOMEPAGE_PANEL_QUIT);
		quit.setLocation(122, 200);
		
		add(quit);
		
		
		setVisible(true);
	}
	/**
	 * Getter for the instance of the panel. It is created with a User.
	 * @param u The user needed to instantiate the panel.
	 * @return The panel.
	 */
	public static HomepagePanel getInstance(User u) {
		if(!u.equals(loggedInPlayer)||homepagePanelInstance == null) {
			loggedInPlayer = u;
			homepagePanelInstance = new HomepagePanel(u);
		}
		return homepagePanelInstance;
	}
}
