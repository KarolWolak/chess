package com.example.chess.controller;

import com.example.chess.DTO.PieceDTO;
import com.example.chess.Service;
import com.example.chess.common.*;
import com.example.chess.common.pieces.Piece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class controller {

  @Autowired
  Service service;

  @GetMapping("/loadPieces")
  List<PieceDTO> loadPieces() {
    return service.loadPieces();
  }

  @PostMapping("/getMoves")
  List<Move> getMoves(@RequestBody String position) {
    return service.getMoves(position);
  }

  @PostMapping("/move")
  List<Move2> move(@RequestBody String position) {
    return service.move(position);
  }
}
