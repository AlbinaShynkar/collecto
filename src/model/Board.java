package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Contains virtual representation of a board and responsible for
 * generating information and values about the status of the board.
 * @author group other-6.4
 */
public class Board {
	/**
	 * stores constant number of players for the game.
	 */
	public static int numOfPlayers = 2;
	/**
	 * One to one map matching fields to its contents.
	 */
	HashMap<Field, Ball>  map;
	
	// --CONSTRUCTORS--
	
	public Board(int numOfPlayers) {
		//TO-DO
	}
	
	// --ORIGINAL METHODS--
	/**
	 * Creates a board with hash map of fields with balls set to empty.
	 * 	Is called by constructor.
	 * @return board data structure
	 */
	public HashMap<Field, Ball> generateBoard(){
		//TO-DO
		return map;
	}
	
	/**
	 * Converts fields to string to give visual output of a board.
	 */
	public String toString() {
		//TO-DO
		return null;
	}
	
	/**
	 * Checks balls are not adjacent to make sure board is set up correctly.
	 */
	public boolean adjacent() {
		//TO-DO
		return false;
	}
	
	/**
	 * Checks whether the board had valid setup.
	 * @ensures there are exactly 8 of balls of each color on board
	 * @ensures central field of board is empty
	 */
	public boolean validSetup() {
		//TO-DO
		return false;
	}
	
	/**
	 * Check whether there are adjacent balls on horizontal
	 * positions anywhere on board after move.
	 */
	public boolean hasHorizontal() {
		return false;
	}
	
	/**
	 * Check whether there are adjacent balls on vertical
	 * positions anywhere on board after move.
	 */
	public boolean hasVertical() {
		return false;
	}
	
	// -- GETTERS AND SETTERS--
	
	/**
	   * Gets field by its string coordinate given in format 
	   * "C2B" where C is row, 2 column, B-blue color of ball.
	   * @requires coordinates!=null
	   * @param coordinates string with coordinates of field (eg."C2B") 
	   * 	where C is row, 2 is column, B is color(blue)
	   * @return field with such coordinates as were given in string coordinates.
	   */
	  public Field getFieldByID(String coordinates) {
		  //TO-DO
			return null;
	  }
	  /**
	   * Converts getFieldByID to string format.
	   * @requires coordinates!=null
	   * @param coordinates string with coordinates of field (eg."C2B") 
	   * 	where C is row, 2 is column, B is color(blue)
	   * @ensures return!=null
	   */
	  public String getStringByID(String coordinates) {
		  Field field= getFieldByID(coordinates);
		  return field.toString();
	  }
	  
	  /**
	   * Gets ball by its coordinate given in string format
	   * @requires coordinates!=null
	   * @param coordinated string with coordinates of field (eg."C2B") 
	   * 	where C is row, 2 is column, B is color(black)
	   * @return ball with such coordinates as were given in string coordinates
	   */
	  public Ball getBallByID(String coordinates) {
		  //TO-DO
		  return null;
	  }
	  
	  /**
	   * Gets all fields of board in one array list of fields.
	   * @ensures The order of Field objects inside the returned ArrayList corresponds 
	   * 	to the order of the input fields.
	   * @return array of fields of a board 
	   */
	  public ArrayList<Field> getAllFields(){
		  //TO-DO
		return null;
	  }

	  /**
	   * Gets board data structure.
	   * @ensures The order of Field objects inside the returned ArrayList corresponds 
	   * 	to the order of the input fields. (Reference to the final report)
	   * @ensures return!=null
	   * @return board data structure
	   */
	  public ArrayList<ArrayList<Field>> getBoard(){
		  //TO-DO
		  return null;
	  }



}
