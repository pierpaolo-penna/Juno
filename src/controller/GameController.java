package controller;

import java.util.Observable;
import java.util.Observer;

import model.game.Deck;
import model.game.DiscardedCards;
import model.game.Game;
import model.player.User;
import view.game.player.Avatar;
import view.window.GameView;
import view.window.JunoWindow;
/**
 * This class is responsible for starting a new game and more generally is a link between the view and the model.
 * It has been designed according to the singleton pattern. It is an observer.
 * @author pierp
 *
 */
@SuppressWarnings("deprecation")
public final class GameController implements Observer{
	/**
	 * The only instance of <code>GameController</code> instantiated during every application run.
	 */
	private static GameController gameControllerInstance = new GameController();
	
	/**
	 * The <code>Game</code> running at every time.
	 */
	private Game game;
	/**
	 * The window in which the application is displayed.
	 */
	private JunoWindow window;
	/**
	 * The display for the actual game.
	 */
	private GameView view;
	
	/**
	 * Private constructor, according to the singleton pattern. There is no need for any instruction in it.
	 */
	private GameController() {}
	
	/**
	 * The method that starts a new <code>Game</code>. It switches the content of the <code>JunoWindow</code> to the <code>GameView</code>,
	 * and adds all the Observers to the model components.
	 * @param u The user launching the game.
	 */
	public void newGame(User u) {
		game = new Game(u, Avatar.getThreeRandomAvatars(u.getAvatar()));
		window.switchToGame(game);
		game.addObserver(view);
		game.getDiscardedCards().addObserver(view.getDiscardedCards());
		game.getDeck().addObserver(gameControllerInstance);
		for(int i=0; i<4; i++) {
			game.getPlayers()[i].addObserver(view.getPlayerGUI(i));
		}
		view.repaint();
	}
	/**
	 * Getter for the game currently being played.
	 * @return The game.
	 */
	public Game getGame() {
		return game;
	}
	/**
	 * Getter for the instance of <code>GameController</code>.
	 * @return The instance.
	 */
	public static GameController getInstance() {
		return gameControllerInstance;
	}
	/**
	 * This method is used by the class {@link Juno} for the initialization of the fields of <code>GameController</code>.
	 * It is protected so that it can't be accessed anywhere else.
	 * @param gw The window displaying the <code>Game</code>
	 * @return The only instance of <code>GameController</code>
	 */
	protected static GameController getInstance(JunoWindow gw) {
		gameControllerInstance.window = gw;
		gameControllerInstance.view = gw.getGameView();
		return gameControllerInstance;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Deck) {
			((DiscardedCards)arg).addObserver(view.getDiscardedCards());
		}
	}
}
