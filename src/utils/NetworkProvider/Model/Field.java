package utils.NetworkProvider.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Field implements Comparable<Field>, Serializable {
	/**
	 * Stored character value of X.
	 * 
	 * @requires Stored character value of X must be of the values A,B,C,D,E,F,G
	 *           (capital)
	 */
	private char coorX;
	/**
	 * It is assumed that the value Y matches value of X.
	 * 
	 * @requires Stored value of Y value must be : 1<=y<=7.
	 */
	private int coorY;
	/*
	 * 
	 */
	private Color color;

	// --CONSTRUCTORS--
	/**
	 * Creates an empty field with given coordinates.
	 * 
	 * @param point
	 * @param marble
	 */
	public Field(char CoorX, int CoorY, Color color) {
		this.coorX = CoorX;
		this.coorY = CoorY;
		this.color = color;
	}

//  --ORIGINAL-METHODS--
	/**
	 * Converts the x and y representations into a string representation for visual
	 * output.
	 */
	public String toString() {
		String x = String.valueOf(this.coorX);
		String y = String.valueOf(this.coorY);
		return x + y + color.toString() + "    ";
	}

//  --GETTERS AND SETTERS--
	public char getCoordinateX() {
		return this.coorX;
	}

	public int getCoordinateY() {
		return this.coorY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public int compareTo(Field field) {
		if (getCoordinateX() > field.getCoordinateX() || getCoordinateY() > field.getCoordinateY()) {
			return 1;
		} else if (getCoordinateX() < field.getCoordinateX() || getCoordinateY() < field.getCoordinateY()) {
			return -1;
		} else {
			return 0;
		}
	}

}
