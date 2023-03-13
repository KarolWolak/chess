package com.example.chess;

import com.example.chess.DTO.PieceDTO;
import com.example.chess.common.*;
import com.example.chess.common.pieces.*;

import java.util.*;

@org.springframework.stereotype.Service
public class Service {

  private static Map<Character, Integer> rows;

  public Service() {
    if (rows == null) {
      rows = new HashMap<>();
      rows.put('A',1);
      rows.put('B',2);
      rows.put('C',3);
      rows.put('D',4);
      rows.put('E',5);
      rows.put('F',6);
      rows.put('G',7);
      rows.put('H',8);
    }
  }

  public List<Move> getMoves(String position) {
    int x = Integer.parseInt(position.split("")[1]);
    int y = rows.get(position.charAt(0));
    List<Move> moves = new ArrayList<>();
    Optional<Piece> pieceOptional = Game.pieces.stream().filter(piece -> piece.getNewX() == x && piece.getNewY() == y && piece.getColor() == Game.turn).findFirst();

    if (pieceOptional.isPresent()) {
      Piece piece = pieceOptional.get();
      Game.lastPiece = piece;
      return piece.getPossibleMoves2();
    }
    else {
      Game.lastPiece = null;
      return moves;
    }
  }

  public List<Move2> move(String position) {
    int x = Integer.parseInt(position.split("")[1]);
    int y = rows.get(position.charAt(0));
    return Game.lastPiece.move(x, y);
  }


  public List<PieceDTO> loadPieces() {
    List<PieceDTO> piecesDTO = new ArrayList<>();
    Game.pieces.forEach(piece -> piecesDTO.add(piece.returnDTO()));
    return piecesDTO;
  }
}
