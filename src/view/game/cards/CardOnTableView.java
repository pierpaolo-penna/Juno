package view.game.cards;

import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.cards.Card;
import model.game.Game;
/**
 * This class represents a card sitting on the table. It can either represent the deck, or the discarded cards pile.
 * It has been designed according to the singleton pattern, and it creates two distinct instances, one for the deck and one for the discarded cards.
 * This class is an <code>Observer</code>.
 * @author pierp
 *
 */
@SuppressWarnings("deprecation")
public final class CardOnTableView extends JPanel implements Observer{
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The instance of the deck.
	 */
	private static CardOnTableView deck;
	/**
	 * The instance of the discarded cards pile.
	 */
	private static CardOnTableView discardedCards;
	/**
	 * The game currently playing.
	 */
	private static Game currentGame;
	/**
	 * The card displayed on screen.
	 */
	private CardInHandView topCard;
	/**
	 * True for the deck, false for the discarded cards pile.
	 */
	private boolean isDeck;
	/**
	 * Private constructor, according to the singleton pattern.
	 * @param g The game to take the top card from, or null for the deck.
	 * @param isDeck True for the deck, false for the discarded cards pile.
	 */
	private CardOnTableView(Game g, boolean isDeck) {
		setSize(105, 155);
		setOpaque(false);
		
		this.isDeck = isDeck;
		
		if(isDeck) {
			topCard = new CardInHandView(EmptyCard.instance);
			topCard.setLocation(0, 10);
			add(topCard);
		}
		else {
			topCard = new CardInHandView(g.getTopCard());
			topCard.setLocation(0, 10);
			add(topCard);
		}
		
		setVisible(true);
	}
	/**
	 * Getter for the deck.
	 * @return The deck.
	 */
	public static CardOnTableView getDeck() {
		if(deck == null) deck = new CardOnTableView(null, true);
		return deck;
	}
	/**
	 * Getter for the discarded cards pile. 
	 * It checks if the input game is the same one stored in the static field, and creates the discarded cards pile instance accordingly.
	 * @param g The game to take the top card from.
	 * @return The discarded cards pile.
	 */
	public static CardOnTableView getDiscardedCards(Game g) {
		if(!g.equals(currentGame) || discardedCards == null) {
			currentGame = g;
			discardedCards = new CardOnTableView(g, false);
		}
		return discardedCards;
	}
	/**
	 * Updates the top card and repaints.
	 * @param c The card to be repainted.
	 */
	private void cardPlayed(Card c) {
		if(!isDeck) {
			remove(topCard);
			topCard = new CardInHandView(c);
			topCard.setSize(100, 150);
			for(MouseListener l: topCard.getMouseListeners()) {
				topCard.removeMouseListener(l);
			}
			add(topCard);
			repaint();
		}
	}
	/**
	 * The update method inherited from {@link Observer}. 
	 * It receives a card as an input and calls the method cardPlayed.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(!isDeck) {
			Card c = (Card)arg;
			cardPlayed(c);
		}
	}
}
