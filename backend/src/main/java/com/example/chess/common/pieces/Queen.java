package com.example.chess.common.pieces;

import com.example.chess.common.*;

import java.util.ArrayList;
import java.util.List;
public class Queen extends AllBoardMoveRangePiece {

  public Queen(int newX, int newY, Color color, Type type) {
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
    nowaMetoda(moves);
    return moves;
  }
}
