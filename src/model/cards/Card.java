package model.cards;

import model.player.Player;
import model.player.PlayerHand;

/**
 * This is the root class for all the cards created within the game.
 * This package has been designed so that its public interface is minimal.
 * The package interfaces with the exterior only through the class {@link FullDeck} and some public utility methods.
 * @author pierp
 *
 */
public abstract class Card {
	/**
	 * The color that the card can have, picked from the enum {@link CardColor}.
	 */
	protected CardColor color;
	/**
	 * This field indicates the amount of points that a card provides when played or when counted at the end of the game.
	 */
	protected final int points;
	/**
	 * This field indicates whether a card can be played or not, depending on the card sitting on the table.
	 */
	protected boolean playable = false;
	
	/**
	 * The constructor is protected so that it can only be called by other <code>Card</code> subclasses or from within the package.
	 * All the subclasses also have a protected constructor for the same reason.
	 * @param color The color assigned to the card.
	 * @param points The amount of points assigned to the card.
	 */
	protected Card(CardColor color, int points) {
		this.color = color;
		this.points=points;
	}
	/**
	 * This method is called whenever there's need to determine whether one instance of
	 * <code>Card</code> can be played, confronting it with the one given as input.
	 * Check {@link PlayerHand} for further details.
	 * @param c The card to be confronted with this one.
	 */
	public void checkIfPlayable(Card c) {
		if(getColor().equals(c.getColor())) {
			playable = true;
			return;
		}
		if(getColor().equals(CardColor.BLACK)) {
			playable = true;
			return;
		}
		if(this instanceof NumberedCard && c instanceof NumberedCard) {
			if(((NumberedCard)this).getNumber().equals(((NumberedCard)c).getNumber())) {
				playable = true;
				return;
			}
			else {
				playable = false;
				return;
			}
		}
		if(this instanceof PlusTwoCard && c instanceof PlusTwoCard) {
			playable = true;
			return;
		}
		if(this instanceof ReverseCard && c instanceof ReverseCard) {
			playable = true;
			return;
		}
		if(this instanceof SkipCard && c instanceof SkipCard) {
			playable = true;
			return;
		}
		playable = false;
	}
	/**
	 * Getter for the boolean value playable.
	 * @return If the card is playable.
	 */
	public boolean isPlayable() {
		return playable;
	}
	/**
	 * Every time a {@link Card} gets played, it has an effect. It is abstract so that all the subclasses will have to implement it.
	 * @param p the player playing the card.
	 * @throws UserNeedsToPickColorException Thrown when the user player plays a Jolly or Plus Four card.
	 */
	public abstract void effect(Player p) throws UserNeedsToPickColorException;
	/**
	 * All the cards have a similar toString method so that the final result comes out as color + card type/number.
	 */
	public String toString() {
		return this.color.toString();
	}
	/**
	 * Getter for the color field.
	 * @return The color of the card.
	 */
	public CardColor getColor() {
		return this.color;
	}
	/**
	 * Getter for the points of the card.
	 * @return The amount of points this card has.
	 */
	public int getPoints() {
		return this.points;
	}
}
