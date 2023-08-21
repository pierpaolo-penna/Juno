package model.player;

import java.io.File;
import java.util.*;

import model.cards.Card;
import model.cards.UserNeedsToPickColorException;
import model.game.Deck;
import model.game.Game;
/**
 * This class models a player.
 * @author pierp
 *
 */
@SuppressWarnings("deprecation")
public class Player extends Observable{
	/**
	 * The name that the player has.
	 */
	protected String name;
	/**
	 * The cards held in hand.
	 */
	protected PlayerHand hand;
	/**
	 * The players to the left and to the right of this one.
	 */
	protected Player leftPlayer, rightPlayer;
	/**
	 * The information whether or not this player can play this turn.
	 */
	protected boolean isStopped;
	/**
	 * The amount of points the player has during a game.
	 */
	protected int points;
	/**
	 * The avatar of the player.
	 */
	protected File avatar;
	/**
	 * The game he is playing.
	 */
	protected Game game;
	/**
	 * The counter for the player.
	 */
	protected int playerCounter;
	
	/**
	 * The constructor creates a new player with an empty hand.
	 * @param name The name of the player.
	 * @param avatar The avatar of the player.
	 * @param playerCounter The index of the player.
	 */
	public Player(String name, File avatar, int playerCounter) {
		this.name = name;
		this.avatar = avatar;
		this.playerCounter = playerCounter;
		hand = new PlayerHand();
	}
	/**
	 * This method models the turn of the player. Depending on the card on top of the discarded pile,
	 * all the hand of the player gets filtered to create the eligibleCards list,
	 * out of which one random card gets picked. If said list is empty, the player must draw a card.
	 * The the observers are notified.
	 */
	public void playOrDraw(){
		if(!isStopped) {
			hand.setCardsPlayable(game.getTopCard());
			List<Card> eligibleCards = hand.findEligibleCards();
			if(eligibleCards.isEmpty()) {
				draw(game.getDeck(),1);
			}
			else {
				Random r = new Random();
				int rand = r.nextInt(eligibleCards.size());
				Card c = eligibleCards.get(rand);
				hand.removeCard(c);
				try {
					game.playCard(c, this);
				} catch (UserNeedsToPickColorException e) {
					//This exception never occurs during a non-user turn
				}
				setChanged();
				notifyObservers();
			}
		}
		else {
			this.isStopped = false;
			setChanged();
			notifyObservers();
		}
		if(hand.isEmpty()) {
			game.gameHasEnded(playerCounter);
		}
	}
	/**
	 * This method models the action of drawing from the deck.
	 * @param d The deck to be drawn from.
	 * @param amount The amount of cards to be drawn.
	 */
	public void draw(Deck d, int amount) {
		for(int i=0;i<amount;i++) d.draw(this);
		setChanged();
		notifyObservers();
	}
	/**
	 * Setter for the player to the left.
	 * @param p The player to the left.
	 */
	public void setLeftPlayer(Player p) {
		leftPlayer = p;
	}
	/**
	 * Setter for the player to the right.
	 * @param p The player to the right.
	 */
	public void setRightPlayer(Player p) {
		rightPlayer = p;
	}
	/**
	 * Getter for the player to the left.
	 * @return The player to the left.
	 */
	public Player getLeftPlayer() {
		return leftPlayer;
	}
	/**
	 * Getter for the player to the left.
	 * @return The player to the left.
	 */
	public Player getRightPlayer() {
		return rightPlayer;
	}
	/**
	 * Setter to stop/un-stop the player.
	 * @param tf The corresponding boolean value.
	 */
	public void setIsStopped(boolean tf) {
		isStopped = tf;
	}
	/**
	 * Getter to see if the player is stopped.
	 * @return The corresponding boolean value.
	 */
	public boolean isStopped() {
		return isStopped;
	}
	/**
	 * Getter for the name of the player.
	 * @return The name of the player.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Getter for the player hand.
	 * @return The player hand.
	 */
	public PlayerHand getHand() {
		return hand;
	}
	/**
	 * Getter for the player points.
	 * @return The player points.
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * This method adds the points to the player score.
	 * @param points The amount to be added.
	 */
	public void addPoints(int points) {
		this.points += points;
		setChanged();
		notifyObservers();
	}
	/**
	 * Getter for the player avatar.
	 * @return The player avatar.
	 */
	public File getAvatar() {
		return avatar;
	}
	/**
	 * Setter for the game being played.
	 * @param game The game.
	 */
	public void setGame(Game game) {
		this.game = game;
	}
	/**
	 * Getter for the player counter.
	 * @return The player counter.
	 */
	public int getPlayerCounter() {
		return playerCounter;
	}
}
