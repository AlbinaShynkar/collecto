package utils.NetworkProvider.Model;


import application.BoardApplication;

public class NaiveStrategy implements Strategy {

	private static final String NAME = "Naive";

	@Override
	public void makeMove(BoardApplication board) {

	}

	@Override
	public String getName() {
		return NAME;
	}

}
