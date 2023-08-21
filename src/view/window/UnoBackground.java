package view.window;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import view.components.*;
/**
 * This class is a container for all the operations that can be performed before a game starts.
 * It has been developed according to the singleton pattern.
 * @author pierp
 *
 */
public final class UnoBackground extends JPanel {
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The only instance of this class.
	 */
	private static UnoBackground UnoBackgroundInstance = new UnoBackground();
	/**
	 * The image used to paint the background.
	 */
	private Image background;
	/**
	 * The logo that appears on the top half of the panel.
	 */
	private JunoLogo junoLogo = JunoLogo.getInstance();
	/**
	 * The panel that gets loaded when the application starts.
	 */
	private SelectorMenu selectorMenu = SelectorMenu.getInstance();
	/**
	 * The panel containing the login form.
	 */
	private LoginScreen loginScreen = LoginScreen.getInstance();
	/**
	 * The panel containing the registration form.
	 */
	private RegisterScreen registerScreen = RegisterScreen.getInstance();
	/**
	 * The panel that appears after logging in.
	 */
	private HomepagePanel homepagePanel;
	/**
	 * The panel where the player stats are displayed.
	 */
	private StatsPanel statsPanel;
	/**
	 * A private constructor, according to the singleton pattern.
	 */
	private UnoBackground() {
		setLayout(null);
		try {
			background = ImageIO.read(new File("src/resources/Uno.jpg"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		junoLogo.setVisible(true);
		junoLogo.setBounds(358, 35, 283, 251);
		this.add(junoLogo);
		
		selectorMenu.setVisible(true);
		this.add(selectorMenu);
		
		loginScreen.setVisible(false);
		this.add(loginScreen);
		
		registerScreen.setVisible(false);
		this.add(registerScreen);
	}
	/**
	 * The getter of the instance of UnoBackground.
	 * @return The instance.
	 */
	public static UnoBackground getInstance() {
		return UnoBackgroundInstance;
	}
	/**
	 * This method loads the selector menu.
	 */
	public void loadSelectorMenu() {
		for(Component c:getComponents()) if(!c.equals(junoLogo)) c.setVisible(false);
		selectorMenu.setVisible(true);
		repaint();
	}
	/**
	 * This method loads the login screen.
	 */
	public void loadLoginScreen() {
		for(Component c:getComponents()) if(!c.equals(junoLogo)) c.setVisible(false);
		loginScreen.load();
		repaint();
	}
	/**
	 * This method loads the register screen.
	 */
	public void loadRegisterScreen() {
		for(Component c:getComponents()) if(!c.equals(junoLogo)) c.setVisible(false);
		registerScreen.load();
		repaint();
	}
	/**
	 * This method loads the homepage panel, if it has been created.
	 */
	public void loadHomepagePanel() {
		if(homepagePanel == null) return;
		for(Component c:getComponents()) if(!c.equals(junoLogo)) c.setVisible(false);
		homepagePanel.setVisible(true);
		repaint();
	}
	/**
	 * This method loads the stats panel, if it has been created.
	 */
	public void loadStatsPanel() {
		if(statsPanel == null) return;
		for(Component c:getComponents()) if(!c.equals(junoLogo)) c.setVisible(false);
		statsPanel.setVisible(true);
		repaint();
	}
	/**
	 * This method destroys the homepage and stats panels.
	 */
	public void logout() {
		remove(homepagePanel);
		homepagePanel = null;
		if(statsPanel != null) remove(statsPanel);
		statsPanel = null;
	}
	/**
	 * Getter for the homepage panel.
	 * @return The homepage panel.
	 */
	public HomepagePanel getHomepagePanel() {
		return homepagePanel;
	}
	/**
	 * Setter for the homepage panel.
	 * @param p The homepage panel.
	 */
	public void setHomepagePanel(HomepagePanel p) {
		homepagePanel = p;
		this.add(homepagePanel,2);
	}
	/**
	 * Getter for the stats panel.
	 * @return The stats panel.
	 */
	public StatsPanel getStatsPanel() {
		return statsPanel;
	}
	/**
	 * Setter for the stats panel.
	 * @param p The stats panel.
	 */
	public void setStatsPanel(StatsPanel p) {
		statsPanel = p;
		this.add(statsPanel,2);
	}
	/**
	 * This method is used to paint the background.
	 */
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
    }
}
