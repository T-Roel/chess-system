package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {

		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);

		// posicao acima do rei
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// posicao abaixo do rei
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// posicao a esquerda do rei
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// posicao a direita do rei
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// posicao diagonal noroeste do rei
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// posicao diagonal nordeste do rei
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// posicao diagonal sudoeste do rei
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// posicao diagonal sudeste do rei
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// jogada especial de Roque
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			// Roque pequeno
			Position posTorreUm = new Position(position.getRow(), position.getColumn() + 3);
			if (testRookCastling(posTorreUm)) {
				Position posUm = new Position(position.getRow(), position.getColumn() + 1);
				Position posDois = new Position(position.getRow(), position.getColumn() + 2);
				if (getBoard().piece(posUm) == null && getBoard().piece(posDois) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}

			// Roque grande
			Position posTorreDois = new Position(position.getRow(), position.getColumn() - 4);
			if (testRookCastling(posTorreDois)) {
				Position posUm = new Position(position.getRow(), position.getColumn() - 1);
				Position posDois = new Position(position.getRow(), position.getColumn() - 2);
				Position posTres = new Position(position.getRow(), position.getColumn() - 3);
				if (getBoard().piece(posUm) == null && getBoard().piece(posDois) == null && getBoard().piece(posTres) == null) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
			}
		}

		return mat;
	}
}
