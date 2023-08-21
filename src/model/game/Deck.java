package model.game;

import java.util.Collections;
import java.util.Observable;
import java.util.Stack;

import model.cards.Card;
import model.cards.FullDeck;
import model.player.Player;

/**
 * This class models the deck used for the game.
 * @author pierp
 *
 */
@SuppressWarnings("deprecation")
public final class Deck extends Observable{
	/**
	 * This field stores the actual collection of cards. I chose to use a stack so that only the first card can be interacted with.
	 */
	private Stack<Card> deck;
	/**
	 * The game currently being played.
	 */
	protected Game game;
	/**
	 * The constructor is protected because only {@link Game} can construct a new deck.
	 * The field containing the collection of cards is filled using the class {@link FullDeck}.
	 */
	protected Deck() {
		deck = new Stack<Card>();
		for(Card c: FullDeck.instance.getDeck()) deck.add(c);
		Collections.shuffle(deck);
	}
	/**
	 * This method is called when the deck becomes empty after executing the method "draw" of this class.
	 * It replaces the deck collection with the stack contained in {@link DiscardedCards} and re-shuffles it.
	 * Then, <code>DiscardedCards</code> is rebuilt with the card previously on top of it.
	 */
	private void rebuildDeck() {
		DiscardedCards d = game.getDiscardedCards();
		Card topCard = d.getCards().pop();
		setDeck(d.getCards());
		DiscardedCards newPile = new DiscardedCards(topCard);
		game.setDiscardedCards(newPile);
		setChanged();
		notifyObservers(newPile);
	}
	/**
	 * This method actually replaces the empty deck collection with the {@link DiscardedCards} stack.
	 * @param discardedCards The stack coming from {@link DiscardedCards}.
	 */
	private void setDeck(Stack<Card> discardedCards) {
		deck = new Stack<Card>();
		for(Card c:discardedCards) deck.add(c);
		Collections.shuffle(deck);
	}
	/**
	 * This method distributes the first seven cards to the players before starting the game.
	 * @param players The array containing the players.
	 */
	protected void distributeCards() {
		Player[] players = game.getPlayers();
		for(int i=0; i<7; i++)
			for(Player p: players) draw(p);
	}
	/**
	 * This method allows one player to draw from the deck. If the deck becomes empty it gets rebuilt.
	 * @param p The player drawing the card.
	 */
	public void draw(Player p){
		p.getHand().addCard(pickFirstCard());
		if(deck.isEmpty()) rebuildDeck();
	}
	/**
	 * This method takes the first card from the stack storing the cards.
	 * @return The first card of the deck.
	 */
	protected Card pickFirstCard() {
		return deck.pop();
	}
}
