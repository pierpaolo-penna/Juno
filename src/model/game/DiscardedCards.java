package model.game;

import java.util.Observable;
import java.util.Stack;

import model.cards.Card;
import model.cards.CardColor;
import model.cards.JollyCard;
import model.cards.NumberedCard;
import model.cards.PlusFourCard;
import model.player.Player;
import model.player.User;
/**
 * This class models the pile of cards discarded throughout the game by the different players.
 * @author pierp
 *
 */
@SuppressWarnings("deprecation")
public final class DiscardedCards extends Observable{
	/**
	 * This field stores the actual collection of cards. I chose to use a stack so that only the first card can be interacted with.
	 */
	private Stack<Card> cards;
	/**
	 * The constructor  is protected because only {@link Game} can construct a new DiscardedCards. The field containing the
	 * collection of cards is filled with the top cards from the deck until a {@link NumberedCard} is met.
	 * This constructor is used only for the first instantiation of this class.
	 * @param d The {@link Deck} needed to pick the first card (eventually more than one card is picked).
	 */
	protected DiscardedCards(Deck d) {
		cards = new Stack<Card>();
		do {
			cards.add(d.pickFirstCard());
		}while(!(cards.peek() instanceof NumberedCard));
	}
	/**
	 * The constructor is protected because only {@link Game} can construct a new DiscardedCards. 
	 * This constructor is used every time the deck is rebuilt.
	 * @param c The card to be put on top of the pile.
	 */
	protected DiscardedCards(Card c) {
		cards = new Stack<Card>();
		cards.add(c);
	}
	/**
	 * Getter for the card on top of the stack.
	 * @return The card on top of the stack.
	 */
	protected Card getTopCard() {
		return cards.peek();
	}
	/**
	 * Getter for the stack of cards.
	 * @return The stack of cards.
	 */
	protected Stack<Card> getCards(){
		return cards;
	}
	/**
	 * This method adds a new card on top of the existing pile and it gets called when
	 * a player throws a card from his hand. The points of the card get added to the player's total,
	 * and the card played is put on top of the stack storing the discarded cards. Then the observers are notified.
	 * @param discardedCard The card played.
	 * @param p The player throwing the card.
	 */
	protected void addCard(Card discardedCard, Player p) {
		p.addPoints(discardedCard.getPoints());
		cards.push(discardedCard);
		setChanged();
		notifyObservers(discardedCard);
	}
	/**
	 * This method is used for when the <code>User</code> throws a <code>JollyCard</code>
	 * or a <code>PlusFourCard</code>, and associated the chosen color with the card.
	 * @param c The card thrown.
	 * @param color The color chosen by the user.
	 * @param u The user.
	 */
	protected void setColor(Card c, CardColor color, User u) {
		if(c instanceof JollyCard) {
			((JollyCard)c).setColor(color);
			addCard(c, u);
			setChanged();
			notifyObservers(c);
		}
		else if(c instanceof PlusFourCard) {
			((PlusFourCard)c).setColor(color);
			addCard(c, u);
			setChanged();
			notifyObservers(c);
		}
		else return;
	}
}
