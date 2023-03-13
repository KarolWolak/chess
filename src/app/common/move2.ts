import { Piece } from "./piece";

export class Move2 {
    constructor(public piece: Piece,
                public oldX: string,
                public oldY: string,
                public newX: string,
                public newY: string,
                public capturedPieceCssClass: string) {}
}
