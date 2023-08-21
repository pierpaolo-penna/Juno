package model.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import model.cards.Card;
import model.cards.CardColor;
import model.cards.PlusFourCard;
import model.game.DiscardedCards;
/**
 * This class models the player's hand. This class is an observer of {@link DiscardedCards}.
 * @author pierp
 *
 */
@SuppressWarnings("deprecation")
public final class PlayerHand implements Observer{
	/**
	 * The collection of cards.
	 */
	private List<Card> hand;
	/**
	 * Protected constructor so that only a <code>Player</code> can construct a new hand.
	 */
	protected PlayerHand() {
		hand = new ArrayList<Card>();
	}
	/**
	 * This method adds a new card in the player's hand.
	 * @param c The new card.
	 */
	public void addCard(Card c) {
		hand.add(c);
	}
	/**
	 * This method removes a card from the player's hand.
	 * @param c The card to be removed.
	 */
	public void removeCard(Card c) {
		hand.remove(c);
	}
	/**
	 * This method goes through the cards in the hand of the player and checks if they are playable.
	 * @param topCard The card on top of the discarded pile, used to compare the other cards.
	 */
	public void setCardsPlayable(Card topCard) {
		int sameColorCounter = 0;
		for(Card c:hand) {
			if(c.getColor().equals(topCard.getColor())) sameColorCounter++;
			c.checkIfPlayable(topCard);
		}
		//Plus four cards can only be played if the hand does not contain cards of the same color as the one on the ground
		if(sameColorCounter>0) 
			for(Card c:hand) 
				if(c instanceof PlusFourCard) 
					((PlusFourCard)c).setPlayable(false);
	}
	/**
	 * This method will create a list out of the playable cards.
	 * @return The list.
	 */
	public List<Card> findEligibleCards() {
		List<Card> eligibleCards = hand.stream().filter(Card::isPlayable).toList();
		return eligibleCards;
	}
	/**
	 * This method allows the computer-controlled player to choose a new color
	 * after he throws a <code>JollyCard</code> or a <code>PlusFourCard</code>.
	 * @return The color picked.
	 */
	public CardColor pickNewColor() {
		Map<CardColor, List<Card>> colorOccurrences = (hand.stream().collect(Collectors.groupingBy(Card::getColor)));
		int max = 0, maxCounter = 0;
		for(CardColor c:colorOccurrences.keySet()) {
			int colorCounter = colorOccurrences.get(c).size();
			if(colorCounter>max) {
				maxCounter = c.getValue();
				max = colorCounter;
			}
		}
		if(maxCounter == 4) return CardColor.values()[0];
		return CardColor.values()[maxCounter];
	}
	/**
	 * Getter for the collection of cards.
	 * @return The collection of cards.
	 */
	public List<Card> getHand(){
		return hand;
	}
	/**
	 * Getter for the number of cards in the hand.
	 * @return The number of cards in the hand.
	 */
	public int getSize() {
		return hand.size();
	}
	/**
	 * Getter to see if the hand has no more cards.
	 * @return The corresponding boolean value.
	 */
	public boolean isEmpty() {
		return hand.isEmpty();
	}
	/**
	 * This method converts all the cards in the hand of a player into points, after a game ends.
	 * @return The points.
	 */
	public int convertToPoints() {
		int points = hand.stream().mapToInt(Card::getPoints).sum();
		return points;
	}
	/**
	 * This method calls the setCardsPlayable method every time a new card is added on {@link DiscardedCards}.
	 */
	@Override
	public void update(Observable o, Object arg) {
		setCardsPlayable((Card)arg);
	}
}
