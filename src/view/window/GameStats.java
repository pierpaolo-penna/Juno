package view.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.buttons.ButtonsVariations;
import model.game.Game;
import model.player.Player;
import view.components.ButtonWithIcon;
/**
 * This class displays the players' scores after a game.
 * It has been developed according to the singleton pattern.
 * @author pierp
 *
 */
final class GameStats extends JPanel{
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The only instance of the game stats.
	 */
	protected static GameStats gameStatsInstance = new GameStats();
	/**
	 * The image used to paint the background.
	 */
	private Image background;
	/**
	 * The container in which the stats are displayed.
	 */
	private JPanel container;
	/**
	 * A label containing a piece of information.
	 */
	private JLabel player0, player1, player2, player3, p0score, p1score, p2score, p3score, p0extra, p1extra, p2extra, p3extra;
	/**
	 * A label containing one of the headers.
	 */
	private JLabel names, scores, extraLabel;
	/**
	 * The button that closes the game stats.
	 */
	private ButtonWithIcon quit;
	/**
	 * The constructor is private, according to the singleton pattern.
	 */
	private GameStats() {
		setLayout(null);
		try {
			background = ImageIO.read(new File("src/resources/gameBackground.jpg")).getScaledInstance(1000, 700, 0);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		container = new JPanel();
		container.setOpaque(true);
		container.setBounds(200, 150, 600, 400);
		container.setBorder(new LineBorder(Color.black,2));
		container.setBackground(Color.lightGray);
		container.setLayout(null);
		
		add(container);
	}
	/**
	 * This method is used to paint the background.
	 */
	@Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
    }
	/**
	 * This method is used to load all the informations after a game ends.
	 * @param g The game ended.
	 */
	protected void loadStats(Game g) {
		unloadStats();
		int winner = g.getWinner();
		int extra = 0;
		int[] points = {0,0,0,0};
		Player[] players = g.getPlayers();
		for(int i=0;i<4;i++) {
			points[i]=players[i].getPoints();
			if(i!=winner) extra+=players[i].getHand().convertToPoints();
		}
		points[winner]-=extra;
		
		
		Font boldVerdana = new Font("Verdana", 1, 22);
		Font normalVerdana = new Font("Verdana", 0, 19);
		
		names = new JLabel("Names", SwingConstants.CENTER);
		names.setBounds(40,40,160,35);
		names.setFont(boldVerdana);
		
		scores = new JLabel("Scores", SwingConstants.CENTER);
		scores.setBounds(225, 40, 160, 35);
		scores.setFont(boldVerdana);
		
		extraLabel = new JLabel("Extra", SwingConstants.CENTER);
		extraLabel.setBounds(410, 40, 160, 35);
		extraLabel.setFont(boldVerdana);
		
		container.add(names);
		container.add(scores);
		container.add(extraLabel);
		
		
		player0 = new JLabel(players[0].getName(), SwingConstants.CENTER);
		player0.setBounds(40, 90, 150, 30);
		player0.setFont(normalVerdana);
		
		player1 = new JLabel("Player 1", SwingConstants.CENTER);
		player1.setBounds(40, 140, 150, 30);
		player1.setFont(normalVerdana);
		
		player2 = new JLabel("Player 2", SwingConstants.CENTER);
		player2.setBounds(40, 190, 150, 30);
		player2.setFont(normalVerdana);
		
		player3 = new JLabel("Player 3", SwingConstants.CENTER);
		player3.setBounds(40, 240, 150, 30);
		player3.setFont(normalVerdana);
		
		container.add(player0);
		container.add(player1);
		container.add(player2);
		container.add(player3);
		
		
		p0score = new JLabel(""+points[0], SwingConstants.CENTER);
		p0score.setBounds(225, 90, 150, 30);
		p0score.setFont(normalVerdana);
		
		p1score = new JLabel(""+points[1], SwingConstants.CENTER);
		p1score.setBounds(225, 140, 150, 30);
		p1score.setFont(normalVerdana);
		
		p2score = new JLabel(""+points[2], SwingConstants.CENTER);
		p2score.setBounds(225, 190, 150, 30);
		p2score.setFont(normalVerdana);
		
		p3score = new JLabel(""+points[3], SwingConstants.CENTER);
		p3score.setBounds(225, 240, 150, 30);
		p3score.setFont(normalVerdana);
		
		container.add(p0score);
		container.add(p1score);
		container.add(p2score);
		container.add(p3score);
		
		
		p0extra = new JLabel("0", SwingConstants.CENTER);
		p0extra.setBounds(410, 90, 150, 30);
		p0extra.setFont(normalVerdana);
		
		p1extra = new JLabel("0", SwingConstants.CENTER);
		p1extra.setBounds(410, 140, 150, 30);
		p1extra.setFont(normalVerdana);
		
		p2extra = new JLabel("0", SwingConstants.CENTER);
		p2extra.setBounds(410, 190, 150, 30);
		p2extra.setFont(normalVerdana);
		
		p3extra = new JLabel("0", SwingConstants.CENTER);
		p3extra.setBounds(410, 240, 150, 30);
		p3extra.setFont(normalVerdana);
		
		String extraPoints = ""+extra;
		if(winner==0) p0extra.setText(extraPoints);
		else if(winner==1) p1extra.setText(extraPoints);
		else if(winner ==2) p2extra.setText(extraPoints);
		else if(winner==3) p3extra.setText(extraPoints);
		
		container.add(p0extra);
		container.add(p1extra);
		container.add(p2extra);
		container.add(p3extra);
		
		
		quit = new ButtonWithIcon("quit.png", "quit2.png", false, ButtonsVariations.GAME_STATS_QUIT);
		quit.setLocation(222, 300);
		
		container.add(quit);
		
		
		add(container);
		repaint();
	}
	/**
	 * This method is used to close the game stats.
	 */
	protected void unloadStats() {
		removeAll();
	}
}
