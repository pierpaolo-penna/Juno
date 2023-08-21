package view.window;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import model.game.Game;

/**
 * This is the window in which the program is loaded. 
 * This class has been developed according to the singleton pattern.
 * @author pierp
 *
 */
public final class JunoWindow extends JFrame {
	/**
	 * I used the default value for this field.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The only instance of the window.
	 */
	private static JunoWindow junoWindowInstance = new JunoWindow();
	/**
	 * The panel in which the pre-game operations are loaded.
	 */
	private UnoBackground unoBackground = UnoBackground.getInstance();
	/**
	 * The panel in which the game is loaded.
	 */
	private GameView gameView = GameView.gameViewInstance;
	/**
	 * The panel in which the post-game stats are displayed.
	 */
	private GameStats gameStats = GameStats.gameStatsInstance;
	/**
	 * A private constructor, according to the singleton pattern.
	 */
	private JunoWindow() {
		super("JUNO");
		setLayout(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		setContentPane(unoBackground);
		
		try {
			this.setIconImage(ImageIO.read(new File("src/resources/Uno logo.png")));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		setSize(1000,700);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	/**
	 * Getter for the window instance.
	 * @return The window instance.
	 */
	public static JunoWindow getInstance() {
		return junoWindowInstance;
	}
	/**
	 * Getter for the uno background.
	 * @return The uno background.
	 */
	public UnoBackground getUnoBackground() {
		return unoBackground;
	}
	/**
	 * Getter for the game view.
	 * @return The game view.
	 */
	public GameView getGameView() {
		return gameView;
	}
	/**
	 * This method sets the content pane to be the game view.
	 * @param g The game to be loaded.
	 */
	public void switchToGame(Game g) {
		gameView.loadGame(g);
		setContentPane(gameView);
		revalidate();
		repaint();
	}
	/**
	 * This method sets the content pane to be the homepage.
	 */
	public void switchToHomepage() {
		gameView.unloadGame();
		gameStats.unloadStats();
		unoBackground.getStatsPanel().update();
		setContentPane(unoBackground);
		revalidate();
		repaint();
	}
	/**
	 * This method sets the content pane to be the game stats.
	 * @param g The game that was played.
	 */
	public void switchToGameStats(Game g) {
		gameStats.loadStats(g);
		setContentPane(gameStats);
		revalidate();
		repaint();
	}
}
