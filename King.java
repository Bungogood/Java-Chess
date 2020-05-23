package com.bungogood.chess;
import java.awt.Point;
import java.util.*;

public class King extends Piece {
    
    public King (Point loc, boolean white, Board board) {
        super(loc, white, board);
        this.symbol = white ? '♔' : '♚';
        this.value = white ? 1800 :-1800;
        this.table = new int[][] {
            {-6,-8,-8,-10,-10,-8,-8,-6},
            {-6,-8,-8,-10,-10,-8,-8,-6},
            {-6,-8,-8,-10,-10,-8,-8,-6},
            {-6,-8,-8,-10,-10,-8,-8,-6},
            {-4,-6,-6, -8, -8,-6,-6,-4},
            {-2,-4,-4, -4, -4,-4,-4,-2},
            { 4, 4, 0,  0,  0, 0, 4, 4},
            { 4, 6, 2,  0,  0, 2, 6, 4}
        };
    }

    public List<Move> generateMoves() {
        List<Move> moves = new ArrayList<Move>();
        Point tmp;
        for (int dy=-1; dy<2; dy++) {
            for (int dx =-1; dx < 2; dx++) {
                if (!(dy == 0 && dx == 0)) {
                    tmp = new Point(this.loc.x+dx, this.loc.y+dy);
                    if (this.board.withinBounds(tmp)) {
                        Piece attacking = this.board.getPiece(tmp);
                        if (attacking == null || attacking.white != this.white) {
                            moves.add(this.board.movingTo(this, tmp));
                        }
                    }
                }
            }
        }
        return moves;
    }
}
