package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import utils.NetworkProvider.Model.Color;
import utils.NetworkProvider.Model.ComputerPlayer;
import utils.NetworkProvider.Model.ComputerPlayerSmart;
import utils.NetworkProvider.Model.Field;
import utils.NetworkProvider.Model.Player;

public class GameApplication {
	public static void main(String[] args) throws IOException {
		GameApplication game = new GameApplication(new ComputerPlayer("Abdel"), new ComputerPlayerSmart("Emre"));
		game.start();
		
	}

	/**
	 * stores constant number of players for the game.
	 */
	public final int numOfPlayers = 2;

	/**
	 * Stores players joined the game in array list of players
	 */
	private Player[] players;

	/**
	 * stores current player, which changes after every move
	 */
	private int current;

	/**
	 * Whether game is started or not
	 */
	public ArrayList<Field> adjacent;

	/**
	 * Holds board of the game
	 */
	private BoardApplication board;

	// -----CONSTRUCTOR------
	/**
	 * Constructor of the game
	 * 
	 * @ensures numOfPlayers =2
	 * @param numberOfPlayers
	 * @throws NumberOfPlayersException
	 */
	public GameApplication(Player s0, Player s1) {
		this.board = new BoardApplication();
		this.players = new Player[this.numOfPlayers];
		this.players[0] = s0;
		this.players[1] = s1;
		this.current = 0;
		this.adjacent = new ArrayList<Field>();
	}

	/**
	 * Starts the game
	 * @throws IOException 
	 * @requires amount of players in the arraylist needs to be 2
	 */
	public void start() throws IOException {
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(in);
		boolean continueGame = true;
		while (continueGame) {
			fillBoard();
			play();
			continueGame = false;
		}
		System.out.println("\\\\n> Play another time? (y/n)");
		String input = br.readLine();
		continueGame = Boolean.parseBoolean(input);
	}

	/**
	 * gives turns to the players and computer players and stops when there are no moves or double moves left
	 * when the game is over it prints the final results and who won.
	 * @param amount of players in the ArrayList needs to be 2
	 * @throws IOException 
	 */
	private void play() throws IOException {
		System.out.println("\n Welcome to collecto, if you want to make a move type an integer.");
		this.current = 0;

		while (availableMoves().size() > 0) {
			if (current == 0) {
				if (players[0] instanceof ComputerPlayerSmart) {
					makeComputerMoveSmart(players[0], this.board);
				} else if (players[0] instanceof ComputerPlayer) {
					makeComputerMove(players[0], this.board);
				} else {
					makeMove(players[0], this.board);
				}
			}
			if (current == 1) {
				if (players[1] instanceof ComputerPlayerSmart) {
					makeComputerMoveSmart(players[1], this.board);
				} else if (players[1] instanceof ComputerPlayer) {
					makeComputerMove(players[1], this.board);
				} else {
					makeMove(players[1], this.board);
				}
			}
		}
		if (availableDoubleMoves().size() > 0) {
			if (current == 0) {
				if (players[0] instanceof ComputerPlayerSmart) {
					makeComputerDoubleMove(players[0], this.board);
				} else if (players[0] instanceof ComputerPlayer) {
					makeComputerDoubleMove(players[0], this.board);
				} else {
					makeDoubleMove(players[0], this.board);
				}
			}
			if (current == 1) {
				if (players[1] instanceof ComputerPlayerSmart) {
					makeComputerDoubleMove(players[1], this.board);
				} else if (players[1] instanceof ComputerPlayer) {
					makeComputerDoubleMove(players[1], this.board);
				} else {
					makeDoubleMove(players[1], this.board);
				}
			}
		}
		update(this.board);
		printResult();
	}

	/**
	 * Prints the current status of a board
	 * @param a board you want the status of
	 * @requires board != null
	 */
	private void update(BoardApplication board) {
		System.out.println("\ncurrent game situation: \n\n" + board.toString());
	}

