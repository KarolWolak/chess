package com.example.chess.common.pieces;

import com.example.chess.common.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Knight extends Piece {

  public Knight(int newX, int newY, Color color, Type type) {
    super(newX, newY, color, type);
  }

  @Override
  public List<Move> getPossibleMoves() {
    List<Move> moves = new ArrayList<>();
    this.addMove(-2, 1, moves);
    this.addMove(-1, 2, moves);
    this.addMove(2, 1, moves);
    this.addMove(1, 2, moves);
    this.addMove(2, -1, moves);
    this.addMove(1, -2, moves);
    this.addMove(-2, -1, moves);
    this.addMove(-1, -2, moves);
    return moves;
  }

  public List<Move> getPossibleMoves2() {
    List<Move> moves = new ArrayList<>();
    this.addMove(-2, 1, moves);
    this.addMove(-1, 2, moves);
    this.addMove(2, 1, moves);
    this.addMove(1, 2, moves);
    this.addMove(2, -1, moves);
    this.addMove(1, -2, moves);
    this.addMove(-2, -1, moves);
    this.addMove(-1, -2, moves);
    nowaMetoda(moves);
    return moves;
  }

  private void addMove(int x, int y, List<Move> moves) {
    if (this.getNewX() + x >= 1 && this.getNewX() + x <=8 && this.getNewY() + y >= 1 && this.getNewY() + y <= 8) {
      Optional<Piece> optionalPiece = Game.pieces.stream().filter(piece -> piece.getNewX() == this.getNewX() + x && piece.getNewY() == this.getNewY() + y && piece.getColor() == this.getColor()).findAny();
      if (optionalPiece.isEmpty()) {
        moves.add(new Move(this.getNewX() + x, this.getNewY() + y));
      }
    }
  }
}
