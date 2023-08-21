package controller.player;
/**
 * This exception is thrown after a successful login, as an easier way to notify the successful attempt.
 * @author pierp
 *
 */
public final class LoginException extends Exception {
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This array contains the informations about the logged in <code>User</code>, read from the file storing the data.
	 */
	private int[] stats = {0,0,0};
	/**
	 * This string contains the file name of the avatar belonging to the logged in <code>User</code>, read from the file storing the data.
	 */
	private String avatar;
	/**
	 * The constructor for this exception. It contains the informations retrieved from the file after the login.
	 * @param avatarFromFile The string containing the file path to the avatar of the <code>User</code>.
	 * @param gamesPlayed The number of games played by the <code>User</code>.
	 * @param gamesWon The number of games won by the <code>User</code>.
	 * @param maxPoints The best score achieved by the <code>User</code>.
	 */
	public LoginException(String avatarFromFile, int gamesPlayed, int gamesWon, int maxPoints) {
		avatar = avatarFromFile;
		stats[0] = gamesPlayed;
		stats[1] = gamesWon;
		stats[2] = maxPoints;
	}
	/**
	 * Getter for the stats of the <code>User</code>.
	 * @return The stats.
	 */
	public int[] getStats() {
		return stats;
	}
	/**
	 * Getter for the file path of the <code>User</code> avatar.
	 * @return The file path of the avatar.
	 */
	public String getAvatar() {
		return avatar;
	}
}