	/**
	 * fills the board to a valid beginners state board
	 * @requires board != null
	 * @return valid beginners state board
	 */
	public BoardApplication fillBoard() {
		for (char alphabet = 'A'; alphabet < 'H'; alphabet++) {
			if (alphabet != 'D') {
				ArrayList<Color> balls = board.randomColor();
				for (int j = 0; j < 6; j++) {
					board.getColumn(alphabet).get(j).setColor(balls.get(j));
				}
			}
		}

		for (char alphabet = 'D'; alphabet < 'E'; alphabet++) {
			ArrayList<Color> color = board.randomColor();
			board.getColumn(alphabet).get(0).setColor(color.get(0));
			board.getColumn(alphabet).get(1).setColor(color.get(1));
			board.getColumn(alphabet).get(2).setColor(color.get(2));
			board.getColumn(alphabet).get(4).setColor(color.get(3));
			board.getColumn(alphabet).get(5).setColor(color.get(4));
			board.getColumn(alphabet).get(6).setColor(color.get(5));
		}

		for (int i = 7; i < 8; i++) {
			ArrayList<Color> color = board.randomColor();
			board.getColumn('A').get(6).setColor(color.get(0));
			board.getColumn('B').get(6).setColor(color.get(1));
			board.getColumn('C').get(6).setColor(color.get(2));
			board.getColumn('E').get(6).setColor(color.get(3));
			board.getColumn('F').get(6).setColor(color.get(4));
			board.getColumn('G').get(6).setColor(color.get(5));

		}

		while (isAdjacent(this.board)) {
			resetBoard(this.board);
			fillBoard();
		}
		return this.board;
	}

	/**
	 * resets the board to an empty board
	 * @requires board != null
	 * @return empty board
	 */
	public BoardApplication resetBoard(BoardApplication board) {
		for (char alphabet = 'A'; alphabet < 'H'; alphabet++) {
			for (int j = 0; j < 7; j++) {
				board.getColumn(alphabet).get(j).setColor(Color.EMPTY);
			}
		}
		return board;
	}

	/**
	 * Checks balls are not adjacent to make sure board is set up correctly or check if a move is valid.
	 * @return true if there are adjacent balls on the board and puts them in the adjacent map.
	 * @requires board != null
	 */
	public boolean isAdjacent(BoardApplication board) {
		boolean flag = false;

		for (int i = 1; i < 8; i++) {
			for (int j = 0; j < 6; j++) {
				if (!board.getRow(i).get(j).getColor().equals(Color.EMPTY)) {
					if (board.getRow(i).get(j).getColor().equals(board.getRow(i).get(j + 1).getColor())) {
						flag = true;
					}
				}
			}
		}

		for (char alphabet = 'A'; alphabet < 'H'; alphabet++) {
			for (int j = 0; j < 6; j++) {
				if (!board.getColumn(alphabet).get(j).getColor().equals(Color.EMPTY)) {
					if (board.getColumn(alphabet).get(j).getColor()
							.equals(board.getColumn(alphabet).get(j + 1).getColor())) {
						flag = true;
					}
				}
			}
		}

		return flag;
	}

	/**
	 * Checks balls are not adjacent to make sure board is set up correctly or check if a move is valid and returns the adjacent balls in a list.
	 * @return returns a list of all the adjacent balls in the board.
	 * @requires board != null
	 */
	public ArrayList<Field> listAdjacent(BoardApplication board) {

		for (int i = 1; i < 8; i++) {
			for (int j = 0; j < 6; j++) {
				if (!board.getRow(i).get(j).getColor().equals(Color.EMPTY)) {
					if (board.getRow(i).get(j).getColor().equals(board.getRow(i).get(j + 1).getColor())) {
						adjacent.add(board.getRow(i).get(j));
						adjacent.add(board.getRow(i).get(j + 1));
					}
				}
			}
		}

		for (char alphabet = 'A'; alphabet < 'H'; alphabet++) {
			for (int j = 0; j < 6; j++) {
				if (!board.getColumn(alphabet).get(j).getColor().equals(Color.EMPTY)) {
					if (board.getColumn(alphabet).get(j).getColor()
							.equals(board.getColumn(alphabet).get(j + 1).getColor())) {
						adjacent.add(board.getColumn(alphabet).get(j));
						adjacent.add(board.getColumn(alphabet).get(j + 1));
					}
				}
			}
		}
		removeDuplicates(adjacent);
		return adjacent;
	}

	/**
	 * makes all the fields in the adjacent variable empty which makes them empty on the board
	 * @requires adjacent != null
	 */
	public void replaceAdjacent() {
		for (Field field : adjacent) {
			field.setColor(Color.EMPTY);
		}
		adjacent.clear();
	}

	public void adjacentToPlayer(Player player, BoardApplication board) {
		listAdjacent(board);
		for (Field field : adjacent) {
			Field newfield = new Field(field.getCoordinateX(), field.getCoordinateY(), field.getColor());
			player.getField().add(newfield);
		}
		replaceAdjacent();
	}

