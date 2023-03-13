package com.example.chess.common;

import com.example.chess.common.pieces.Piece;
import lombok.Data;

@Data
public class Move {

  public Move(int x, int y) {
    this.x = String.valueOf(x);
    this.y = Piece.rows.get(y);
  }
  private String x;
  private String y;
}
