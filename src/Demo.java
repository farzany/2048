/**
 * Author: Farzan Yazdanjou | yazdanjf
 * Revised: April 10, 2021
 * 
 * Description: Initializing a game of 2048 on the terminal.
 */

package src;

public class Demo {
	public static void main(String[] args) {
		GameT model = new GameT();
		Display view = Display.getInstance();
		Controller game = Controller.getInstance(model, view);
		game.runGame();
	}
}