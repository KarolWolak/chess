package com.example.chess.common.pieces;

import com.example.chess.common.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Pawn extends Piece {

  public boolean hasMoved = false;

  public Pawn(int newX, int newY, Color color, Type type) {
    super(newX, newY, color, type);
  }


  public List<Move> getPossibleMoves2() {
    List<Move> moves = new ArrayList<>();
    int directionY = this.color.equals(Color.WHITE)? 1:-1;
      this.addMove(moves, directionY);
      this.capture(moves, 1, directionY);
      this.capture(moves, -1, directionY);
    nowaMetoda(moves);
      return moves;
  }

  public List<Move> getPossibleMoves() {
    List<Move> moves = new ArrayList<>();
    int directionY = this.color.equals(Color.WHITE)? 1:-1;
    this.addMove(moves, directionY);
    this.capture(moves, 1, directionY);
    this.capture(moves, -1, directionY);
    return moves;
  }

  public List<Move2> move(int x, int y) {
    this.hasMoved = true;
    List<Move2> moves2 = new ArrayList<>();
    moves2.add(new Move2(this, x, y));
    endTurn(moves2);
    return moves2;
  }

  void addMove(List<Move> moves, int directionY) {
    if (this.getNewY() != 1 && this.getNewY() != 8) {
      Optional<Piece> optionalPiece = Game.pieces.stream().filter(piece -> piece.getNewX() == this.getNewX() && piece.getNewY() == this.getNewY() + directionY).findAny();
      if (optionalPiece.isEmpty()) {
        moves.add(new Move(this.getNewX(), this.getNewY() + directionY));
        this.moveTwo(moves, directionY);
      }
    }
  }

  private void moveTwo(List<Move> moves, int directionY) {
    if (!this.hasMoved) {
      Optional<Piece> optionalPiece = Game.pieces.stream().filter(piece -> piece.getNewX() == this.getNewX() && piece.getNewY() == this.getNewY() + 2 * directionY).findAny();
      if (optionalPiece.isEmpty()) {
        moves.add(new Move(this.getNewX(), this.getNewY() + 2 * directionY));
      }
    }
  }

  private void capture(List<Move> moves, int directionX, int directionY) {
    Optional<Piece> optionalPiece = Game.pieces.stream().filter(piece -> piece.getNewX() == this.getNewX() + directionX && piece.getNewY() == this.getNewY() + directionY && piece.color != this.getColor()).findAny();
    if (optionalPiece.isPresent()) {
      moves.add(new Move(this.getNewX() + directionX, this.getNewY() + directionY));
    }
  }
}
