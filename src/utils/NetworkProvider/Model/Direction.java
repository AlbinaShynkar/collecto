package utils.NetworkProvider.Model;

public class Direction {

	private Directions direction;
	private Target target;
	private int index;

	public enum Target {
		ROW, COLUMN;
	}

	public enum Directions {
		LEFT, UP, RIGHT, DOWN;
	}
	
	public Direction(Directions direction, Target target, int index) {
		this.direction= direction;
		this.target= target;
		this.index= index;
	}

}
