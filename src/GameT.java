/**
 * @file GameT.java
 * @author Farzan Yazdanjou | yazdanjf
 * @brief Contains an ADT for the state of a game of 2048.
 * @date April 6, 2021
 */

package src;
import java.util.Random;
import java.util.Arrays;

/**
 * @brief An ADT for the game state of 2048.
 * @details A game is represented by the layout of tiles on the board and the score.
 * The game board consists of 4 rows of 4 tiles for a total of 16 tiles stored as
 * an integer array of arrays. A tile is said to be empty if it has a value of zero - which
 * they all do by default. It is assumed that the addTile() method will be called manually
 * to populate the board with random tiles at the start of the game and after every valid move.
 * Largely assuming that the correct input types will be provided to the methods.
 */
public class GameT {
	private int[][] board;
	private boolean validMove;
	private int score;
	private static int size = 4;
	private static int base = 2;

	/**
	 * @brief The constructor for GameT.
	 * @details Initializes the score to zero, validMove to true, and the
	 * board to an integer 2D array of zeros with the addition of two random tiles.
	 */
	public GameT() {
		this.score = 0;
		validMove = true;
		board = new int[size][size];
		addTile();
		addTile();
	}

	/**
	 * @brief Returns the game's score.
	 * @return The game's score as an integer (int).
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * @brief Sets the game's board to a specified layout.
	 * @details This method overrides the current board's layout for the new layout.
	 * This action cannot be undone as the old layout is not stored. This method has
	 * been included ONLY for testing purposes.
	 * @param b A 2D integer array of size 4x4 (int[4][4]).
	 */
	public void setBoard(int[][] b) {
		this.board = b;
	}

	/**
	 * @brief Returns the game's board.
	 * @return The game's board as a 2D integer array.
	 */
	public int[][] getBoard() {
		return this.board;
	}

	/**
	 * @brief Adds a random tile (of value 2 or 4) to the board.
	 * @details A tile is only added at a position where the current tile has
	 * a value of zero. If no such position exists, a tile is not added. If the
	 * previous move was not valid (did not alter the board), such that
	 * validMove is false, a tile is not added.
	 */
	public void addTile() {
		if (!validMove) {return;}
		boolean hasZero = false;
		for (int[] row : board) {
			for (int tile : row) {
				if (tile == 0) {hasZero = true;}
			}
		} if (!hasZero) {return;}
		Random rand = new Random();
		int[] options = new int[] {base,base*2};
		int i = rand.nextInt(size);
		int j = rand.nextInt(size);
		while (board[i][j] != 0) {
			i = rand.nextInt(size);
			j = rand.nextInt(size);
		} this.board[i][j] = options[rand.nextInt(2)];
	}

	/**
	 * @brief Performs a move on the board in the given direction.
	 * @details A direction of up or down performs a move on the columns of the board.
	 * A direction of left or right performs a move on the rows of the board.
	 * validMove is set to false before the move is performed.
	 * @param direction A DirectionT object representing in which direction the board will move.
	 */
	public void makeMove(DirectionT direction) {
		validMove = false;
		if (direction == DirectionT.up) { // Up
			int[][] b = transpose(board);
			for (int i=0; i<size; i++) {
				b[i] = combine_seq_left(b[i]);
			} this.board = transpose(b);
		}
		if (direction == DirectionT.down) { // Down
			int[][] b = transpose(board);
			for (int i=0; i<size; i++) {
				b[i] = combine_seq_right(b[i]);
			} this.board = transpose(b);
		}
		if (direction == DirectionT.left) { // Left
			for (int i=0; i<size; i++) {
				board[i] = combine_seq_left(board[i]);
			} 
		}
		if (direction == DirectionT.right) { // Right
			for (int i=0; i<size; i++) {
				board[i] = combine_seq_right(board[i]);
			} 
		}
	}

	/**
	 * @brief Checks whether a 2048 tile has been reached.
	 * @details Checks if a tile is equal to or greater than 2048.
	 * @return A boolean expressing if 2048 has been reached.
	 */
	public boolean isWin() {
		for (int[] row : this.board) {
			for (int tile : row) {if (tile>=Math.pow(base,11)) {return true;}}
		} return false;
	}

	/**
	 * @brief Checks whether the user has lost and the game is over.
	 * @details Checks whether the game is over due to no possible moves remaining.
	 * @return A boolean expressing if the game has been lost.
	 */
	public boolean isLose() {
		GameT result = new GameT();
		for (int i=0;i<size;i++) {
			for (int j=0;j<size;j++) {
				result.board[i][j] = board[i][j];
				if (board[i][j] == 0) {return false;}
			}
		}
		result.makeMove(DirectionT.up);
		result.makeMove(DirectionT.down);
		result.makeMove(DirectionT.left);
		result.makeMove(DirectionT.right);
		for (int i=0;i<size;i++) {
			for (int j=0;j<size;j++) {
				if (result.board[i][j] != board[i][j]) {return false;}
			}
		}
		return true;
	}

	/**
	 * @brief Moves and combines all integers in an array to the left.
	 * @details Used to move a row to the left. Combines all equal elements while moving left.
	 * Updates validMove to true if the output array differs from the input array.
	 * @return An integer array representing the transformed input array.
	 */
	private int[] combine_seq_left(int[] seq) {
		int[] shifted = shift_seq_left(seq);
		for (int i=0; i<size-1; i++) {
			if (shifted[i] == shifted[i+1]) {
				this.score += 2*shifted[i];
				shifted[i] = 2*shifted[i];
				shifted[i+1] = 0;
			}
		} if (! Arrays.equals(seq,shift_seq_left(shifted))) {validMove = true;}
		return shift_seq_left(shifted);
	}

	/**
	 * @brief Moves and combines all integers in an array to the right.
	 * @details Used to move a row to the right. Combines all equal elements while moving right.
	 * Updates validMove to true if the output array differs from the input array.
	 * @return An integer array representing the transformed input array.
	 */
	private int[] combine_seq_right(int[] seq) {
		int[] shifted = shift_seq_right(seq);
		for (int i=size-1; i>0; i--) {
			if (shifted[i] == shifted[i-1]) {
				this.score += 2*shifted[i];
				shifted[i] = 2*shifted[i];
				shifted[i-1] = 0;
			}
		} if (! Arrays.equals(seq,shift_seq_right(shifted))) {validMove = true;}
		return shift_seq_right(shifted);
	}

	/**
	 * @brief Shifts all the non-zero elements in an array to the left.
	 * @return An integer array of the array after the transformation.
	 */
	private int[] shift_seq_left(int[] seq) {
		int[] temp = new int[size];
		int i = 0;
		for (int j=0; j<size; j++) {
			if (seq[j] != 0) {
				temp[i] = seq[j];
				i++;
			}
		} return temp;
	}

	/**
	 * @brief Shifts all the non-zero elements in an array to the right.
	 * @return An integer array of the array after the transformation.
	 */
	private int[] shift_seq_right(int[] seq) {
		int[] temp = new int[size];
		int i = 3;
		for (int j=size-1; j>=0; j--) {
			if (seq[j] != 0) {
				temp[i] = seq[j];
				i--;
			}
		} return temp;
	}

	/**
	 * @brief Interchanges the rows and columns of the given 2D integer array.
	 * @details Assuming the given 2D array is size x size.
	 * @return Interchanged 2D integer array.
	 */
	private int[][] transpose(int[][] b) {
		int[][] temp = new int[size][size];
		for (int i=0;i<size;i++) {
			for (int j=0;j<size;j++) {
				temp[i][j] = b[j][i];
			}
		} return temp;
	}

}

