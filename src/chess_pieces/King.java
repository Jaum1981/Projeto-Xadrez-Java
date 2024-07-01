package chess_pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

	private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch){
        super(board, color);
		this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	private boolean testRooKCastling(Position position) { //testa se a torre esta hápita para roque
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p!= null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

    @Override
    public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);

		//Roque:
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			//roque pequeno
			Position positionRook1 = new Position(position.getRow(), position.getColumn() + 3);
			if (testRooKCastling(positionRook1)){
				Position p1 = new Position(position.getRow(), position.getColumn() + 1); // casa da direita do rei
				Position p2 = new Position(position.getRow(), position.getColumn() + 2); // casa da direita + 1do rei
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null){
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}
			//roque grande
			Position positionRook2 = new Position(position.getRow(), position.getColumn() - 4);
			if (testRooKCastling(positionRook2)){
				Position p1 = new Position(position.getRow(), position.getColumn() - 1); // casa da esquerda do rei
				Position p2 = new Position(position.getRow(), position.getColumn() - 2); // casa da esquerda + 1 do rei
				Position p3 = new Position(position.getRow(), position.getColumn() - 3); // casa da esquerda + 2 do rei
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null){
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
			}
			
		}
    

        // above
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// below
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// left
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// right
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// nw
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// ne
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// sw
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// se
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

        return mat;
    }
    
}
