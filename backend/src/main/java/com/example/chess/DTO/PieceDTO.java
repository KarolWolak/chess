package com.example.chess.DTO;

import com.example.chess.common.Color;
import com.example.chess.common.Type;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PieceDTO {
  String x;
  String y;
  String color;
  String type;
}
