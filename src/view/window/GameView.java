package view.window;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.game.Game;
import model.player.*;
import view.game.UnoButton;
import view.game.cards.CardOnTableView;
import view.game.player.PlayerGUI;

/**
 * This is the part of the view in which the game is played.
 * This class has been developed according to the singleton pattern. It is also an <code>Observer</code>.
 * @author pierp
 *
 */
@SuppressWarnings("deprecation")
public final class GameView extends JPanel implements Observer{
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The only instance of the game view. 
	 */
	protected static GameView gameViewInstance = new GameView();
	/**
	 * The background image of the game view.
	 */
	private Image background;
	/**
	 * The <code>PlayerGUI</code>s loaded on the screen.
	 */
	private PlayerGUI[] playerGUIs;
	/**
	 * The button that one presses to say uno.
	 */
	private UnoButton uno;
	/**
	 * The visual representation of the deck.
	 */
	private CardOnTableView deck;
	/**
	 * The visual representation of the discarded cards.
	 */
	private CardOnTableView discardedCards;
	/**
	 * The balloon that pops up for saying "uno" or "you didn't say uno"
	 */
	private JLabel balloonPlayer1, balloonPlayer3, balloonPlayer0;
	/**
	 * A private constructor according to the singleton pattern.
	 */
	private GameView() {
		setLayout(null);
		try {
			background = ImageIO.read(new File("src/resources/gameBackground.jpg")).getScaledInstance(1000, 700, 0);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method loads the game view when a game is started. It constructs and positions all the components needed.
	 * @param g The game to be shown on screen.
	 */
	public void loadGame(Game g) {
		Player[] players = g.getPlayers();
		
		playerGUIs = new PlayerGUI[4];
		
		playerGUIs[0] = new PlayerGUI(players[0]);
		playerGUIs[0].setLocation(0, 460);
		add(playerGUIs[0]);
		
		playerGUIs[1] = new PlayerGUI(players[1]);
		playerGUIs[1].setLocation(0, 0);
		add(playerGUIs[1]);
		
		playerGUIs[2] = new PlayerGUI(players[2]);
		playerGUIs[2].setLocation(0, 0);
		add(playerGUIs[2]);
		
		playerGUIs[3] = new PlayerGUI(players[3]);
		playerGUIs[3].setLocation(750, 0);
		add(playerGUIs[3]);
		
		deck = CardOnTableView.getDeck();
		deck.setLocation(330, 250);
		
		add(deck);
		
		discardedCards = CardOnTableView.getDiscardedCards(g);
		discardedCards.setLocation(450, 250);
		
		add(discardedCards);
		
		uno = new UnoButton();
		uno.setLocation(590, 275);
		
		add(uno);
		
		ImageIcon i = null;
		
		try {
			i=new ImageIcon(ImageIO.read(new File("src/resources/Uno!.png")).getScaledInstance(145, 125, 0));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		balloonPlayer0 = new JLabel(i);
		balloonPlayer0.setVisible(false);
		balloonPlayer0.setSize(145, 125);
		balloonPlayer0.setLocation(750, 425);
		
		add(balloonPlayer0);
		
		try {
			i = new ImageIcon(ImageIO.read(new File("src/resources/You didnt say uno left.png")).getScaledInstance(145, 125, 0));
		} catch (IOException e) {
			e.printStackTrace();
		}
		balloonPlayer1 = new JLabel(i);
		balloonPlayer1.setVisible(false);
		balloonPlayer1.setSize(145, 125);
		balloonPlayer1.setLocation(210, 490);
		
		add(balloonPlayer1);
		
		try {
			i = new ImageIcon(ImageIO.read(new File("src/resources/You didnt say uno right.png")).getScaledInstance(145, 125, 0));
		} catch (IOException e) {
			e.printStackTrace();
		}
		balloonPlayer3 = new JLabel(i);
		balloonPlayer3.setVisible(false);
		balloonPlayer3.setSize(145, 125);
		balloonPlayer3.setLocation(650, 0);
		
		add(balloonPlayer3);
		
		repaint();
	}
	/**
	 * This method is pretty self-explanatory.
	 */
	protected void unloadGame() {
		removeAll();
	}
	/**
	 * This method uses the image "background" to paint the game view.
	 */
	@Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
    }
	/**
	 * Getter for the discarded cards.
	 * @return The discarded cards.
	 */
	public CardOnTableView getDiscardedCards() {
		return discardedCards;
	}
	/**
	 * Getter for a specific player GUI.
	 * @param playerCounter The index of the GUI.
	 * @return The GUI.
	 */
	public PlayerGUI getPlayerGUI(int playerCounter) {
		return playerGUIs[playerCounter];
	}
	/**
	 * Getter for the balloon of the User.
	 * @return The balloon.
	 */
	public JLabel getBalloonPlayer0() {
		return balloonPlayer0;
	}
	/**
	 * The update method inherited from {@link Observer}. 
	 * Depending on the class of <code>arg</code>, the view gets updated differently.
	 * The updates can change the playerGUI frame color, start the end-game sequence or make the balloons pop up.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Game) {
			Game g = (Game)o;
			if(arg instanceof Player) {
				Player p = (Player)arg;
				for(int i=0; i<4; i++) {
					if(p.equals(g.getPlayers()[i])) playerGUIs[i].setIconBorder(false, p.isStopped());
					else playerGUIs[i].setIconBorder(true, false);
				}
			}
			else if(arg instanceof Integer) {
				Icon i = null;
				Integer winner = (Integer)arg;
				if(winner.equals(0)) {
					try {
						i = new ImageIcon(ImageIO.read(new File("src/resources/wow.gif")).getScaledInstance(100, 100, 0));
					} catch (IOException e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Congratulations!", "You win!", JOptionPane.INFORMATION_MESSAGE, i);
					JunoWindow.getInstance().switchToGameStats(g);
				}
				else {
					try {
						i = new ImageIcon(ImageIO.read(new File("src/resources/game over.gif")).getScaledInstance(100, 100, 0));
					} catch (IOException e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Better luck next time!", "You lose!", JOptionPane.INFORMATION_MESSAGE, i);
					JunoWindow.getInstance().switchToGameStats(g);
				}
			}
			else if(arg instanceof String) {
				String message = (String)arg;
				if(message.equals("Did say uno")||message.equals("Didnt see it")) {
					if(balloonPlayer0.isVisible()) balloonPlayer0.setVisible(false);
				}
				else if(message.equals("Didnt say uno")) {
					if(g.isClockwise()) {
						balloonPlayer1.setVisible(true);
					}
					else {
						balloonPlayer3.setVisible(true);
					}
				}
				else if(message.equals("Ending my turn")) {
					balloonPlayer1.setVisible(false);
					balloonPlayer3.setVisible(false);
				}
			}
		}
	}
}
