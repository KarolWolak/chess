import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Move } from '../common/move';
import { Move2 } from '../common/move2';
import { Piece } from '../common/piece';


@Injectable({
  providedIn: 'root'
})
export class BoardService {

  constructor(private httpClient: HttpClient) { }


  loadPieces(): Observable<Piece[]> {
    return this.httpClient.get<Piece[]>("http://localhost:8080/loadPieces");
  }

  showMoves(id: string): Observable<Move[]> {
    return this.httpClient.post<Move[]>("http://localhost:8080/getMoves", id);
  }

  move(id: string): Observable<Move2[]> {
    return this.httpClient.post<Move2[]>("http://localhost:8080/move", id);
  }
}