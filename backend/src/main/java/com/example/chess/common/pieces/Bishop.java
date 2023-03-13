package com.example.chess.common.pieces;

import com.example.chess.common.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bishop extends AllBoardMoveRangePiece {

  public Bishop(int newX, int newY, Color color, Type type) {
    super(newX, newY, color, type);
  }

  @Override
  public List<Move> getPossibleMoves() {
    List<Move> moves = new ArrayList<>();
    this.addMove(-1, 1, moves);
    this.addMove(1, 1, moves);
    this.addMove(1, -1, moves);
    this.addMove(-1, -1, moves);
    return moves;
  }

  public List<Move> getPossibleMoves2() {
    List<Move> moves = new ArrayList<>();
    this.addMove(-1, 1, moves);
    this.addMove(1, 1, moves);
    this.addMove(1, -1, moves);
    this.addMove(-1, -1, moves);
    nowaMetoda(moves);
    return moves;
  }
}
