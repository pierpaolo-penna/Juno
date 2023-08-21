package controller.game.cards;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import controller.GameController;
import model.cards.Card;
import model.cards.CardColor;
import model.cards.JollyCard;
import model.cards.PlusFourCard;
import model.game.Game;
import model.player.User;
/**
 * This class has two static methods that are used after the <code>User</code> plays a
 * <code>JollyCard</code> or a <code>PlusFourCard</code>, and has to choose the new color.
 * @author pierp
 *
 */
public final class ColorPicker {
	/**
	 * This method opens a <code>JOptionPane</code> for selecting the new color.
	 * @param c The card played by the <code>User</code>.
	 */
	public static void chooseColor(Card c) {
		String[] options = {"red","green","yellow","blue"};
		String card = null;
		Icon i = null;
		
		if(c instanceof PlusFourCard) card = "black plus four";
		else if(c instanceof JollyCard) card = "black jolly";
		try {
			i = new ImageIcon(ImageIO.read(new File("src/resources/cards/"+card+".png")));
		} catch(IOException e) {
			e.printStackTrace();
		}
		int color = JOptionPane.showOptionDialog(null, "Choose color,\n default red", "Choose color", 0, 3, i, options, null);
		setCardColor(c, color);
	}
	/**
	 * This method is called by <code>chooseColor</code> to communicate
	 * to the model the color that was picked by the <code>User</code>.
	 * @param card The card originally played by the <code>User</code>.
	 * @param chosenColor The integer corresponding to the color chosen by the <code>User</code>.
	 */
	private static void setCardColor(Card card, int chosenColor) {
		GameController gc = GameController.getInstance();
		User u = User.getLoggedInUser();
		Game g = gc.getGame();
		CardColor color = null;
		if(chosenColor<1) color = CardColor.RED; //The JOptionPane.showOptionDialog method returns -1 if no option is chosen, thus the need for chosenColor<1
		else if(chosenColor==1) color = CardColor.GREEN;
		else if(chosenColor==2) color = CardColor.YELLOW;
		else if(chosenColor==3) color = CardColor.BLUE;
		
		g.changeColor(card, color);
		g.computerPlayerPlayGame(g.pickNextPlayer(u));
	}
}
