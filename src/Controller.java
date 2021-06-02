/**
 * @file Controller.java
 * @author Farzan Yazdanjou | yazdanjf
 * @brief Contains an abstract object for playing a game of 2048.
 * @date April 6, 2021
 */

package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/**
 * @brief An abstract object for playing a game of 2048 on the terminal.
 * @details A module used to play a game of 2048 using GameT and Display on the terminal.
 */
public class Controller {
	private GameT model;
	private Display view;
	private static Controller controller = null;

	private Scanner keyboard = new Scanner(System.in);

	/**
	 * @brief Constructor for the controller.
	 * @details Initializes the base GameT and Display objects.
	 * @param model The GameT object for which the controller will play on.
	 * @param model The Display object for which the game will be displayed on.
	 */
	private Controller(GameT model, Display view) {
		this.model = model;
		this.view = view;
	}
	
    /**
     * @brief Public static method for obtaining a single Controller instance.
     * @return The single Controller object.
	 * @param model The GameT object for which the controller will play on.
	 * @param model The Display object for which the game will be displayed on.
     */
	public static Controller getInstance (GameT model, Display view) {
		if (controller == null) {
			controller = new Controller(model, view);
		} return controller;
	}

	/**
	 * @brief Initializes the game (GameT object) to a new game.
	 * @details The board is wiped and two random tiles are added.
	 */
	public void initializeGame() {
		this.model = new GameT();
	}

	/**
	 * @brief Checks whether the game is over (lost) or not.
	 * @return A boolean expressing whether the game is over.
	 */
	public boolean getStatus() {
		return this.model.isLose();
	}

	/**
	 * @brief Returns the user's raw string input.
	 * @return The string input that the user typed.
	 */
	public String readInput() {
		return keyboard.nextLine();
	}

	/**
	 * @brief Run the game.
	 * @details Runs the game, accepting input and moving the board accordingly.
	 * Displays the board and score along with helpful messages.
	 */
	public void runGame() {
		initializeGame();
		displayWelcomeMessage();
		displayInstructions();
		displayBoard();
		boolean win = false;
		while (!model.isLose()) {
			System.out.print("Move:\t");
			String input = readInput();
			if (input.equals("w")) {model.makeMove(DirectionT.up);model.addTile();}
			else if (input.equals("s")) {model.makeMove(DirectionT.down);model.addTile();}
			else if (input.equals("a")) {model.makeMove(DirectionT.left);model.addTile();}
			else if (input.equals("d")) {model.makeMove(DirectionT.right);model.addTile();}
			else if (input.equals("q")) {break;}
			displayScore();
			displayBoard();
			if (model.isWin() && !win) {
				win = true;
				display2048Message();
			}
		} displayEndingMessage();
	}

	/**
	 * @brief Updates the view module to display a welcome message.
	 */
	public void displayWelcomeMessage() {
		this.view.printWelcomeMessage();
	}

	/**
	 * @brief Updates the view module to display the game instructions.
	 */
	public void displayInstructions() {
		this.view.printInstructions();
	}

	/**
	 * @brief Updates the view module to display the model's (GameT) board.
	 */
	public void displayBoard() {
		this.view.printBoard(model);
	}

	/**
	 * @brief Updates the view module to display the model's (GameT) score.
	 */
	public void displayScore() {
		this.view.printScore(model);
	}

	/**
	 * @brief Updates the view module to display the ending message.
	 */
	public void displayEndingMessage() {
		this.view.printEndingMessage();
	}

	/**
	 * @brief Updates the view module to display the congratulatory message for reaching 2048.
	 */
	public void display2048Message() {
		this.view.print2048Message();
	}

	public static void main(String[] args) {
		GameT model = new GameT();
		Display view = Display.getInstance();
		Controller game = Controller.getInstance(model, view);
		game.runGame();
	}
		
}
