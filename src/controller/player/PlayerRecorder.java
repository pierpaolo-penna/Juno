package controller.player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import model.player.User;
/**
 * This class contains some static methods used to interact with the "database" in which the <code>User</code> informations are stored.
 * The reference files are contained in the resources folder.
 * @author pierp
 *
 */
public final class PlayerRecorder {
	/**
	 * This method is used for logging in the <code>User</code>.
	 * @param name The username that the <code>User</code> tries to log in with.
	 * @param password The password that the <code>User</code> tries to log in with.
	 * @throws LoginException Thrown when the login is successful.
	 * @throws UserNotFoundException Thrown when the wrong username is used.
	 * @throws LoginFailedException Thrown when the <code>User</code> writes the wrong password.
	 */
	public static void login(String name, String password) throws LoginException, UserNotFoundException, LoginFailedException{
		try {
			File players = new File("src/resources/players.txt");
			File stats = new File("src/resources/stats.txt");
			BufferedReader playersReader = new BufferedReader(new FileReader(players));
			BufferedReader statsReader = new BufferedReader(new FileReader(stats));

			String player, stat;
			while ((player = playersReader.readLine()) != null) {
				String[] playerData = player.split(" ");
				String nameFromFile = playerData[1];
				String passwordFromFile = playerData[3];
				String avatarFromFile = playerData[5];
				
				if(name.equals(nameFromFile)) {
					if(password.equals(passwordFromFile)) {
						while((stat = statsReader.readLine()) != null) {
							String[] playerStats = stat.split(" ");
							String nameFromStats = playerStats[1];
							if(nameFromStats.equals(nameFromFile)) {
								int gamesPlayedFromFile = Integer.parseInt(playerStats[3]);
								int gamesWonFromFile = Integer.parseInt(playerStats[5]);
								int maxPointsFromFile = Integer.parseInt(playerStats[7]);
								
								playersReader.close();
								statsReader.close();
								throw new LoginException(avatarFromFile, gamesPlayedFromFile, gamesWonFromFile, maxPointsFromFile);
							}
						}
					}
					else{
						playersReader.close();
						statsReader.close();
						throw new LoginFailedException();
					}
				}
			}
			playersReader.close();
			statsReader.close();
			throw new UserNotFoundException();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to register a new <code>User</code>.
	 * @param name The name that the <code>User</code> tries to register with.
	 * @param password The password that the <code>User</code> tries to register with.
	 * @param avatar The string containing the path for the file where the <code>User</code> avatar is at.
	 * @throws DuplicateUsernameException Thrown when a new <code>User</code> tries to register with an already existing username.
	 * @throws RegisterException Thrown when the registration is successful.
	 */
	public static void register(String name, String password, String avatar) throws DuplicateUsernameException, RegisterException{
		try {
			File players = new File("src/resources/players.txt");
			File stats = new File("src/resources/stats.txt");
			
			BufferedReader playersReader = new BufferedReader(new FileReader(players));
			
			String player, existingPlayers = "";
			while ((player = playersReader.readLine()) != null) {
				existingPlayers+=player+"\n";
				String[] playerData = player.split(" ");
				String nameFromFile = playerData[1];
				
				if(name.equals(nameFromFile)) {
					playersReader.close();
					throw new DuplicateUsernameException();
				}
			}
			playersReader.close();
			
			FileWriter playersWriter = new FileWriter(players);
			String newPlayerInformations = existingPlayers+"Name: "+name+" Password: "+password+" Avatar: "+avatar;
			playersWriter.write(newPlayerInformations);
			
			playersWriter.close();
			
			BufferedReader statsReader = new BufferedReader(new FileReader(stats));
			
			String statsRow, existingStats = "";
			while((statsRow = statsReader.readLine()) != null) {
				existingStats += statsRow+"\n";
			}
			statsReader.close();
			
			FileWriter statsWriter = new FileWriter(stats);
			String newPlayerStats = existingStats+"Name: "+name+" Games_played: "+0+" Games_won: "+0+" Max_points: "+0;
			statsWriter.write(newPlayerStats);
			
			statsWriter.close();
			
			throw new RegisterException();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to update the number of games the <code>User</code> has played.
	 */
	public static void newGame() {
		try {
			User u = User.getLoggedInUser();
			String name = u.getName();
			File stats = new File("src/resources/stats.txt");
			
			BufferedReader statsReader = new BufferedReader(new FileReader(stats));
			
			String statsRow, existingStats = "";
			while((statsRow = statsReader.readLine())!=null) {
				String[] records = statsRow.split(" ");
				if(records[1].equals(name)) {
					int gamesPlayed = Integer.parseInt(records[3])+1;
					records[3] = ""+gamesPlayed;
					statsRow = "";
					for(int i=0; i<8; i++) statsRow+=records[i]+" ";
				}
				existingStats += statsRow+"\n";
			}
			statsReader.close();
			
			FileWriter statsWriter = new FileWriter(stats);
			statsWriter.write(existingStats);
			statsWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to update the <code>User</code> stats after the end of a <code>Game</code>: the maximum score reached gets re-checked,
	 * and if he is the winner, the count of the games he won will increase.
	 * @param winner The index of the player who won the game (0: <code>User</code>; 1,2,3: computer player).
	 */
	public static void gameEnded(int winner) {
		try {
			User u = User.getLoggedInUser();
			String name = u.getName();
			File stats = new File("src/resources/stats.txt");
			
			BufferedReader statsReader = new BufferedReader(new FileReader(stats));
			
			String statsRow, existingStats = "";
			while((statsRow = statsReader.readLine())!=null) {
				String[] records = statsRow.split(" ");
				if(records[1].equals(name)) {
					if(winner == 0) {
						int gamesWon = Integer.parseInt(records[5]+1);
						records[5]=""+gamesWon;
					}
					int maxScore = Integer.parseInt(records[7]);
					if(u.getPoints()>maxScore) records[7] = ""+u.getPoints();
					statsRow = "";
					for(int i=0; i<8; i++) statsRow+=records[i]+" ";
				}
				existingStats += statsRow+"\n";
			}
			
			statsReader.close();
			
			FileWriter statsWriter = new FileWriter(stats);
			statsWriter.write(existingStats);
			statsWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method attempts to change the User's password.
	 * @param oldPassword The old password.
	 * @param newPassword The new password.
	 * @param confirmNewPassword The new password again.
	 * @return true if the attempt was successful, false otherwise.
	 */
	public static boolean changePassword(String oldPassword, String newPassword, String confirmNewPassword) {
		if(!newPassword.equals(confirmNewPassword)) {
			JOptionPane.showMessageDialog(null, "New passwords do not match", "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("src/resources/players.txt")));
			User loggedInPlayer = User.getLoggedInUser();
			String player, existingPlayers = "";
			while((player = br.readLine()) != null) {
				String[] data = player.split(" ");
				if(data[1].equals(loggedInPlayer.getName())){
					if(!data[3].equals(oldPassword)) {
						JOptionPane.showMessageDialog(null, "Wrong password", "Error", JOptionPane.ERROR_MESSAGE);
						br.close();
						return false;
					}
					else {
						if(newPassword.equals(oldPassword)) {
							JOptionPane.showMessageDialog(null, "New password cannot be the old one", "Warning", JOptionPane.WARNING_MESSAGE);
							br.close();
							return false;
						}
						else {
							player = "";
							data[3] = newPassword;
							for(int i=0; i<6; i++) player += data[i]+" ";
							existingPlayers += player+"\n";
						}
					}
				}
				else {
					existingPlayers += player+"\n";
				}
			}
			FileWriter fw = new FileWriter(new File("src/resources/players.txt"));
			fw.write(existingPlayers);
			br.close();
			fw.close();
			JOptionPane.showMessageDialog(null, "The password has been changed", "Success", JOptionPane.WARNING_MESSAGE);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
