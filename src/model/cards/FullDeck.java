package model.cards;

import java.util.Stack;
import model.game.Deck;
/**
 * This class is the interface between the "cards" package and the {@link Deck} class, that uses this class to be constructed.
 * Check {@link Card} for more details.
 * @author pierp
 *
 */
public final class FullDeck {
	/**
	 * Using the singleton pattern, the class only holds one instance of itself.
	 */
	public static final FullDeck instance = new FullDeck();
	/**
	 * The actual deck used in the game.
	 */
	private final Stack<Card> deck;
	/**
	 * A private constructor, according to the singleton pattern. It builds a deck with all 104 Uno cards.
	 */
	private FullDeck() {
		deck = new Stack<Card>();
		
		for(CardColor c: CardColor.values()) {
			if(c.equals(CardColor.BLACK)) {
				deck.add(new JollyCard());
				deck.add(new JollyCard());
				deck.add(new PlusFourCard());
				deck.add(new PlusFourCard());
			}
			else {
				for(CardNumber n: CardNumber.values()) {
					if(n.equals(CardNumber.ZERO)) deck.add(new NumberedCard(c,n));
					else {
						deck.add(new NumberedCard(c,n));
						deck.add(new NumberedCard(c,n));
					}
				}
				deck.add(new PlusTwoCard(c));
				deck.add(new ReverseCard(c));
				deck.add(new SkipCard(c));
				deck.add(new PlusTwoCard(c));
				deck.add(new ReverseCard(c));
				deck.add(new SkipCard(c));
			}
		}
	}
	/**
	 * Getter for the collection of cards.
	 * @return The collection of cards.
	 */
	public Stack<Card> getDeck(){
		return deck;
	}
}
