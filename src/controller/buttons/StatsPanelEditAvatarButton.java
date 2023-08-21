package controller.buttons;

import java.awt.event.MouseEvent;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import model.player.User;
import view.components.StatsPanel;
import view.window.JunoWindow;
/**
 * This class models the behavior of the "edit" button responsible for changing the avatar chosen by the <code>User</code>.
 * @author pierp
 *
 */
final class StatsPanelEditAvatarButton extends ButtonWithIconHandler {
	/**
	 * The constructor is protected, according to the Factory pattern.
	 */
	protected StatsPanelEditAvatarButton() {}
	/**
	 * This method opens a new <code>JFileChooser</code> and records the (eventual) new avatar in the "players.txt" file.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		User u = User.getLoggedInUser();
		StatsPanel sp = JunoWindow.getInstance().getUnoBackground().getStatsPanel();
		JFileChooser jfc = sp.getAvatarChooser();
		jfc.setCurrentDirectory(new File("src/resources/avatars"));
		int result = jfc.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION && jfc.getSelectedFile() != null) {
			try {
				BufferedReader playerReader = new BufferedReader(new FileReader("src/resources/players.txt"));
				
				String player, existingPlayers = "";
				while((player = playerReader.readLine()) != null) {
					String[] playerData = player.split(" ");
					if(playerData[1].equals(u.getName())) {
						playerData[5] = jfc.getSelectedFile().toString();
						player = "";
						for(int i=0; i<6; i++) {
							player += playerData[i]+" ";
						}
					}
					existingPlayers += player+"\n";
				}
				FileWriter playerWriter = new FileWriter("src/resources/players.txt");
				if(!existingPlayers.equals("")) playerWriter.write(existingPlayers);
				playerReader.close();
				playerWriter.close();
				sp.getAvatar().setIcon(new ImageIcon(ImageIO.read(jfc.getSelectedFile()).getScaledInstance(150, 150, 0)));
				u.setAvatar(jfc.getSelectedFile());
			} catch (IOException f) {
				f.printStackTrace();
			}
			sp.repaint();
		}
		else return;
	}
}
