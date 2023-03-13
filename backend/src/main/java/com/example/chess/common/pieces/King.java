package com.example.chess.common.pieces;

import com.example.chess.common.*;
import com.example.chess.utilities.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class King extends Piece {
  public boolean hasMoved = false;

  public King(int newX, int newY, Color color, Type type) {
    super(newX, newY, color, type);
  }

  @Override
  public List<Move> getPossibleMoves() {
    List<Move> moves = new ArrayList<>();
    this.addMove(-1, 1, moves);
    this.addMove(0, 1, moves);
    this.addMove(1, 1, moves);
    this.addMove(1, 0, moves);
    this.addMove(1, -1, moves);
    this.addMove(0, -1, moves);
    this.addMove(-1, -1, moves);
    this.addMove(-1, 0, moves);
    this.addCastleMove(1, moves);
    this.addCastleMove(8, moves);
    return moves;
  }

  public List<Move> getPossibleMoves2() {
    List<Move> moves = new ArrayList<>();
    this.addMove(-1, 1, moves);
    this.addMove(0, 1, moves);
    this.addMove(1, 1, moves);
    this.addMove(1, 0, moves);
    this.addMove(1, -1, moves);
    this.addMove(0, -1, moves);
    this.addMove(-1, -1, moves);
    this.addMove(-1, 0, moves);
    this.addCastleMove(1, moves);
    this.addCastleMove(8, moves);
    nowaMetoda(moves);
    return moves;
  }

  public List<Move2> move(int x, int y) {
    this.hasMoved = true;
    List<Move2> moves2 = new ArrayList<>();
    moves2.add(new Move2(this, x, y));
    if (Math.abs(this.getNewX() - x) == 2) {
      if (x == 3) {
        Piece rook = Game.pieces.stream().filter(piece -> piece.getNewX() == 1 && this.getNewY() == piece.getNewY()).findAny().get();
        moves2.add(new Move2(rook, 4, this.getNewY()));
      }
      else {
        Piece rook = Game.pieces.stream().filter(piece -> piece.getNewX() == 8 && this.getNewY() == piece.getNewY()).findAny().get();
        moves2.add(new Move2(rook, 6, this.getNewY()));
      }

    }
    endTurn(moves2);
    return moves2;
  }

  private void addMove(int x, int y, List<Move> moves) {
    if (this.getNewX() + x >= 1 && this.getNewX() + x <= 8 && this.getNewY() + y >= 1 && this.getNewY() + y <= 8) {
      Optional<Piece> optionalPiece = Game.pieces.stream().filter(piece -> piece.getNewX() == this.getNewX() + x && piece.getNewY() == this.getNewY() + y && piece.getColor() == this.getColor()).findAny();
      if (optionalPiece.isEmpty()) {
        moves.add(new Move(this.getNewX() + x, this.getNewY() + y));
      }
    }
  }

  private void addCastleMove(int rookX, List<Move> moves) {
    Optional<Piece> optionalRook = Game.pieces.stream().filter(piece -> piece.getNewX() == rookX &&
                                                               piece.getNewY() == this.getNewY() &&
                                                               piece.getType() == Type.ROOK &&
                                                               piece.getColor() == this.getColor())
                                                               .findAny();

    if (optionalRook.isPresent()) {
      Rook rook = (Rook) optionalRook.get();
      if (!rook.hasMoved && noPiecesBetween(rook.getNewX()) && fieldsNotChecked(rook.getNewX())) {
        if (rook.getNewX() == 1) {
          moves.add(new Move(3, this.getNewY()));
        }
        else{
          moves.add(new Move(7, this.getNewY()));
        }

      }
    }
  }

  private boolean noPiecesBetween(int rookX) {

    if (rookX == 1) {
      for (int i = 2; i != this.getNewX(); i++) {
        int finalX = i;
        Optional<Piece> optionalPiece = Game.pieces.stream().filter(piece -> piece.getNewX() == finalX &&
            piece.getNewY() == this.getNewY())
          .findAny();
        if (optionalPiece.isPresent()) {
          return false;
        }
      }
    }

    else {
      for (int i = 7; i != this.getNewX(); i--) {
        int finalX = i;
        Optional<Piece> optionalPiece = Game.pieces.stream().filter(piece -> piece.getNewX() == finalX &&
            piece.getNewY() == this.getNewY())
          .findAny();
        if (optionalPiece.isPresent()) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean fieldsNotChecked(int rookX) {
    List<Piece> enemyPieces = Game.pieces.stream().filter(piece -> piece.getColor() != Game.turn).toList();
    if (rookX == 1) {
      for (int i = 3; i <= 5; i++) {
        int x = i;
        for (Piece piece: enemyPieces) {
          Optional<Move> optionalMove = piece.getPossibleMoves().stream().filter(move -> Integer.parseInt(move.getX()) == x && Constants.rowsMap.get(move.getY()) == this.getNewY()).findAny();
          if (optionalMove.isPresent()) {
            return false;
          }
        }
      }
    }
    else {
      for (int i = 7; i >= 5; i--) {
        int x = i;
        for (Piece piece: enemyPieces) {
          Optional<Move> optionalMove = piece.getPossibleMoves().stream().filter(move -> Integer.parseInt(move.getX()) == x && Constants.rowsMap.get(move.getY()) == this.getNewY()).findAny();
          if (optionalMove.isPresent()) {
            return false;
          }
        }
      }
    }
    return true;
  }

}
