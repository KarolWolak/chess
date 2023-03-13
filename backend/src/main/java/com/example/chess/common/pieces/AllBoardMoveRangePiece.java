package com.example.chess.common.pieces;

import com.example.chess.common.Color;
import com.example.chess.common.Game;
import com.example.chess.common.Move;
import com.example.chess.common.Type;

import java.util.List;
import java.util.Optional;

public abstract class AllBoardMoveRangePiece extends Piece{

  public AllBoardMoveRangePiece(int newX, int newY, Color color, Type type) {
    super(newX, newY, color, type);
  }
   protected void addMove(int x, int y, List<Move> moves) {

    int x2 = this.getNewX() + x;
    int y2 = this.getNewY() + y;

    while (x2 >= 1 && x2 <= 8 && y2 >= 1 && y2 <= 8) {
      int x3 = x2;
      int y3 = y2;
      Optional<Piece> optionalPiece = Game.pieces.stream().filter(piece -> piece.getNewX() == x3 && piece.getNewY() == y3).findAny();
      if (optionalPiece.isEmpty()) {
        moves.add(new Move(x3, y3));
      }
      else {
        Piece piece = optionalPiece.get();
        if (piece.getColor() != this.getColor()) {
          moves.add(new Move(x3, y3));
        }
        break;
      }
      x2 += x;
      y2 += y;
    }
  }
}