	/**
	 * Function to remove duplicates from an ArrayList
	 * @ensures the list to not have duplicates by putting it in a set and returning it in another list
	 * @return a list without duplicates
	 */
	public static ArrayList<Field> removeDuplicates(ArrayList<Field> list) {

		// Create a new LinkedHashSet
		Set<Field> set = new LinkedHashSet<>();

		// Add the elements to set
		set.addAll(list);

		// Clear the list
		list.clear();

		// add the elements of set
		// with no duplicates to the list
		list.addAll(set);

		// return the list
		return list;
	}

	/**
	 * Uses an integer to make a move on a board. 
	 * @ensures the integer input gets translated to a move on the board.
	 * @param integer that gets translated to a move and the board the move needs to be made on
	 * @requires board != null && a != null
	 */
	public void userInput(int a, BoardApplication board) {
		if (a < 7 && a >= 0) {
			int input = a + 1;
			board.moveLeft(intToChar(input));
		}
		if (a >= 7 && a < 14) {
			int input = a - 6;
			board.moveRight(intToChar(input));
		}
		if (a >= 14 && a < 21) {
			int input = a - 13;
			board.moveUp(input);
		}
		if (a >= 21 && a < 28) {
			int input = a - 20;
			board.moveDown(input);
		}
	}

	/**
	 * Translates a integer to a char used for the userinput to translate a move from int to char
	 * @ensures the integer to become a char
	 */
	public char intToChar(int input) {
		char result = 'a';
		char a = 'A';
		for (int i = 1; i < 27; i++) {
			if (i == input) {
				result = a;
			}
			a++;
		}
		return result;
	}

	/**
	 * shows all the available moves possible on the current board in a ArrayList
	 * @ensures ensures the board doesn't change when the new moves are being tried
	 * @requires this.board != null
	 * @return list of all the possible moves on the current board
	 */
	public ArrayList<Integer> availableMoves() {
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for (int i = 0; i < 28; i++) {
			BoardApplication copy = new BoardApplication(this.board.getMap());
			userInput(i, copy);
			if (isAdjacent(copy)) {
				moves.add(i);
			}
		}
		return moves;
	}

	public ArrayList<Integer> availableMovesSmart() {
		ArrayList<Integer> moves = new ArrayList<Integer>();
		int count = 1;
		int move = -1;
		for (int i = 0; i < 28; i++) {
			BoardApplication copy = new BoardApplication(this.board.getMap());
			userInput(i, copy);
			if (listAdjacent(copy).size() > count) {
				count = listAdjacent(copy).size();
				move = i;
				adjacent.clear();
			}
			moves.add(move);
		}
		return moves;
	}
	
	/**
	 * shows all the available double moves possible on the current board in a HashMap
	 * @ensures ensures the board doesn't change when the new moves are being tried
	 * @requires this.board != null
	 * @return a HashMap with the key being an integer of the first move available and the value being the second move available
	 */
	public HashMap<Integer, Integer> availableDoubleMoves() {
		HashMap<Integer, Integer> moves = new HashMap<Integer, Integer>();
		for (int i = 0; i < 28; i++) {
			BoardApplication copy = new BoardApplication(this.board.getMap());
			userInput(i, copy);
			for (int j = 0; j < 28; j++) {
				userInput(j, copy);
				if (isAdjacent(copy)) {
					moves.put(i, j);
				}
			}
		}
		return moves;
	}

	/**
	 * gives a player the turn in the game
	 * @ensures ensures the move only gets made if it results in adjacent balls else it will give the player another chance until it does result in adjacent balls
	 * @requires player != null && board != null
	 */
	public void makeMove(Player player, BoardApplication board) throws IOException {
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(in);
		boolean successful = false;
		this.update(board);
		while (!successful) {
			System.out.println(board.availableMoves());
			System.out.println(player.getName() + " what is your move? (integer between 0 and 27)");
			String choice = br.readLine();
			int integer = Integer.parseInt(choice);
			if (availableMoves().contains(integer)) {
				System.out.println("Move has been succesful");
				userInput(integer, board);
				adjacentToPlayer(player, board);
				currentInc(current);
				successful = true;
			} else {
				System.out.println("Your move needs to result in adjacent balls!");
				if (availableMoves().size() == 0) {
					successful = true;
				}
			}
		}
	}

