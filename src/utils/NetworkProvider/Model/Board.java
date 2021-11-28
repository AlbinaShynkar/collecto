package utils.NetworkProvider.Model;

import java.util.HashMap;



public class Board {
	/**
	 * One to one map matching fields to its contents.
	 */
	private HashMap<Field, Color> map;

	// --CONSTRUCTORS--

	public Board(HashMap<Field, Color> map) {
		this.map = map;
	}
	// --ORIGINAL METHODS--

	// -- GETTERS AND SETTERS--

	public HashMap<Field, Color> getMap(){
		return this.map;
	}
}
