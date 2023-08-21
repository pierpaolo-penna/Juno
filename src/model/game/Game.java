package model.game;

import java.io.File;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import controller.player.PlayerRecorder;

import java.util.Random;

import model.cards.Card;
import model.cards.CardColor;
import model.cards.UserNeedsToPickColorException;
import model.player.*;
/**
 * This class models a game, with four players, a deck and a pile of discarded cards.
 * It also manages the computer players' turns.
 * @author pierp
 *
 */
@SuppressWarnings("deprecation")
public final class Game extends Observable{
	/**
	 * The deck used during the game.
	 */
	private Deck deck;
	/**
	 * The pile of discarded cards used during the game.
	 */
	private DiscardedCards discardedCards;
	/**
	 * The direction of the game.
	 */
	private boolean clockwise = true;
	/**
	 * The players playing the game.
	 */
	private Player[] players;
	/**
	 * The player playing during the current turn.
	 */
	private Player currentPlayer;
	/**
	 * The winner set at the end of the game
	 */
	private int winner;
	/**
	 * The <code>User</code> playing the game.
	 */
	private User user;
	/**
	 * The constructor sets the players and their interconnection, creates the deck and distributes
	 * its cards among the players, creates the pile of discarded cards, and checks if the cards in
	 * the hand of the user (he is the first one playing) are playable.
	 * @param u The user player starting the game.
	 * @param avatars Three avatars to be assigned to the computer-controlled players.
	 */
	public Game(User u, File[] avatars) {
		if(avatars.length!=3) throw new IllegalArgumentException();
		PlayerRecorder.newGame();
		user = u;
		u.emptyHand();
		u.setIsStopped(false);
		u.setHasSaidUno(false);
		u.resetPoints();
		u.newGamePlayed();
		players = new Player[4];
		currentPlayer = u;
		players[0] = u;
		players[1] = new Player("Player 1", avatars[0], 1);
		players[2] = new Player("Player 2", avatars[1], 2);
		players[3] = new Player("Player 3", avatars[2], 3);
		players[0].setLeftPlayer(players[1]);
		players[0].setRightPlayer(players[3]);
		players[1].setLeftPlayer(players[2]);
		players[1].setRightPlayer(players[0]);
		players[2].setLeftPlayer(players[3]);
		players[2].setRightPlayer(players[1]);
		players[3].setLeftPlayer(players[0]);
		players[3].setRightPlayer(players[2]);
		deck = new Deck();
		deck.game = this;
		deck.distributeCards();
		discardedCards = new DiscardedCards(deck);
		for(int i=0; i<4; i++) {
			players[i].setGame(this);
			discardedCards.addObserver(players[i].getHand());
			players[i].getHand().setCardsPlayable(getTopCard());
		}
	}
	/**
	 * This class models a computer-controlled player's turn.
	 * It can also be called to execute a <code>User</code> turn when he is stopped.
	 * It uses a <code>Timer</code> to run different <code>TimerTasks</code> that divide the turn in different actions.
	 * The first action is calling the method playOrDraw from the class {@link Player}.
	 * The second action is to check if the <code>User</code> has said uno.
	 * The third action is the procedure of selecting the next player.
	 * @param p The player playing the current turn.
	 */
	public void computerPlayerPlayGame(Player p) {
		currentPlayer = p;
		setChanged();
		notifyObservers(p);
		Timer t = new Timer();
		if(p.equals(user)) {
			//These instruction are only executed when the user is stopped
			TimerTask wait = new TimerTask() {
				@Override
				public void run() {}
			};
			t.schedule(wait, 1000);
		}
		else {
			TimerTask play = new TimerTask() {
				@Override
				public void run() {
					p.playOrDraw();
				}
			};
			t.schedule(play, 1500);
			
			TimerTask checkUser = new TimerTask() {
				@Override
				public void run() {
					Random rand = new Random();
					int chance = rand.nextInt(10);
					if(chance <7) {
						//In only 70% of the cases the user gets checked whether he said uno.
						if(!(user).hasSaidUno()) {
							setChanged();
							notifyObservers("Didnt say uno");
							user.draw(deck,2);
							user.setHasSaidUno(true);
						}
						else {
							setChanged();
							notifyObservers("Did say uno");
						}
					}
					else {
						setChanged();
						notifyObservers("Didnt see it");
					}
				}
			};
			t.schedule(checkUser, 2500);
		}
		TimerTask nextTurn = new TimerTask() {
			@Override
			public void run() {
				setChanged();
				notifyObservers("Ending my turn");
				Player nextPlayer = pickNextPlayer(p);
				currentPlayer = nextPlayer;
				if(nextPlayer.equals(user)) {
					//If the next player is the user, we need to check if he is stopped or he can play.
					if(!(user.isStopped())) {
						user.setHasSaidUno(false);
						setChanged();
						notifyObservers(nextPlayer);
					}
					else {
						user.setHasSaidUno(true);
						computerPlayerPlayGame(user);
					}
				}
				else {
					setChanged();
					notifyObservers(nextPlayer);
					p.setIsStopped(false);
					computerPlayerPlayGame(nextPlayer);
				}
			}
		};
		t.schedule(nextTurn, 3000);
	}
	/**
	 * This method starts the end-game procedure
	 * @param playerCounter The index of the player that won the game.
	 */
	public void gameHasEnded(int playerCounter) {
		winner = playerCounter;
		if(playerCounter == 0) {
			int extraPoints = 0;
			for(int i=1;i<4;i++) extraPoints+=players[i].getHand().convertToPoints();
			user.addPoints(extraPoints);
			user.newGameWon();
		}
		if(user.getPoints()>user.getMaxPoints()) user.newMaxPoints(user.getPoints());
		setChanged();
		notifyObservers(playerCounter);
	}
	/**
	 * This method toggles the direction of the game.
	 */
	public void toggleClockwise() {
		this.clockwise = clockwise?false:true;
	}
	/**
	 * True if the game is running clockwise, false if it is running counterclockwise.
	 * @return The corresponging boolean value.
	 */
	public boolean isClockwise() {
		return clockwise;
	}
	/**
	 * Depending on the game direction, the next player is picked looking to the left or right player.
	 * @param p The player in the middle.
	 * @return Either the player on the left or the one on the right.
	 */
	public Player pickNextPlayer(Player p) {
		return clockwise?p.getLeftPlayer():p.getRightPlayer();
	}
	/**
	 * This method models a player throwing a card.
	 * @param c The card thrown.
	 * @param p The player throwing it.
	 * @throws UserNeedsToPickColorException If this method is called by the <code>User</code>, this exception can be triggered.
	 */
	public void playCard(Card c, Player p) throws UserNeedsToPickColorException{
		if(p instanceof User) user.play(c);
		c.effect(p);
		discardedCards.addCard(c, p);
	}
	/**
	 * This method is used for when the <code>User</code> throws a <code>JollyCard</code>
	 * or a <code>PlusFourCard</code>, and it calls the method setColor from {@link DiscardedCards}.
	 * @param card The card thrown.
	 * @param color The color chosen.
	 */
	public void changeColor(Card card, CardColor color) {
		discardedCards.setColor(card, color, user);
	}
	/**
	 * Getter for the top card of the pile of discarded cards.
	 * @return The card on top of the pile of discarded cards.
	 */
	public Card getTopCard() {
		return discardedCards.getTopCard();
	}
	/**
	 * The getter for the deck.
	 * @return The deck.
	 */
	public Deck getDeck() {
		return deck;
	}
	/**
	 * The getter for the pile of discarded cards.
	 * @return The pile of discarded cards.
	 */
	public DiscardedCards getDiscardedCards() {
		return discardedCards;
	}
	/**
	 * The setter of the pile of discarded cards. It's used after a deck is rebuilt.
	 * @param d The new pile of discarded cards.
	 */
	protected void setDiscardedCards(DiscardedCards d) {
		discardedCards = d;
	}
	/**
	 * Getter for the players array.
	 * @return The players array.
	 */
	public Player[] getPlayers() {
		return players;
	}
	/**
	 * Getter for the current player.
	 * @return The current player.
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	/**
	 * Getter for the winner of the game.
	 * @return The index of the winner.
	 */
	public int getWinner() {
		return winner;
	}
}
