package com.example.chess.common.pieces;

import com.example.chess.common.*;

import java.util.ArrayList;
import java.util.List;

public class Rook extends AllBoardMoveRangePiece {
  public boolean hasMoved = false;

  public Rook(int newX, int newY, Color color, Type type) {
    super(newX, newY, color, type);
  }

  @Override
  public List<Move> getPossibleMoves() {
    List<Move> moves = new ArrayList<>();
    this.addMove(1, 0, moves);
    this.addMove(-1, 0, moves);
    this.addMove(0, 1, moves);
    this.addMove(0, -1, moves);
    return moves;
  }

  public List<Move> getPossibleMoves2() {
    List<Move> moves = new ArrayList<>();
    this.addMove(1, 0, moves);
    this.addMove(-1, 0, moves);
    this.addMove(0, 1, moves);
    this.addMove(0, -1, moves);
    nowaMetoda(moves);
    return moves;
  }

  public List<Move2> move(int x, int y) {
    this.hasMoved = true;
    List<Move2> moves2 = new ArrayList<>();
    moves2.add(new Move2(this, x, y));
    endTurn(moves2);
    return moves2;
  }
}

