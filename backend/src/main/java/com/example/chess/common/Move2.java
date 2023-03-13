package com.example.chess.common;

import com.example.chess.common.pieces.Piece;
import lombok.Data;

@Data
public class Move2 {

  public Move2(Piece piece, int newX, int newY) {
    this.piece = piece;
    this.newX = Integer.toString(newX);
    this.newY = Piece.rows.get(newY);
  }
  private Piece piece;
  private String oldX;
  private String oldY;
  private String newX;
  private String newY;
  private String capturedPieceCssClass="";
}
