import { HttpClient } from '@angular/common/http';
import { ElementSchemaRegistry } from '@angular/compiler';
import { Component, OnInit, Renderer2, ElementRef } from '@angular/core';
import { Observable } from 'rxjs';
import { Move } from 'src/app/common/move';
import { Move2 } from 'src/app/common/move2';
import { Piece } from 'src/app/common/piece';
import { BoardService } from 'src/app/services/board.service';


@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
})
export class BoardComponent implements OnInit {

  dottedFields: Move[] = [];

  constructor(private httpClient: HttpClient,
              private boardService: BoardService, private renderer: Renderer2, private elementRef: ElementRef) { }

  ngOnInit(): void {
    this.addEventListeners();
    this.getData();
  }

  getData(): void {
    this.boardService.loadPieces().subscribe(
      (data: any) => {
      for (let piece of data) {
        const element = this.elementRef.nativeElement.querySelector(`#${piece.y}${piece.x}`);
        const className = `${piece.color}_${piece.type}`;
        this.renderer.addClass(element, className);
        // this.renderer.setProperty(element, 'innerHTML', 'AAA')
      }
    },
      (error: any) => {
        console.log("Error while loading pieces");
      });
  }

  addEventListeners(): void {
    for (let x=1; x<=8; x++){
      for (let y=0; y<=7; y++) {
        const letter = String.fromCharCode('A'.charCodeAt(0) + y);
        const element = this.elementRef.nativeElement.querySelector(`#${letter}${x}`);
        this.renderer.listen(element, 'click', event => {
        
          
          
          for (let move of this.dottedFields) {
            const element = this.elementRef.nativeElement.querySelector(`#${move.y}${move.x}`);
            this.renderer.setProperty(element, 'innerHTML', '');
          }

          

          const foundMove = this.dottedFields.find(move => move.x == element.id[1] && move.y == element.id[0]);
          if (foundMove) {
            console.log("FOUND MOVE METHOD");
            this.boardService.move(element.id).subscribe(data => {
              for (let move of data) {
                // console.log(`Piece: ${move.piece}`)
                // console.dir(`Piece: ${JSON.stringify(move.piece)}`)
                // console.log(`Old x: ${move.oldX}`)
                // console.log(`Old y: ${move.oldY}`)
                // console.log(`New x: ${move.newX}`)
                // console.log(`New Y: ${move.newY}`)
                // console.log(`New Y: ${move.newY}`)
                // console.log(`Captured css class: ${move.capturedPieceCssClass}`)
                this.dottedFields = [];
                const element2 = this.elementRef.nativeElement.querySelector(`#${move.oldY}${move.oldX}`);
                let className = `${move.piece.color}_${move.piece.type}`.toLowerCase();
                this.renderer.removeClass(element2, className);

                const element = this.elementRef.nativeElement.querySelector(`#${move.newY}${move.newX}`);
                if (move.capturedPieceCssClass.length) {
                  this.renderer.removeClass(element, move.capturedPieceCssClass);
                }
                className = `${move.piece.color}_${move.piece.type}`.toLowerCase();
                this.renderer.addClass(element, className);
              }
            });
          }

          else {
            console.log("ELSE");
            this.dottedFields = [];
            this.boardService.showMoves(element.id).subscribe(data => {
             
              for (let move of data) {
                this.dottedFields.push(move);
                const element = this.elementRef.nativeElement.querySelector(`#${move.y}${move.x}`);
                this.renderer.setProperty(element, 'innerHTML', '<img src="assets/dot.png"/>');
              }
            })
          }
      
      })
      }
    }
  }
}

