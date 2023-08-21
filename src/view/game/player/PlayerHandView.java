package view.game.player;

import java.util.*;

import javax.swing.JLayeredPane;

import model.cards.Card;
import model.player.*;
import view.game.cards.CardInHandView;
import view.game.cards.EmptyCard;
/**
 * This class displays a player hand.
 * @author pierp
 *
 */
final class PlayerHandView extends JLayeredPane {
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The constructor for this class.
	 * @param p The player whose hand is displayed.
	 */
	protected PlayerHandView(Player p) {
		int playerCounter = p.getPlayerCounter();
		setLayout(null);
		if(playerCounter == 0 || playerCounter == 2) setSize(550, 160);
		else setSize(160, 400);
		
		List<Card> hand = p.getHand().getHand();
		CardInHandView c;
		for(int i=0; i<hand.size(); i++) {
			if(p instanceof User) {
				c = new CardInHandView(hand.get(i), playerCounter);
				c.setLocation(460-i*17, 34);
				add(c);
			}
			else {
				c = new CardInHandView(EmptyCard.instance, playerCounter);
				if(playerCounter == 1) {
					c.setLocation(27, 315-i*17);
					add(c);
				}
				else if(playerCounter == 2) {
					c.setLocation(20+i*17, 14);
					add(c);
				}
				else if(playerCounter == 3) {
					c.setLocation(27, 20+i*17);
					add(c);
				}
				else {}
			}
		}
	}
}
