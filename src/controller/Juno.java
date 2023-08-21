package controller;

import view.window.JunoWindow;

/**
 * This is the main class for the application. It opens the game window and loads the game controller.
 * @author pierp
 *
 */
@SuppressWarnings("unused")
public class Juno {
	/**
	 * The main method of the project.
	 * @param args For the main method.
	 */
	public static void main(String[] args) {
		JunoWindow gw = JunoWindow.getInstance();
		GameController gc = GameController.getInstance(gw);
	}
}
