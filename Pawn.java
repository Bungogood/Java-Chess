package com.bungogood.chess;
import java.awt.Point;
import java.util.*;

public class Pawn extends Piece {
    boolean firstMove = true;

    public Pawn (Point loc, boolean white, Board board) {
        super(loc, white, board);
        this.symbol = white ? '♙' : '♟';
        this.value = white ? 20 : -20;
        this.table = new int[][] {
            { 0, 0, 0, 0, 0, 0, 0, 0},
            {10,10,10,10,10,10,10,10},
            { 2, 2, 4, 6, 6, 4, 2, 2},
            { 1, 1, 2, 5, 5, 2, 1, 1},
            { 0, 0, 0, 4, 4, 0, 0, 0},
            { 1,-1,-2, 0, 0,-2,-1, 1},
            { 1, 2, 2,-4,-4, 2, 2, 1},
            { 0, 0, 0, 0, 0, 0, 0, 0}
        };
    }

    public List<Move> generateMoves() {
        List<Move> moves = new ArrayList<Move>();
        int dy = this.white ? -1 : 1;
        Point tmp = new Point(this.loc.x, this.loc.y+dy);
        Piece attacking;
        if (this.board.withinBounds(tmp)) {
            attacking = this.board.getPiece(tmp);
            if (attacking == null) {
                moves.add(this.board.movingTo(this, tmp));
                if (this.firstMove) {
                    tmp = new Point(this.loc.x, this.loc.y+2*dy);
                    attacking = this.board.getPiece(tmp);
                    if (attacking == null) {
                        moves.add(this.board.movingTo(this, tmp, true));
                    }
                }
            }
        }
        tmp = new Point(this.loc.x+1, this.loc.y+dy);
        if (this.board.withinBounds(tmp)) {
            attacking = this.board.getPiece(tmp);
            if (attacking != null && attacking.white != this.white) {
                moves.add(this.board.movingTo(this, tmp));
            }
        }
        tmp = new Point(this.loc.x+1, this.loc.y+dy);
        if (this.board.withinBounds(tmp)) {
            attacking = this.board.getPiece(tmp);
            if (attacking != null && attacking.white != this.white) {
                moves.add(this.board.movingTo(this, tmp));
            }
        }
        return moves;
    }
}