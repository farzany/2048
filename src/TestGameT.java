/**
 * Author: Farzan Yazdanjou
 * Revised: April 7, 2021
 * 
 * Description: Unit testing for GameT
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;


public class TestGameT {

	int[][] board1 = new int[][]{{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}}; // Empty
	int[][] board2 = new int[][]{{0,0,0,0},{0,2,2,0},{0,0,0,0},{0,0,0,0}}; // 2 Tiles
	int[][] board3 = new int[][]{{2,2,2,2},{2,2,2,2},{2,2,2,2},{2,2,2,2}}; // All filled
	int[][] board4 = new int[][]{{2,0,2,2},{2,0,2,2},{2,0,2,2},{2,0,2,2}}; // 2nd Column Empty
	int[][] board5 = new int[][]{{2,2,0,2},{2,2,0,2},{2,2,0,2},{2,2,0,2}}; // 3rd Column Empty
	int[][] board6 = new int[][]{{2,2,2,2},{0,0,0,0},{2,2,2,2},{2,2,2,2}}; // 2nd Row Empty
	int[][] board7 = new int[][]{{2,2,2,2},{2,2,2,2},{0,0,0,0},{2,2,2,2}}; // 3rd Row Empty
	int[][] board8 = new int[][]{{2,4,8,16},{16,8,4,2},{32,16,8,4},{4,8,16,32}}; // Losing Board 
	int[][] board9 = new int[][]{{2,0,0,2},{0,0,0,0},{0,0,0,0},{2,0,0,2}}; // Corners 
	int[][] board10 = new int[][]{{2,0,0,0},{4,0,0,0},{8,0,0,0},{16,0,0,0}}; // Column all different
	int[][] board11 = new int[][]{{2,4,128,64},{0,0,0,0},{0,0,0,0},{2,4,32,64}}; // Top and bottom rows similar
	GameT empty = new GameT();
	GameT test1 = new GameT();
	GameT test2 = new GameT();
	GameT test3 = new GameT();

	@Before
	public void setUp() {
		empty.setBoard(board1);
	}

	@After
	public void tearDown() {
		empty = null;
		test1 = null;
		test2 = null;
		test3 = null;
	}

	@Test
	public void test_getScore1() { // Combining 2 2's
		test1.setBoard(board2);
		test1.makeMove(DirectionT.right);
		assertEquals(test1.getScore(), 4);
	}

	@Test
	public void test_getScore2() { // Combining 16 2's
		test2.setBoard(board3);
		test2.makeMove(DirectionT.right);
		assertEquals(test2.getScore(), 32);
	}

	@Test
	public void test_getScore3() { // Combining none
		test3.setBoard(board8);
		test3.makeMove(DirectionT.right);
		assertEquals(test3.getScore(), 0);
	}

	@Test
	public void test_getBoard1() { // getBoard with board2
		test1.setBoard(board2);
		assertEquals(test1.getBoard(),board2);
	}

	@Test
	public void test_getBoard2() { // getBoard with board2 moved right
		test1.setBoard(board2);
		test1.makeMove(DirectionT.right);
		assertEquals(test1.getBoard(),new int[][] {{0,0,0,0},{0,0,0,4},{0,0,0,0},{0,0,0,0}});
	}

	@Test
	public void test_getBoard3() { // getBoard with empty board
		assertEquals(empty.getBoard(),new int[][] {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}});
	}

	@Test
	public void test_makeMove1() { // All moves on empty board
		empty.setBoard(board1);
		empty.makeMove(DirectionT.up);
		empty.makeMove(DirectionT.down);
		empty.makeMove(DirectionT.left);
		empty.makeMove(DirectionT.right);
		assertEquals(empty.getBoard(), board1);
	}

	@Test
	public void test_makeMove2() { // All moves on board with 2 2's
		test1.setBoard(board2);
		test1.makeMove(DirectionT.up);
		test1.makeMove(DirectionT.down);
		test1.makeMove(DirectionT.left);
		test1.makeMove(DirectionT.right);
		assertEquals(test1.getBoard(), new int[][] {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,4}});
	}

	@Test
	public void test_makeMove3() { // Left move with 2nd column missing
		test1.setBoard(board4);
		test1.makeMove(DirectionT.left);
		assertEquals(test1.getBoard(), new int[][] {{4,2,0,0},{4,2,0,0},{4,2,0,0},{4,2,0,0}});
	}

	@Test
	public void test_makeMove4() { // Right move with 3rd column missing
		test1.setBoard(board5);
		test1.makeMove(DirectionT.right);
		assertEquals(test1.getBoard(), new int[][] {{0,0,2,4},{0,0,2,4},{0,0,2,4},{0,0,2,4}});
	}

	@Test
	public void test_makeMove5() { // Up move with 2nd row missing
		test1.setBoard(board6);
		test1.makeMove(DirectionT.up);
		assertEquals(test1.getBoard(), new int[][] {{4,4,4,4},{2,2,2,2},{0,0,0,0},{0,0,0,0}});
	}

	@Test
	public void test_makeMove6() { // Down move with 3rd row missing
		test1.setBoard(board7);
		test1.makeMove(DirectionT.down);
		assertEquals(test1.getBoard(), new int[][] {{0,0,0,0},{0,0,0,0},{2,2,2,2},{4,4,4,4}});
	}

	@Test
	public void test_makeMove7() { // All moves with corner tiles set to 2
		test1.setBoard(board9);
		test1.makeMove(DirectionT.left);
		test1.makeMove(DirectionT.right);
		test1.makeMove(DirectionT.down);
		test1.makeMove(DirectionT.up);
		assertEquals(test1.getBoard(), new int[][] {{0,0,0,8},{0,0,0,0},{0,0,0,0},{0,0,0,0}});
	}

	@Test
	public void test_makeMove8() { // All moves with board9 (full losing board)
		test1.setBoard(board8);
		test1.makeMove(DirectionT.left);
		test1.makeMove(DirectionT.right);
		test1.makeMove(DirectionT.down);
		test1.makeMove(DirectionT.up);
		assertEquals(test1.getBoard(), board8);
	}

	@Test
	public void test_makeMove9() { // Left with first column all different
		test1.setBoard(board10);
		test1.makeMove(DirectionT.left);
		assertEquals(test1.getBoard(), board10);
	}

	@Test
	public void test_makeMove10() { // Left with first column all different
		test1.setBoard(board10);
		test1.makeMove(DirectionT.right);
		assertEquals(test1.getBoard(), new int[][] {{0,0,0,2},{0,0,0,4},{0,0,0,8},{0,0,0,16}});
	}

	@Test
	public void test_makeMove11() { // Up with first column all different
		test1.setBoard(board10);
		test1.makeMove(DirectionT.right);
		assertEquals(test1.getBoard(), board10);
	}

	@Test
	public void test_getScore11() { // Up with first and last row semi-equal
		test1 = new GameT();
		test1.setBoard(board10);
		test1.makeMove(DirectionT.down);
		assertEquals(test1.getScore(), 0);
	}

	@Test
	public void test_makeMove12() { // Down with first column all different
		test1 = new GameT();
		test1.setBoard(board10);
		test1.makeMove(DirectionT.right);
		assertEquals(test1.getBoard(), board10);
	}

	@Test
	public void test_getScore12() { // Up with first and last row semi-equal
		test1 = new GameT();
		test1.setBoard(board10);
		test1.makeMove(DirectionT.down);
		assertEquals(test1.getScore(), 0);
	}

	@Test
	public void test_makeMove13() { // Down with first and last row semi-equal
		test1 = new GameT();
		test1.setBoard(board11);
		test1.makeMove(DirectionT.down);
		assertEquals(test1.getBoard(), new int[][] {{0,0,0,0},{0,0,0,0},{0,0,128,0},{4,8,32,128}});
	}

	@Test
	public void test_getScore13() { // Up with first and last row semi-equal
		test1 = new GameT();
		test1.setBoard(board11);
		test1.makeMove(DirectionT.down);
		assertEquals(test1.getScore(), 140);
	}

	@Test
	public void test_makeMove14() { // Up with first and last row semi-equal
		test1 = new GameT();
		test1.setBoard(board11);
		test1.makeMove(DirectionT.up);
		assertEquals(test1.getBoard(), new int[][] {{4,8,128,128},{0,0,32,0},{0,0,0,0},{0,0,0,0}});
	}

	@Test
	public void test_getScore14() { // Up with first and last row semi-equal
		test1 = new GameT();
		test1.setBoard(board11);
		test1.makeMove(DirectionT.up);
		assertEquals(test1.getScore(), 140);
	}

	@Test
	public void test_makeMove15() { // Up with all tiles set to 2
		test1 = new GameT();
		test1.setBoard(board3);
		test1.makeMove(DirectionT.up);
		assertEquals(test1.getBoard(), new int[][] {{4,4,4,4},{4,4,4,4},{0,0,0,0},{0,0,0,0}});
	}

	@Test
	public void test_getScore15() { // Up with all tiles set to 2
		test1 = new GameT();
		test1.setBoard(board3);
		test1.makeMove(DirectionT.up);
		assertEquals(test1.getScore(), 32);
	}

	@Test
	public void test_makeMove16() { // Down with all tiles set to 2
		test1 = new GameT();
		test1.setBoard(board3);
		test1.makeMove(DirectionT.down);
		assertEquals(test1.getBoard(), new int[][] {{0,0,0,0},{0,0,0,0},{4,4,4,4},{4,4,4,4}});
	}

	@Test
	public void test_getScore16() { // Down with all tiles set to 2
		test1 = new GameT();
		test1.setBoard(board3);
		test1.makeMove(DirectionT.down);
		assertEquals(test1.getScore(), 32);
	}

	@Test
	public void test_gameover1() { // Losing board (no tile >= 2048)
		test1.setBoard(board8);
		assertFalse(test1.isWin());
	}

	@Test
	public void test_gameover2() { // Losing board (no tile >= 2048)
		test1.setBoard(board8);
		assertTrue(test1.isLose());
	}

	@Test
	public void test_gameover3() { // Non-losing board (no tile >= 2048)
		test1.setBoard(board10);
		assertFalse(test1.isWin());
	}

	@Test
	public void test_gameover4() { // Non-losing board (no tile >= 2048)
		test1.setBoard(board10);
		assertFalse(test1.isLose());
	}

	@Test
	public void test_gameover5() { // Losing board (One tile = 2048)
		test1.setBoard(new int[][] {{0,0,2048,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}});
		assertTrue(test1.isWin());
	}

	@Test
	public void test_gameover6() { // Non-losing board with 1 zero tile.
		test1 = new GameT();
		test1.setBoard(new int[][] {{2,4,8,16},{16,8,32,0},{32,16,8,128},{4,8,16,32}});
		assertFalse(test1.isLose());
	}

	@Test
	public void test_gameover7() { // Non-losing board with 1 zero tile. addTile() called to fill the gap.
		test1 = new GameT();
		test1.setBoard(new int[][] {{2,4,8,16},{16,8,32,0},{32,16,8,128},{4,8,16,32}});
		test1.makeMove(DirectionT.up);
		test1.addTile();
		assertTrue(test1.isLose());
	}

	@Test
	public void test_addTile1() { // Tiles added till full.
		test1 = new GameT();
		test1.setBoard(board1);
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		boolean all = true;
		for(int[] row : test1.getBoard()) {
			for(int tile : row) {
				if(tile==0) {
					all = false;
				}
			}
		} assertTrue(all);
	}

	@Test
	public void test_addTile2() { // Tiles added till full except 1 spot.
		test1 = new GameT();
		test1.setBoard(board1);
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		test1.addTile();
		boolean all = true;
		for(int[] row : test1.getBoard()) {
			for(int tile : row) {
				if(tile==0) {
					all = false;
				}
			}
		} assertFalse(all);
	}

}
