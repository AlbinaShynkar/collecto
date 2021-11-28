package utils.NetworkProvider.Model;

import application.BoardApplication;

/**
 * Strategy interface extended with strategies of different complicity.
 * 
 * @author Other-6.4
 *
 */
public interface Strategy {
	public String getName();

	public void makeMove(BoardApplication board);

}
