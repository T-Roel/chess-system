package chess;

import boardgame.BoardException;

public class ChessException extends BoardException {

	private static final long serialVersionUID = 6492993152489147354L;
	
	public ChessException(String msg) {
		super(msg);
	}

}
