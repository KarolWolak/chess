package com.example.chess.common.pieces;

import com.example.chess.DTO.PieceDTO;
import com.example.chess.common.*;
import com.example.chess.utilities.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Array;
import java.util.*;

@Data
@NoArgsConstructor
public abstract class Piece implements Cloneable{

  int newX;
  int newY;

  Color color;
  Type type;

  public static Map<Integer, String> rows;

  public Piece(int newX, int newY, Color color, Type type) {

    if (rows == null) {
      rows = new HashMap<>();
      rows.put(1,"A");
      rows.put(2,"B");
      rows.put(3,"C");
      rows.put(4,"D");
      rows.put(5,"E");
      rows.put(6,"F");
      rows.put(7,"G");
      rows.put(8,"H");
    }

    this.newX = newX;
    this.newY = newY;
    this.color = color;
    this.type = type;
  }

  public List<Move2> move(int x, int y) {
    List<Move2> moves2 = new ArrayList<>();
    moves2.add(new Move2(this, x, y));
    endTurn(moves2);
    return moves2;
  }

  public void endTurn(List<Move2> moves) {
    for (Move2 move: moves) {
      Optional<Piece> optionalPiece = Game.pieces.stream().filter(piece -> piece.getNewX() == Integer.parseInt(move.getNewX()) &&
                                                                  piece.getNewY() == Constants.rowsMap.get(move.getNewY())).findAny();
      if (optionalPiece.isPresent()) {
        Piece piece = optionalPiece.get();
        String capturedPieceCssClass = (piece.getColor() + "_" + piece.getType()).toLowerCase();
        Game.pieces.remove(optionalPiece.get());
        move.setCapturedPieceCssClass(capturedPieceCssClass);
      }


      move.setOldX(Integer.toString(move.getPiece().getNewX()));
      move.setOldY(rows.get(move.getPiece().getNewY()));
      move.getPiece().setNewX(Integer.parseInt(move.getNewX()));
      move.getPiece().setNewY(Constants.rowsMap.get(move.getNewY()));
    }
    Game.changeTurn();
  }
public void najnowsza(List<Move> moves) {
  System.out.println("najnowsza metoda");
}
  public void nowaMetoda(List<Move> moves) {
    System.out.println(this);
//    List<Move> moves2 = new ArrayList<>();
//    for (Move move: moves) {
//      List<Piece> gamePieces = new ArrayList<>();
//      for (Piece gamePiece : Game.pieces) {
//        gamePieces.add(gamePiece.clone());
//      }
//      Optional<Piece> optionalPiece = gamePieces.stream().filter(p -> p.getNewX() == Integer.parseInt(move.getX()) &&
//        p.getNewY() == Constants.rowsMap.get(move.getY())).findAny();
//      if (optionalPiece.isPresent()) {
//        Piece capturedPiece = optionalPiece.get();
//        gamePieces.remove(capturedPiece);
//      }
//      Piece piece2 = gamePieces.stream().filter(p -> p.getNewX() == this.getNewX() && p.getNewY() == this.getNewY()).findAny().get();
//      piece2.setNewX(Integer.parseInt(move.getX()));
//      piece2.setNewY(Constants.rowsMap.get(move.getY()));
//
//      List<Piece> enemyPieces = gamePieces.stream().filter(enemyPiece -> enemyPiece.getColor() != Game.turn).toList();
//      Piece king = gamePieces.stream().filter(p -> p.getColor() == Game.turn && p.getType() == Type.KING).findAny().get();
//
//      for (Piece enemyPiece: enemyPieces) {
//        List<Move> enemyMoves = enemyPiece.getPossibleMoves();
//        Optional<Move> optionalMove = enemyMoves.stream().filter(m -> Integer.parseInt(m.getX()) == king.getNewX() && Constants.rowsMap.get(m.getY()) == king.getNewY()).findAny();
//        if (optionalMove.isPresent()) {
//          System.out.println("Niemożliwy ruch: " + move);
//          moves2.add(move);
//        }
//      }
//    }
//

  }

  public abstract List<Move> getPossibleMoves2() ;








  public abstract List<Move> getPossibleMoves();



  public PieceDTO returnDTO() {
    return new PieceDTO(
      Integer.toString(this.getNewX()),
      rows.get(this.getNewY()),
      this.getColor().name().toLowerCase(),
      this.getType().name().toLowerCase()
    );
  }

  @Override
  public Piece clone() {
    try {
      Piece clone = (Piece) super.clone();
      clone.setNewX(this.getNewX());
      clone.setNewY(this.getNewY());
      clone.setColor(this.getColor());
      clone.setType(this.getType());
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }
}
