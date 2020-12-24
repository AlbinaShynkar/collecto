package model;

public class Ball {
	public static enum Colors{RED, ORANGE, YELLOW, GREEN, BLUE, PINK, EMPTY};
	Colors color;
	Field field;
	
//  --CONSTRUCTORS--
	/**
	 * Constructor of a ball with certain color or color empty. //TO-DO(Reference to final report colors of balls)
	 * @param color
	 */
	public Ball(Colors color) {
		this.color = color;
	}

//  --ORIGINAL METHODS--


	
	
//  --GETTERS AND SETTERS--
	public void setField(Field field) {
		this.field= field;
	}
	
	public Field getField() {
		return this.field;
	}
	
	
	public Colors getColor() {
		return this.color;
	}
	
	public void setColor(Colors color) {
		this.color = color; 
	}
	
	/**
	 * @return - The first character of the color.
	 */
	public String toString() {
		if(color==Colors.RED) {
			return "R";
		}
		if(color==Colors.ORANGE) {
			return "O";
		}
		if(color==Colors.YELLOW) {
			return "Y";
		}
		if(color==Colors.GREEN) {
			return "G";
		}
		if(color==Colors.BLUE) {
			return "B";
		}
		if(color==Colors.PINK) {
			return "P";
		}
		return "E";
	}

}