	/**
	 * gives a player two turns in the game if there are no single moves available
	 * @ensures ensures the two moves only get made if it results in adjacent balls else it will give the player another chance until they do result in adjacent balls
	 * @requires player != null && board != null && availableMoves.size() == 0
	 */
	public void makeDoubleMove(Player player, BoardApplication board) throws IOException {
		if (availableDoubleMoves().size() > 0) {
			HashMap<Integer, Integer> dmoves = new HashMap<Integer, Integer>();
			dmoves = availableDoubleMoves();
			InputStreamReader in = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(in);
			boolean successful = false;
			this.update(board);
			while (!successful) {
				System.out.println(availableDoubleMoves());
				System.out.println(players[0].getName()
						+ " what is your first integer for the double move? (no single move available) (integer between 0 and 27)");
				String choice = br.readLine();
				int integer = Integer.parseInt(choice);
				if (dmoves.containsKey(integer)) {
					System.out.println("Move by " + player.getName() + " has been made.");
					userInput(integer, board);
					update(board);
					System.out.println(players[0].getName()
							+ " what is your second integer for the double move? (no single move available) (integer between 0 and 27)");
					InputStreamReader in2 = new InputStreamReader(System.in);
					BufferedReader br2 = new BufferedReader(in2);
					String schoice = br2.readLine();
					int integer2 = Integer.parseInt(schoice);
					if (dmoves.get(integer).equals(integer2)) {
						System.out.println("Move by " + player.getName() + " has been made.");
						userInput(integer2, board);
						update(board);
						adjacentToPlayer(players[0], board);
						currentInc(current);
						successful = true;
					} else {
						System.out.println("This second move will not result in a double move!");
						if (availableDoubleMoves().size() == 0) {
							successful = true;
						}
					}
				} else {
					System.out.println("This first move will not result in a double move!");
					if (availableDoubleMoves().size() == 0) {
						successful = true;
					}
				}
			}
		} else {
			System.out.println("There are no moves left the game is over.");
		}
	}

	/**
	 * prints the winner of the game with how many points and balls the player has, if it is a draw it will print a draw.
	 * @ensures one of the players wins or a draw
	 * @requires players[] != null && player.getField() != null
	 */
	public void printResult() {
		int totalballs1 = awardPoints(players[0]);
		int totalballs2 = awardPoints(players[1]);
		if (players[0].getPoints() > players[1].getPoints()) {
			System.out.println("Player " + players[0].getName() + " won with " + players[0].getPoints() + " points and "
					+ totalballs1 + " balls.");
		}
		if (players[1].getPoints() > players[0].getPoints()) {
			System.out.println("Player " + players[1].getName() + " won with " + players[1].getPoints() + " points and "
					+ totalballs2 + " balls.");
		}
		if (totalballs1 != totalballs2) {
			if (totalballs1 > totalballs2) {
				System.out.println("Player " + players[0].getName() + " won with " + players[0].getPoints()
						+ " points and " + totalballs1 + " balls.");
			}
			if (totalballs2 > totalballs1) {
				System.out.println("Player " + players[1].getName() + " won with " + players[1].getPoints()
						+ " points and " + totalballs2 + " balls.");
			}
		} else {
			System.out.println("It is a draw, both players have equal number of points and balls.");
		}
	}

