package view.game.player;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.player.Player;
import view.window.GameView;
/**
 * This class represents a player GUI, with his hand and his icon.
 * This class is an <code>Observer</code>.
 * @author pierp
 *
 */
@SuppressWarnings("deprecation")
public final class PlayerGUI extends JPanel implements Observer{
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The hand of the player.
	 */
	private PlayerHandView hand;
	/**
	 * The icon containing the informations of the player.
	 */
	private PlayerIcon icon;
	/**
	 * The constructor of this class.
	 * @param p The player whose GUI is being constructed.
	 */
	public PlayerGUI(Player p) {
		super(null);
		int playerCounter = p.getPlayerCounter();
		if(playerCounter==0||playerCounter==2) setSize(995, 200);
		else setSize(250, 655);
		setOpaque(false);
		
		icon = new PlayerIcon(p);
		hand = new PlayerHandView(p);
		setHandLocation(playerCounter);
		
		if(playerCounter == 0) {
			icon.setLocation(780, 75);
			icon.setAvatarBorder(false, false);
		}
		else if(playerCounter == 1) {
			icon.setLocation(35, 530);
		}
		else if(playerCounter == 2) {
			icon.setLocation(35, 35);
		}
		else if(playerCounter == 3) {
			icon.setLocation(35, 35);
		}
		add(icon);
		add(hand);
	}
	/**
	 * This method is used to reposition the player hand when it is constructed and after it gets updated.
	 * @param playerCounter The index of the player.
	 */
	private void setHandLocation(int playerCounter) {
		if(playerCounter == 0) {
			hand.setLocation(220, 30);
		}
		else if(playerCounter == 1) {
			hand.setLocation(45, 127);
		}
		else if(playerCounter == 2) {
			hand.setLocation(220, 15);
		}
		else if(playerCounter == 3) {
			hand.setLocation(45, 127);
		}
	}
	/**
	 * The update method inherited from {@link Observer}. 
	 * Depending on <code>arg</code>, the view gets updated differently.
	 * If arg is null the hand gets updated, if arg is Boolean the User balloon becomes visible.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Player) {
			if(arg == null) {
				Player p = (Player)o;
				remove(hand);
				hand = new PlayerHandView(p);
				setHandLocation(p.getPlayerCounter());
				add(hand);
				icon.setPoints(p.getPoints());
				repaint();
			}
			else if(arg instanceof Boolean) {
				((GameView)getParent()).getBalloonPlayer0().setVisible(true);
			}
		}
	}
	/**
	 * Getter for the icon.
	 * @return The icon.
	 */
	public PlayerIcon getIcon() {
		return icon;
	}
	/**
	 * This method repaints the icon border.
	 * @param empty True if no border is needed
	 * @param red True if the border has to be red, false if it has to be green.
	 */
	public void setIconBorder(boolean empty, boolean red) {
		icon.setAvatarBorder(empty, red);
	}
}
