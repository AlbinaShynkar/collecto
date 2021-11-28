package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import utils.NetworkProvider.Model.Color;
import utils.NetworkProvider.Model.Field;

/**
 * Class BoardApplication stores all business logic that is related to board
 * class.
 * 
 * @author User
 *
 */
@SuppressWarnings("serial")
public class BoardApplication extends HashMap<Field, Color> {
	/**
	 * Stores number of each of color of Ball.
	 */
	final static int BALLS_NUMBER = 8;
	/**
	 * Stores map object.
	 */
	HashMap<Field, Color> map;
	/**
	 * Stores list of fields that are adjacent.
	 */
	ArrayList<Field> adjacent;
	// --CONSTRUCTORS--

	public BoardApplication() {
		this.map = this.generateBoard();
	}

	public BoardApplication(HashMap<Field, Color> map) {
		HashMap<Field, Color> newMap = new HashMap<>();
		for (Field field : map.keySet()) {
			Field newfield = new Field(field.getCoordinateX(), field.getCoordinateY(), field.getColor());
			newMap.put(newfield, newfield.getColor());
		}
		this.map = newMap;
	}

	// --ORIGINAL METHODS--
	public static void main(String[] args) {
		BoardApplication board = new BoardApplication();
		board.fillBoard();
		System.out.println(board.getMap());
	}
	
