package model;

import java.util.ArrayList;
import java.util.Arrays;

import model.Ball.Colors;

public class Field {
	/**
	 * Stores static array list of string with letters which represent rows on board 	
	 */
	public static final ArrayList<String> characters = 
			new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G"));
  /**
* Stored character value of X.
* @requires Stored character value of X must be of the values A,B,C,D,E,F,G (capital)
*/
  private char coorX;
  /**
* It is assumed that the value Y matches value of X. 
* @requires Stored value of Y value must be : 1<=y<=7.
*/
  private int coorY;
	/**
	 * Stores marble, which has a color
	 */
  private  Ball ball;
  
 //  --CONSTRUCTORS--
  /**
* Creates an empty field with given coordinates. 
* @param CoorX
* @param CoorY
* @param marble
*/
	public Field(char CoorX, int CoorY, Ball ball) {
		this.coorX = CoorX;
		this.coorY = CoorY;
		this.ball= ball;
	}
	
//  --ORIGINAL-METHODS--
	/**
	 * Converts the x and y representations into a string representation for visual output.
	 */
	public String toString(){
		String x = String.valueOf(this.coorX);
		String y = String.valueOf(this.coorY);
		return x+y+ball.toString()+"    ";
	}
	
	/**
	 * Checks whether given field has marble with color empty
	 * @return true if marble in field is colored empty
	 */
	public static boolean isEmpty(Field field) {
		if(field.getBall().getColor()==Colors.EMPTY) {
			return true;
		}
		return false;
	}
	/**
	 * Checks whether field has ball with color inside(not Colors.EMPTY)
	 * @return true if given field has colored ball , false if field has ball with color empty inside
	 */
	public boolean hasBall(Field field) {
		if(field.getBall().getColor()!=Colors.EMPTY) {
			return true;
		}
		return false;
	}
	/**
	 * Increases the value of this.x in alphabetical order (eg. B -> A)
	 * @ensures this.x incremented by one in alphabetical order
	 */

	public char upRow() {
		int code = (int)this.coorX;
		code--;
		char result = (char) code;
		return result;

	}

	/**
	 * Decreases the value of this.x in alphabetical order (eg. A -> B) 
	 * @ensures this.x decremented by one in alphabetical order
	 */
	public char downRow(){
		int code = (int)this.coorX;
		code++;
		char result = (char) code;
		return result;
	}
	
	/**
	 * Decrement the value of this.y by 1;
	 * @ensures this.y decremented by 1
	 */
	public int leftColumn(){
		return this.coorY-1;
	}
	
	/**
	 * Increment the value of this.y by 1;
	 * @ensures this.y incremented by 1
	 */
	public int rightColumn(){
		return this.coorY+1;
	}
	
	/**
	 *Returns a field relative to the field in the specified direction. 
	 *@requires field has coordinates of x y that are within the boundaries of the board.
	 *@return Field that is relative to the given field with the given direction.
	 */
	public String getRelativeField(int direction){
		if(direction==1) { 
			String result = this.coorX+ String.valueOf(this.leftColumn()) + "";
			return result;
		}else if(direction==2) {
			String result = this.coorX+ String.valueOf(this.upRow()) + "";
			return result;
		}else if(direction==3) {
			String result=this.coorX+ String.valueOf(this.rightColumn()) + "";
			return result;
		}else if(direction==4) {
			String result = this.coorX+ String.valueOf(this.downRow()) + "";
			return result;
		}
		return null;
		
	}
	
	/**
	 * Return an array of fields relative to the fields (given in the parameter) in the specified direction. 
	 * @requires Field objects have coordinates that are within the board.
	 * @return An ArrayList of Field that maintains the order of the fields parameter.
	 * @ensures The order of Field objects inside the returned ArrayList corresponds to the order of the input fields.
	 */
//	public static ArrayList<Field> getRelativeFields(int direction, ArrayList<Field> fields){
//		ArrayList<Field> arrlist= new ArrayList<Field>();
//
//		for(int i=0; i<fields.size(); i++) {
//			String x= String.valueOf(fields.get(i).getCoordinateX());
//			String y= String.valueOf(fields.get(i).getCoordinateY());
//			
//			char charValue= x.charAt(0);
//			int intValue= Integer.parseInt(y);
//			
//			Field newField= new Field(charValue, intValue,fields.get(i).getBall());
//			
//			Field f1= new Field
//					setCoordinates(getRelativeField(direction));
//			
//				arrlist.add(f1);
//		}
//		return arrlist;
//	}
	
	
	/**
	 * Check coordinate X of two fields, sorts fields by coordinate X.
	 * @returns 0 if fields have different coordinate X, 
	 *     -1 if field(eg. A3) compared to given(eg.A2) has bigger coordinate Y, 
	 *         1 if field(eg. A1) compared to given(eg. A2) has smaller coordinate Y.
	 * @ensures return!=null
	 */
	public int compareTo(Field field) {
		if(field.getCoordinateX()==this.getCoordinateX()) {
			if(field.getCoordinateY() < this.getCoordinateY()) {
				return 1;
			}
			else if (field.getCoordinateY() > this.getCoordinateY()) {
				return -1;
			}
		}return 0;
	}
	
	
	
//  --GETTERS AND SETTERS--
	public void setCoordinates(char x, int y) {
		this.coorX = x;
		this.coorY = y; 
	}
	
	/**
	 * The parameter takes in an appropriate string (see requires) and sets the variables of this.x and this.y appropriately
	 * @requires xy is in the form of "CI" where C is a character and I is an integer.  
	 * @requires xy!=null
	 * @ensures this.x!=null && this.y!=null
	 */
	public void setCoordinates(String xy){
		int charValueX= xy.charAt(0);
		int charValueY= xy.charAt(1);
		String x= String.valueOf(charValueX);
		this.coorX = x.charAt(0);
		String y= String.valueOf(charValueY);
		this.coorY = y.charAt(0);
		
		System.out.println(charValueX+charValueY);
	}
	
	public void setCoordinateX(char x){
		this.coorX = x;
	}
	
	public void setCoordinateY(int y){
		this.coorY = y;
	}
	
	public char getCoordinateX(){
		return this.coorX;
	}
	
	public int getCoordinateY(){
		return this.coorY;
	}
	public Ball getBall() {
		return this.ball;
	}
	    
	public void setBall(Ball ball) {
	    this.ball= ball;
	}
}
