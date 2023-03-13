package com.example.chess.common;

import com.example.chess.common.pieces.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Game {
  public static List<Piece> pieces = new ArrayList<>();

  public static Color turn = Color.WHITE;
  public static Piece lastPiece;

  public static void changeTurn() {
    if (turn == Color.WHITE) {
      turn = Color.BLACK;
    }
    else {
      turn = Color.WHITE;
    }
  }

  private Game() {
    for (int i=1; i<=8; i++) {
      pieces.add(new Pawn(i,2,Color.WHITE, Type.PAWN));
      pieces.add(new Pawn(i,7,Color.BLACK, Type.PAWN));
    }
    pieces.add(new King(5,1,Color.WHITE,Type.KING));
    pieces.add(new King(5,8,Color.BLACK,Type.KING));

    pieces.add(new Rook(1,1,Color.WHITE,Type.ROOK));
    pieces.add(new Rook(8,1,Color.WHITE,Type.ROOK));
    pieces.add(new Rook(1,8,Color.BLACK,Type.ROOK));
    pieces.add(new Rook(8,8,Color.BLACK,Type.ROOK));

    pieces.add(new Knight(2,1,Color.WHITE,Type.KNIGHT));
    pieces.add(new Knight(7,1,Color.WHITE,Type.KNIGHT));
    pieces.add(new Knight(2,8,Color.BLACK,Type.KNIGHT));
    pieces.add(new Knight(7,8,Color.BLACK,Type.KNIGHT));

    pieces.add(new Bishop(3,1,Color.WHITE, Type.BISHOP));
    pieces.add(new Bishop(6,1,Color.WHITE, Type.BISHOP));
    pieces.add(new Bishop(3,8,Color.BLACK, Type.BISHOP));
    pieces.add(new Bishop(6,8,Color.BLACK, Type.BISHOP));

    pieces.add(new Queen(4,1,Color.WHITE, Type.QUEEN));
    pieces.add(new Queen(4,8,Color.BLACK, Type.QUEEN));
  }
}