	public ArrayList<Integer> availableMoves() {
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for (int i = 0; i < 28; i++) {
			BoardApplication copy = new BoardApplication(map);
			userInput(i,copy);
			if (isAdjacent(copy)) {
				moves.add(i);
			}
		}
		return moves;
	}
	public boolean isAdjacent(BoardApplication board) {
		boolean flag = false;

		for (int i = 1; i < 8; i++) {
			for (int j = 0; j < 6; j++) {
				if (!board.getRow(i).get(j).getColor().equals(Color.EMPTY)) {
					if (board.getRow(i).get(j).getColor()
							.equals(board.getRow(i).get(j + 1).getColor())) {
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
	 * Creates a board with hash map of fields with balls set to empty.
	 * 
	 * @return board data structure
	 */
	public HashMap<Field, Color> generateBoard() {
		HashMap<Field, Color> board = new HashMap<>();

		for (int i = 1; i < 8; i++) {
			Field f = new Field('A', i, Color.EMPTY);
			board.put(f, f.getColor());
		}
		for (int i = 1; i < 8; i++) {
			Field f = new Field('B', i, Color.EMPTY);
			board.put(f, f.getColor());
		}
		for (int i = 1; i < 8; i++) {
			Field f = new Field('C', i, Color.EMPTY);
			board.put(f, f.getColor());
		}
		for (int i = 1; i < 8; i++) {
			Field f = new Field('D', i, Color.EMPTY);
			board.put(f, f.getColor());
		}
		for (int i = 1; i < 8; i++) {
			Field f = new Field('E', i, Color.EMPTY);
			board.put(f, f.getColor());
		}
		for (int i = 1; i < 8; i++) {
			Field f = new Field('F', i, Color.EMPTY);
			board.put(f, f.getColor());
		}
		for (int i = 1; i < 8; i++) {
			Field f = new Field('G', i, Color.EMPTY);
			board.put(f, f.getColor());
		}

		return board;
	}

	/**
	 * Converts fields to string to give visual output of a board.
	 */
	public String getSetupBoard() {
		String out = "";
		final String[] BLOCKS = { " _____   ", "|     |  ", "| [C] |  ", "|_____|  " };
		final int BLOCK_ROWS = BLOCKS.length;

		for (int i = 0; i < 7; i++) {

			ArrayList<Field> row = getRow(i);

			for (int j = 0; j < BLOCK_ROWS; j++) {

				for (int k = 0; k < row.size(); k++) {

					Field f = row.get(k);

					if (f != null) {

						out += BLOCKS[j];
						Color ball = f.getColor();
						String fillerC = "       ";

						if (ball != null) {

							fillerC = f.getColor().toString();
							if (fillerC.equals("BLUE")) {
								fillerC = " B  ";
							} else if (fillerC.equals("ORANGE")) {
								fillerC = " O ";
							} else if (fillerC.equals("RED")) {
								fillerC = " R ";
							} else if (fillerC.equals("YELLOW")) {
								fillerC = " Y ";
							} else if (fillerC.equals("GREEN")) {
								fillerC = " G ";
							} else if (fillerC.equals("PINK")) {
								fillerC = " P ";
							}

						}
						out = out.replace("[C]", fillerC);

					}

				}

				out += "\n";
			}
		}
		return out;
	}

	/**
	 * Fills the board with empty fields with balls of different color.
	 * 
	 * @return hash map of field and ball filled with balls of different colors
	 */
	public HashMap<Field, Color> fillBoard() {
		for (char alphabet = 'A'; alphabet < 'H'; alphabet++) {
			if (alphabet != 'D') {
				ArrayList<Color> color = randomColor();
				for (int j = 0; j < 6; j++) {
					getColumn(alphabet).get(j).setColor(color.get(j));
				}
			}
		}

		for (char alphabet = 'D'; alphabet < 'E'; alphabet++) {
			ArrayList<Color> color = randomColor();
			getColumn(alphabet).get(0).setColor(color.get(0));
			getColumn(alphabet).get(1).setColor(color.get(1));
			getColumn(alphabet).get(2).setColor(color.get(2));
			getColumn(alphabet).get(4).setColor(color.get(3));
			getColumn(alphabet).get(5).setColor(color.get(4));
			getColumn(alphabet).get(6).setColor(color.get(5));
		}

		for (int i = 7; i < 8; i++) {
			ArrayList<Color> color = randomColor();
			getColumn('A').get(6).setColor(color.get(0));
			getColumn('B').get(6).setColor(color.get(1));
			getColumn('C').get(6).setColor(color.get(2));
			getColumn('E').get(6).setColor(color.get(3));
			getColumn('F').get(6).setColor(color.get(4));
			getColumn('G').get(6).setColor(color.get(5));

		}

		while (adjacent()) {
			fillBoard();
		}

		return map;
	}

	public void setMap(HashMap<Field, Color> map) {
		this.map = map;
	}

	/**
	 * String representation of the board structure.
	 */
	public String toString() {

		String row1 = "    ";
		String row2 = "    ";
		String row3 = "    ";
		String row4 = "    ";
		String row5 = "    ";
		String row6 = "    ";
		String row7 = "    ";
		row1 += getStringByID("A1") + getStringByID("A2") + getStringByID("A3") + getStringByID("A4")
				+ getStringByID("A5") + getStringByID("A6") + getStringByID("A7");

		row2 += getStringByID("B1") + getStringByID("B2") + getStringByID("B3") + getStringByID("B4")
				+ getStringByID("B5") + getStringByID("B6") + getStringByID("B7");

		row3 += getStringByID("C1") + getStringByID("C2") + getStringByID("C3") + getStringByID("C4")
				+ getStringByID("C5") + getStringByID("C6") + getStringByID("C7");

		row4 += getStringByID("D1") + getStringByID("D2") + getStringByID("D3") + getStringByID("D4")
				+ getStringByID("D5") + getStringByID("D6") + getStringByID("D7");

		row5 += getStringByID("E1") + getStringByID("E2") + getStringByID("E3") + getStringByID("E4")
				+ getStringByID("E5") + getStringByID("E6") + getStringByID("E7");

		row6 += getStringByID("F1") + getStringByID("F2") + getStringByID("F3") + getStringByID("F4")
				+ getStringByID("F5") + getStringByID("F6") + getStringByID("F7");

		row7 += getStringByID("G1") + getStringByID("G2") + getStringByID("G3") + getStringByID("G4")
				+ getStringByID("G5") + getStringByID("G6") + getStringByID("G7");

		return row1 + "\n\n" + row2 + "\n\n" + row3 + "\n\n" + row4 + "\n\n" + row5 + "\n\n" + row6 + "\n\n" + row7;
	}

	/**
	 * Checks balls are not adjacent to make sure board is set up correctly.
	 * 
	 * @return true if there are adjacent balls on the board and puts them in the
	 *         adjacent map.
	 */
	public boolean adjacent() {
		boolean flag = false;

		for (int i = 1; i < 8; i++) {
			for (int j = 0; j < 6; j++) {
				if (!getRow(i).get(j).getColor().equals(Color.EMPTY)) {
					if (getRow(i).get(j).getColor().equals(getRow(i).get(j + 1).getColor())) {
						flag = true;
					}
				}
			}
		}

		for (char alphabet = 'A'; alphabet < 'H'; alphabet++) {
			for (int j = 0; j < 6; j++) {
				if (!getColumn(alphabet).get(j).getColor().equals(Color.EMPTY)) {
					if (getColumn(alphabet).get(j).getColor().equals(getColumn(alphabet).get(j + 1).getColor())) {
						flag = true;
					}
				}
			}
		}

		return flag;
	}

	// -- GETTERS AND SETTERS--
	/**
	 * Gets current map.
	 * 
	 * @return representation of the balls in fields on the board
	 */
	public HashMap<Field, Color> getMap() {
		return this.map;
	}

	/**
	 * Gets row by its string coordinate given in format e.g. "2" where 2 is row
	 * coordinate.
	 * 
	 * @requires coordinate!=null
	 * @param coordinate string with coordinate of column (eg."2") where 2 is row
	 *                   coordinate.
	 * @return row with such coordinate as were given in string coordinate.
	 */
	public ArrayList<Field> getRow(int b) {
		ArrayList<Field> fields = new ArrayList<>();
		int coorY = b;
		for (Field field : map.keySet()) {
			if (field.getCoordinateY() == coorY) {
				fields.add(field);
			}
		}
		Collections.sort(fields);
		return fields;
	}

	/**
	 * Converts getRow to string format.
	 * 
	 * @requires coordinate!=null
	 * @param coordinate string with coordinate of field (eg."2") where 2 is row
	 *                   coordinate.
	 * @ensures return!=null
	 */
	public String getStringRow(int b) {
		ArrayList<Field> field = getRow(b);
		return field.toString();
	}

	/**
	 * Gets column by its string coordinate given in format e.g. "C" where C is
	 * column coordinate.
	 * 
	 * @requires coordinate!=null
	 * @param coordinate string with coordinate of row (eg."C") where C is column
	 *                   coordinate.
	 * @return column with such coordinate as were given in string coordinate.
	 */
	public ArrayList<Field> getColumn(char a) {
		ArrayList<Field> fields = new ArrayList<>();
		char coorX = a;
		for (Field field : map.keySet()) {
			if (field.getCoordinateX() == coorX) {
				fields.add(field);
			}
		}
		Collections.sort(fields);
		return fields;
	}

	/**
	 * Converts getColumn to string format.
	 * 
	 * @requires coordinate!=null
	 * @param coordinate string with coordinate of row (eg."C") where C is column
	 *                   coordinate.
	 * @ensures return!=null
	 */
	public String getStringColumn(char a) {
		ArrayList<Field> field = getColumn(a);
		return field.toString();
	}

	/**
	 * Converts getFieldByID to string format
	 * 
	 * @requires coordinates!=null
	 * @param coordinates string with coordinates of field (e.g."12") where 1 is
	 *                    row, 2 is column.
	 * @ensures return!=null
	 */
	public String getStringByID(String coordinates) {
		Field field = getFieldByID(coordinates);
		return field.toString();
	}

	/**
	 * Gets field by its string coordinate given in format "12" where 1 is row, 2
	 * column.
	 * 
	 * @requires coordinates!=null
	 * @param coordinates string with coordinates of field (e.g."12") where 1 is
	 *                    row, 2 is column.
	 * @return field with such coordinates as were given in string coordinates
	 */
	public Field getFieldByID(String coordinates) {
		char coorX = coordinates.charAt(0);
		int coorY = Integer.parseInt(coordinates.substring(1, 2));

		for (Entry<Field, Color> f : map.entrySet()) {
			if (coorY == f.getKey().getCoordinateY() && coorX == f.getKey().getCoordinateX()) {
				return f.getKey();
			}
		}
		return null;
	}

	/**
	 * Gets all fields of board in one array list of fields.
	 * 
	 * @ensures The order of Field objects inside the returned ArrayList corresponds
	 *          to the order of the input fields.
	 * @return array of fields of a board
	 */
	public ArrayList<Field> getAllFields() {
		ArrayList<Field> fields = new ArrayList<>();
		for (int i = 0; i < map.size(); i++) {
			fields.addAll(getBoard().get(i));
		}
		return fields;
	}

	/**
	 * Gets current adjacent list of fields.
	 * 
	 * @return adjacent list of fields.
	 */
	public ArrayList<Field> getAdjacent() {
		return adjacent;
	}

	/**
	 * Gets board data structure.
	 * 
	 * @ensures The order of Field objects inside the returned ArrayList corresponds
	 *          to the order of the input fields. (Reference to the final report)
	 * @ensures return!=null
	 * @return board data structure
	 */
	public ArrayList<ArrayList<Field>> getBoard() {
		ArrayList<ArrayList<Field>> board = new ArrayList<ArrayList<Field>>();
		ArrayList<Field> f = getAllFields();
		board.add(f);
		return board;
	}

	/**
	 * Creates a list of 6 colors in a random order
	 * 
	 * @ensures that the colors in the list are in a random order each time it is called
	 * @ensures balls != null
	 * @return a list of all the colors (6) in a random order.
	 */
	public ArrayList<Color> randomColor() {
		ArrayList<Color> color = new ArrayList<Color>();
		color.add(Color.BLUE);
		color.add(Color.GREEN);
		color.add(Color.ORANGE);
		color.add(Color.PINK);
		color.add(Color.RED);
		color.add(Color.YELLOW);
		Collections.shuffle(color);
		return color;
	}

	/**
	 * moves a row up if there is one or more empty spaces in the row on the given integer.
	 * @ensures that the empty spaces are below the colored fields.
	 */
	public void moveUp(int a) {
		for (int i = 1; i < 7; i++) {
			if (!getRow(a).get(i).getColor().equals(Color.EMPTY)) {
				int count = -1;
				int truecount = 0;
				if (i + count > -1 && getRow(a).get(i + count).getColor().equals(Color.EMPTY)) {
					while (i + count > -1 && getRow(a).get(i + count).getColor().equals(Color.EMPTY)) {
						truecount = count;
						count--;
					}
				} else {
					count = 0;
				}
				Color ball = Color.EMPTY;
				ball = getRow(a).get(i).getColor();
				getRow(a).get(i).setColor(Color.EMPTY);
				getRow(a).get(i + truecount).setColor(ball);
			}
		}
	}

	/**
	 * moves a row down if there is one or more empty spaces in the row on the given integer.
	 * @ensures that the empty spaces are above the colored fields.
	 */
	public void moveDown(int a) {
		for (int i = 5; i > -1; i--) {
			if (!getRow(a).get(i).getColor().equals(Color.EMPTY)) {
				int count = 1;
				int truecount = 0;
				if (i + count < 7 && getRow(a).get(i + count).getColor().equals(Color.EMPTY)) {
					while (i + count < 7 && getRow(a).get(i + count).getColor().equals(Color.EMPTY)) {
						truecount = count;
						count++;
					}
				} else {
					count = 0;
				}
				Color ball = Color.EMPTY;
				ball = getRow(a).get(i).getColor();
				getRow(a).get(i).setColor(Color.EMPTY);
				getRow(a).get(i + truecount).setColor(ball);
			}
		}
	}

	/**
	 * moves a row to the left if there is one or more empty spaces in the row on the given character.
	 * @ensures that the empty spaces are to the right of the colored fields.
	 */
	public void moveLeft(char a) {
		for (int i = 1; i < 7; i++) {
			if (!getColumn(a).get(i).getColor().equals(Color.EMPTY)) {
				int count = -1;
				int truecount = 0;
				if (count + i > -1 && getColumn(a).get(i + count).getColor().equals(Color.EMPTY)) {
					while (i + count > -1 && getColumn(a).get(i + count).getColor().equals(Color.EMPTY)) {
						truecount = count;
						count--;
					}
				} else {
					count = 0;
				}
				Color ball = getColumn(a).get(i).getColor();
				getColumn(a).get(i).setColor(Color.EMPTY);
				getColumn(a).get(i + truecount).setColor(ball);
			}
		}
	}

	/**
	 * moves a row to the right if there is one or more empty spaces in the row on the given character.
	 * @ensures that the empty spaces are to the left of the colored fields.
	 */
	public void moveRight(char a) {
		for (int i = 5; i > -1; i--) {
			if (!getColumn(a).get(i).getColor().equals(Color.EMPTY)) {
				int count = 1;
				int truecount = 0;
				if (i + count < 7 && getColumn(a).get(i + count).getColor().equals(Color.EMPTY)) {
					while (i + count < 7 && getColumn(a).get(i + count).getColor().equals(Color.EMPTY)) {
						truecount = count;
						count++;
					}
				} else {
					count = 0;
				}
				Color ball = getColumn(a).get(i).getColor();
				getColumn(a).get(i).setColor(Color.EMPTY);
				getColumn(a).get(i + truecount).setColor(ball);
			}
		}
	}

	/**
	 * puts all the fields that are in the adjacent variable into empty fields
	 * @ensures the fields in the adjacent variable to be empty
	 * @ensures f.getColor.equals(Colors.EMPTY) in adjacent list
	 * @ensures adjacent list to be cleared after the method is called
	 */
	public void replaceAdjacent() {
		for (Field f : adjacent) {
			f.setColor(Color.EMPTY);
		}
		adjacent.clear();
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

}
