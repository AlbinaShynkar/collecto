package modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import application.BoardApplication;
import utils.NetworkProvider.Model.Color;
import utils.NetworkProvider.Model.Field;

public class BoardTest {
	BoardApplication board = new BoardApplication();

	@Test
	public void generateBoardTest() {

		board.generateBoard();
		
		for (char alphabet = 'A'; alphabet < 'H'; alphabet++) {
		assertEquals(7, board.getColumn(alphabet).size());
		}
		
		for (int i = 1; i < 8; i++) {
			assertEquals(7, board.getRow(i).size());
		}
	}
	
	@Test
	public void getFieldByIDTest() {
		Field  f = new Field('A', 1, Color.EMPTY);
		Field field = board.getFieldByID("A1");
		
		assertEquals(f.toString(), field.toString());
	}
	
	@Test
	public void fillBoardTest() {
		board.fillBoard();
		assertEquals(board.isAdjacent(board), false);
	}
	
	@Test
	public void randomBallsTest() {
		ArrayList<Color> test = board.randomColor();
		boolean bool = true;
		for (int i = 0; i < 5; i++) {
			if (test.get(i).equals(test.get(i+1))) {
				bool = false;
			}
		}
		assertEquals(bool, true);
	}
	
	@Test
	public void moveUpTest() {
		board.fillBoard();
		assertEquals(board.getFieldByID("G4").getColor().equals(Color.EMPTY), false);
		board.moveUp(4);
		assertEquals(board.getFieldByID("G4").getColor().equals(Color.EMPTY), true);
	}
	
	@Test
	public void moveDownTest() {
		board.fillBoard();
		assertEquals(board.getFieldByID("A4").getColor().equals(Color.EMPTY), false);
		board.moveDown(4);
		assertEquals(board.getFieldByID("A4").getColor().equals(Color.EMPTY), true);
	}
	
	@Test
	public void moveLeftTest() {
		board.fillBoard();
		assertEquals(board.getFieldByID("D7").getColor().equals(Color.EMPTY), false);
		board.moveLeft('D');
		assertEquals(board.getFieldByID("D7").getColor().equals(Color.EMPTY), true);
	}
	
	@Test
	public void moveRightTest() {
		board.fillBoard();
		assertEquals(board.getFieldByID("D1").getColor().equals(Color.EMPTY), false);
		board.moveRight('D');
		assertEquals(board.getFieldByID("D1").getColor().equals(Color.EMPTY), true);
	}
}
