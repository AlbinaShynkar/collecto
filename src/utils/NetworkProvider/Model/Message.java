package utils.NetworkProvider.Model;

import java.io.Serializable;

public class Message<T> implements Serializable{
	private T object;
	private Action action;

	public enum Action implements Serializable {
		HELLO, LOGIN, ALREADYLOGGEDIN, LIST, NEWGAME, MOVE, GAMEOVER, QUEUE, ERROR
	}

	public Message(T object, Action action) {
		this.object = object;
		this.action = action;
	}

	public T getObject() {
		return object;
	}

	public Action getAction() {
		return action;
	}

}
