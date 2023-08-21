package model.player;

import java.io.*;

import controller.player.LoginException;
import controller.player.LoginFailedException;
import controller.player.LogoutException;
import controller.player.PlayerRecorder;
import controller.player.UserNotFoundException;
import model.cards.Card;
import model.game.Game;
/**
 * This class models a player controlled by the user. It extends the regular player.
 * @author pierp
 *
 */
@SuppressWarnings("deprecation")
public final class User extends Player {
	/**
	 * This field indicates whether or not the user is currently logged in.
	 */
	private static boolean isLoggedIn = false;
	/**
	 * This field holds the information about the current logged in player.
	 */
	private static User loggedInUser;
	/**
	 * This field is pretty self-explanatory, and its purpose is holding the corresponding stat about the player.
	 */
	private int gamesPlayed, gamesWon, maxPoints;
	/**
	 * This field is switched every time the user player has said uno.
	 */
	private boolean hasSaidUno = false;
	/**
	 * This is the constructor of the User. It is private so that a new UserPlayer can only be constructed after a succesful login.
	 * @param name The name of the player, to be passed to the superconstructor.
	 * @param avatar The avatar of the player, to be passed to the superconstructor.
	 * @param gamesPlayed The amount of games played.
	 * @param gamesWon The amount of games won.
	 * @param maxPoints The max score obtained in a game.
	 */
	private User(String name, File avatar, int gamesPlayed, int gamesWon, int maxPoints) {
		super(name, avatar, 0);
		this.gamesPlayed = gamesPlayed;
		this.gamesWon = gamesWon;
		this.maxPoints = maxPoints;
	}
	/**
	 * This method plays a card from the player hand and then notify the observers.
	 * @param c The card to be played.
	 */
	public void play(Card c) {
		hand.removeCard(c);
		setChanged();
		notifyObservers();
	}
	/**
	 * This method logs in the player, passing all the necessary informations to the {@link PlayerRecorder} class. After that,
	 * if the login is successful, a {@link LoginException} gets caught and a new instance of <code>User</code> gets created.
	 * @param name The name of the user.
	 * @param password The password of the player.
	 * @return The logged in player. 
	 * @throws LoginFailedException Thrown by {@link PlayerRecorder} and re-thrown to notify that the password is wrong.
	 * @throws UserNotFoundException Thrown by {@link PlayerRecorder} and re-thrown to notify a username not found in the database.
	 */
	public static User loginUser(String name, String password) throws LoginFailedException, UserNotFoundException{
		try {
			PlayerRecorder.login(name, password);
		}catch (LoginException e) {
			String avatar = e.getAvatar();
			int gamesPlayed = e.getStats()[0];
			int gamesWon = e.getStats()[1];
			int maxPoints = e.getStats()[2];
			
			isLoggedIn = true;
			loggedInUser = new User(name, new File(avatar), gamesPlayed, gamesWon, maxPoints);
		}
		return getLoggedInUser();
	}
	/**
	 * This method logs out the player, removing all the stored informations.
	 * @throws LogoutException To notify the logout.
	 */
	public static void logoutUser() throws LogoutException{
		loggedInUser = null;
		isLoggedIn = false;
		throw new LogoutException();
	}
	/**
	 * Getter for the logged in player.
	 * @return The logged in player.
	 */
	public static User getLoggedInUser() {
		if(!isLoggedIn) return null;
		return loggedInUser;
	}
	/**
	 * Getter for the number of games played.
	 * @return The number of games played.
	 */
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	/**
	 * When a new game starts, the counter increases.
	 */
	public void newGamePlayed() {
		gamesPlayed++;
	}
	/**
	 * Getter for the number of games won.
	 * @return The number of games won.
	 */
	public int getGamesWon() {
		return gamesWon;
	}
	/**
	 * When a game is won, the counter increases.
	 */
	public void newGameWon() {
		gamesWon++;
	}
	/**
	 * Getter for the max score.
	 * @return The max score.
	 */
	public int getMaxPoints() {
		return maxPoints;
	}
	/**
	 * When a new max score is achieved, it gets set.
	 * @param newMaxPoints The new max score.
	 */
	public void newMaxPoints(int newMaxPoints) {
		maxPoints = newMaxPoints;
	}
	/**
	 * Getter to check if the player has said uno.
	 * @return The corresponding boolean value.
	 */
	public boolean hasSaidUno() {
		if(hand.getSize()!=1) return true;
		return hasSaidUno;
	}
	/**
	 * Setter for the hasSaidUno value.
	 * This method is called during the computer-controlled players turns, after the user gets caught not saying uno.
	 * Check the method computerPlayerPlayGame from {@link Game}.
	 * @param hasSaidUno The corresponding boolean value.
	 */
	public void setHasSaidUno(boolean hasSaidUno) {
		this.hasSaidUno = hasSaidUno;
	}
	/**
	 * Setter for the hasSaidUno value.
	 * This method is called when the user says uno.
	 * I used overloading to distinguish this method from the previous.
	 * @param hasSaidUno The corresponding boolean value.
	 * @param u The user.
	 */
	public void setHasSaidUno(boolean hasSaidUno, User u) {
		this.hasSaidUno = hasSaidUno;
		setChanged();
		notifyObservers(hasSaidUno);
	}
	/**
	 * Setter for the player avatar.
	 * @param f The new player avatar.
	 */
	public void setAvatar(File f) {
		avatar = f;
	}
	/**
	 * This method resets the score to 0 for when a new game starts.
	 */
	public void resetPoints() {
		points = 0;
	}
	/**
	 * This method empties the User hand.
	 */
	public void emptyHand() {
		hand = new PlayerHand();
	}
}