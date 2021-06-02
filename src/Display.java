/**
 * @file Display.java
 * @author Farzan Yazdanjou | yazdanjf
 * @brief Contains an abstract object for displaying messages related to a game of 2048.
 * @date April 6, 2021
 */
package src;
/**
 * @brief An abstract object for displaying messages related to a game of 2048.
 * @details Includes multiple methods to display the game board, score,
 * and other useful messages.
 */
public class Display {
	private static Display visual = null;
	private Display(){}

    /**
     * @brief Public static method for obtaining a single Display instance.
     * @return The single Display object.
     */
	public static Display getInstance() {
		if (visual == null) {
			return visual = new Display();
		} return visual;
	}

	/**
	 * @brief Displays the game's welcome message.
	 * @details Single-line message.
	 */
	public void printWelcomeMessage() {
		System.out.println("-----------------------------");
		System.out.println("       2048 : Welcome!       ");
		System.out.println("-----------------------------");
	}

	/**
	 * @brief Displays the game's instructions.
	 * @details Multi-line message.
	 */
	public void printInstructions() {
		String instructions = "Instructions:\n"
							+ "Your goal is to reach a combined tile of size 2048.\n"
							+ "You can move up, down, left, and right.\n"
							+ "Moving two equal tiles into eachother combines them into one larger tile.\n"
							+ "The game has been lost if you can't move in any direction.\n"
							+ "The game has been won if you reach the 2048 tile.\n"
							+ "--------------------------\n"
							+ "Up: w\tDown: s\tLeft: a\tRight: d\tQuit: q\n";
		System.out.println(instructions);
	}

	/**
	 * @brief Displays the game's board state.
	 * @details Displays the game board using ASCII characters.
	 * Zeros are changed to the letter "O". Multi-line message.
	 * @param model The GameT object for which the board will be displayed.
	 */
	public void printBoard(GameT model) {
		System.out.println("--------------------------");
		int end = model.getBoard().length;
		int count = 0;
		for (int[] row : model.getBoard()) {
			count++;
			boolean first = true;
			for (int tile : row) {
				if (first) {
					if (tile == 0) {System.out.print("O");} 
					else {System.out.print(tile);}
					first = false;
				}
				else {
					if (tile == 0) {System.out.print("\tO");} 
					else {System.out.print("\t"+ tile);}
				}
			} if (end > count) System.out.print("\n\n");
			else {System.out.print("\n");}
		}
		System.out.println("--------------------------");
	}

	/**
	 * @brief Displays the game's score.
	 * @details Single-line message.
	 * @param model The GameT object for which the score will be displayed.
	 */
	public void printScore(GameT game) {
		System.out.println("---->");
		System.out.println("Score:\t" + game.getScore());
		System.out.println("---->");
	}

	/**
	 * @brief Displays the game's end message.
	 * @details Displays the game-end message. Depends on if the game has
	 * been won or lost. Multi-line message.
	 */
	public void printEndingMessage() {
		System.out.println("-----------------------------------------------------");
		System.out.println("       Game has ended : Thank you for playing!       ");
		System.out.println("-----------------------------------------------------");
	}

	/**
	 * @brief Displays the game's end message.
	 * @details Displays the game-end message. Depends on if the game has
	 * been won or lost. Multi-line message.
	 */
	public void print2048Message() {
		System.out.println("-------------------------------------------");
		System.out.println("           You have reached 2048!          ");
		System.out.println("Congratulation! You can continue or restart");
		System.out.println("-------------------------------------------");
	}
}
