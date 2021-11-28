package modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import application.BoardApplication;
import application.GameApplication;
import utils.NetworkProvider.Model.Color;
import utils.NetworkProvider.Model.Player;

public class GameTest {
	GameApplication game = new GameApplication(new Player("Abdel"), new Player("Albina"), null);
	
	@Test
	public void PlayerTest() {
		assertEquals(game.getPlayers()[0].getName(), "Abdel");
		assertEquals(game.getPlayers()[1].getName(), "Albina");
	}
	
	@Test
	public void gameBoardTest() {
		boolean test = true;
		for (char alphabet = 'A'; alphabet < 'H'; alphabet++) {
			for (int i = 0; i < 7; i++) {
				if (!game.getBoard().getColumn(alphabet).get(i).getColor().equals(Color.EMPTY)) {
					test = false;
				}
			}
		}
		assertEquals(test, true);
		
		game.fillBoard();
		
		for (char alphabet = 'A'; alphabet < 'H'; alphabet++) {
			for (int i = 0; i < 7; i++) {
				if (!game.getBoard().getColumn(alphabet).get(i).getColor().equals(Color.EMPTY)) {
					test = false;
				}
			}
		}
		assertEquals(test, false);
		
		game.resetBoard(game.getBoard());
		
		test = true;
		for (char alphabet = 'A'; alphabet < 'H'; alphabet++) {
			for (int i = 0; i < 7; i++) {
				if (!game.getBoard().getColumn(alphabet).get(i).getColor().equals(Color.EMPTY)) {
					test = false;
				}
			}
		}
		assertEquals(test, true);
	}
	
	@Test
	public void currentTest() {
		assertEquals(game.getCurrent(), 0);
		game.currentInc(game.getCurrent());
		assertEquals(game.getCurrent(), 1);
		game.currentInc(game.getCurrent());
		assertEquals(game.getCurrent(), 0);
	}
	
	@Test
	public void userInputUpTest() {
		game.getBoard().fillBoard();
		assertEquals(game.getBoard().getFieldByID("G4").getColor().equals(Color.EMPTY), false);
		game.userInput(17, game.getBoard());;
		assertEquals(game.getBoard().getFieldByID("G4").getColor().equals(Color.EMPTY), true);
	}
	
	@Test
	public void userInputDownTest() {
		game.getBoard().fillBoard();
		assertEquals(game.getBoard().getFieldByID("A4").getColor().equals(Color.EMPTY), false);
		game.userInput(24, game.getBoard());
		assertEquals(game.getBoard().getFieldByID("A4").getColor().equals(Color.EMPTY), true);
	}
	
	@Test
	public void userInputLeftTest() {
		game.getBoard().fillBoard();
		assertEquals(game.getBoard().getFieldByID("D7").getColor().equals(Color.EMPTY), false);
		game.userInput(3, game.getBoard());
		assertEquals(game.getBoard().getFieldByID("D7").getColor().equals(Color.EMPTY), true);
	}
	
	@Test
	public void userInputRightTest() {
		game.getBoard().fillBoard();
		assertEquals(game.getBoard().getFieldByID("D1").getColor().equals(Color.EMPTY), false);
		game.userInput(10, game.getBoard());
		assertEquals(game.getBoard().getFieldByID("D1").getColor().equals(Color.EMPTY), true);
	}
	
	@Test
	public void makeComputerMoveTest() {
		game.fillBoard();
		BoardApplication copy = new BoardApplication(game.getBoard().getMap());
		assertEquals(copy, game.getBoard());
		
		game.makeComputerMove(game.getPlayers()[0], game.getBoard());
		assertEquals(copy, game.getBoard());
	}
	
}