	/**
	 * counts the amount times the player has one color, they get these colors when they get them adjacent in the game
	 * @requires p.getField != null
	 * @param player and one possible color of the field
	 * @return the count of that color of the persons colors they got from the game
	 */
	public int countColor(Player p, Color c) {
		int count = 0;
		for (Field field : p.getField()) {
			if (field.getColor().equals(c)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * counts the points and the colors left of a person at the time this method is called
	 * @requires p != null
	 * @param player
	 * @return total colors the specific player has
	 */
	public int awardPoints(Player p) {
		int bcount = countColor(p, Color.BLUE);
		int rcount = countColor(p, Color.RED);
		int ocount = countColor(p, Color.ORANGE);
		int pcount = countColor(p, Color.PINK);
		int gcount = countColor(p, Color.GREEN);
		int ycount = countColor(p, Color.YELLOW);
		int total = bcount + rcount + ocount + pcount + gcount + ycount;

		while (bcount >= 3) {
			p.increasePoints();
			bcount = bcount - 3;
		}

		while (rcount >= 3) {
			p.increasePoints();
			rcount = rcount - 3;
		}
		while (ocount >= 3) {
			p.increasePoints();
			ocount = ocount - 3;
		}
		while (pcount >= 3) {
			p.increasePoints();
			pcount = pcount - 3;
		}
		while (gcount >= 3) {
			p.increasePoints();
			gcount = gcount - 3;
		}

		while (ycount >= 3) {
			p.increasePoints();
			ycount = ycount - 3;
		}
		return total;
	}

	/**
	 * gives the computer player a turn, which makes a random move out of the availableMoves list which is always valid
	 * @requires p != null && board != null
	 * @param player (instance of ComputerPlayer) and a board the move needs to be made on
	 */
	public void makeComputerMove(Player p, BoardApplication board) {
		ArrayList<Integer> moves = new ArrayList<Integer>();
		if (availableMoves().size() > 0) {
		moves = availableMoves();
		boolean successful = false;
		this.update(board);
		while (!successful) {
			Collections.shuffle(moves);
			System.out.println(p.getName() + " what is your move? (integer between 0 and 27)");
			System.out.println(moves.get(0));
			userInput(moves.get(0), board);
			if (!isAdjacent(board)) {
				System.out.println("Your move needs to result in adjacent balls!");
			} else {
				System.out.println("Move has been succesful");
				adjacentToPlayer(p, board);
				update(board);
				currentInc(current);
				successful = true;
			}
		}
		}
	}

	/**
	 * gives the computer player two moves, which makes a random move out of the the keyset of availableDoubleMoves and with that number of keysat it gets the value for the second move 
	 * @requires p != null && board != null
	 * @param player (instance of ComputerPlayer) and a board the move needs to be made on
	 */
	public void makeComputerDoubleMove(Player p, BoardApplication board) {
		if (availableDoubleMoves().size() > 0) {
			HashMap<Integer, Integer> dmoves = new HashMap<Integer, Integer>();
			dmoves = availableDoubleMoves();
			ArrayList<Integer> firstmove = new ArrayList<Integer>(dmoves.keySet());
			boolean successful = false;
			this.update(board);
			while (!successful) {
				Collections.shuffle(firstmove);
				System.out.println(p.getName()
						+ " what is your first integer for the double move? (no single move available) (integer between 0 and 27)");
				if (dmoves.containsKey(firstmove.get(0))) {
					System.out.println(firstmove.get(0));
					System.out.println("Move by " + p.getName() + " has been made.");
					userInput(firstmove.get(0), board);
					update(board);
					System.out.println(p.getName()
							+ " what is your second integer for the double move? (no single move available) (integer between 0 and 27)");
					int secondmove = dmoves.get(firstmove.get(0));
					if (dmoves.get(firstmove.get(0)).equals(secondmove)) {
						System.out.println(secondmove);
						System.out.println("Move by " + p.getName() + " has been made.");
						userInput(secondmove, board);
						adjacentToPlayer(p, board);
						update(board);
						currentInc(current);
						successful = true;
					} else {
						System.out.println("This second move will not result in a double move!");					}

				} else {
					System.out.println("This first move will not result in a double move!");
				}
			}
		} else {
			System.out.println("There are no moves left the game is over.");
		}
	}
	
	public void makeComputerMoveSmart(Player p, BoardApplication board) {
		ArrayList<Integer> moves = new ArrayList<Integer>();
		if (availableMovesSmart().size() > 0 && availableMovesSmart().get(0) != -1){
		moves = availableMovesSmart();
		boolean successful = false;
		this.update(board);
		while (!successful) {
			Collections.shuffle(moves);
			System.out.println(p.getName() + " what is your move? (integer between 0 and 27)");
			System.out.println(moves.get(0));
			userInput(moves.get(0), board);
			if (!isAdjacent(board)) {
				System.out.println("Your move needs to result in adjacent balls!");
			} else {
				System.out.println("Move has been succesful");
				adjacentToPlayer(p, board);
				update(board);
				currentInc(current);
				successful = true;
			}
		}
		} else {
			makeComputerMove(p, board);
		}
	}
	
	/**
	 * Calculates which player is next.
	 * @param curr index of current player.
	 * @return the next player.
	 */
	public int currentInc(int curr) {
		if (current == 1) {
			current = 0;
		} else if (current == 0) {
			current = 1;
		}
		return current;
	}

	// -----GETTERS AND SETTERS-------

	public int getNumOfPlayers() {
		return numOfPlayers;
	}

	public void setBoard(BoardApplication board) {
		this.board = board;
	}

	public BoardApplication getBoard() {
		return this.board;
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	public int getCurrent() {
		return current;
	}

}
