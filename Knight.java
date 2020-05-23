package com.bungogood.chess;
import java.awt.Point;
import java.util.*;

public class Knight extends Piece {

    public Knight (Point loc, boolean white, Board board) {
        super(loc, white, board);
        this.symbol = white ? '♘' : '♞';
        this.value = white ? 60 : -60;
        this.table = new int[][] {
            {-10,-8,-6,-6,-6,-6,-8,-10},
            { -8,-4, 0, 0, 0, 0,-4, -8},
            { -6, 0, 2, 3, 3, 2, 0, -6},
            { -6, 1, 3, 4, 4, 3, 1, -6},
            { -6, 0, 3, 4, 4, 3, 0, -6},
            { -6, 1, 2, 3, 3, 2, 1, -6},
            { -8,-4, 0, 1, 1, 0,-4, -8},
            {-10,-8,-6,-6,-6,-6,-8,-10}
        };
    }

    public List<Move> generateMoves() {
        List<Move> moves = new ArrayList<Move>();
        this.checkMove(new Point(this.loc.x+1, this.loc.y+2), moves);
        this.checkMove(new Point(this.loc.x+1, this.loc.y-2), moves);
        this.checkMove(new Point(this.loc.x-1, this.loc.y+2), moves);
        this.checkMove(new Point(this.loc.x-1, this.loc.y-2), moves);
        this.checkMove(new Point(this.loc.x+2, this.loc.y+1), moves);
        this.checkMove(new Point(this.loc.x+2, this.loc.y-1), moves);
        this.checkMove(new Point(this.loc.x-2, this.loc.y+1), moves);
        this.checkMove(new Point(this.loc.x-2, this.loc.y-1), moves);
        return moves;
    }

    public void checkMove(Point tmp, List<Move> moves) {
        if (this.board.withinBounds(tmp)) {
            Piece attacking = this.board.getPiece(tmp);
            if (attacking == null || attacking.white != this.white) {
                moves.add(this.board.movingTo(this, tmp));
            }
        }
    }
}
