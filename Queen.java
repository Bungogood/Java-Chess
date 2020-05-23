package com.bungogood.chess;
import java.awt.Point;
import java.util.*;

public class Queen extends Piece {

    public Queen (Point loc, boolean white, Board board) {
        super(loc, white, board);
        this.symbol = white ? '♕' : '♛';
        this.value = white ? 180 : -180;
        this.table = new int[][] {
            {-4,-2,-2,-1,-1,-2,-2,-4},
            {-2, 0, 0, 0, 0, 0, 0,-2},
            {-2, 0, 1, 1, 1, 1, 0,-2},
            {-1, 0, 1, 1, 1, 1, 0,-1},
            { 0, 0, 1, 1, 1, 1, 0,-1},
            {-2, 1, 1, 1, 1, 1, 0,-2},
            {-2, 0, 1, 0, 0, 0, 0,-2},
            {-4,-2,-2,-1,-1,-2,-2,-4}
        };
    }

    public List<Move> generateMoves() {
        List<Move> moves = new ArrayList<Move>();
        moves.addAll(this.line( 0, 1));
        moves.addAll(this.line( 0,-1));
        moves.addAll(this.line( 1, 0));
        moves.addAll(this.line(-1, 0));
        moves.addAll(this.line( 1, 1));
        moves.addAll(this.line( 1,-1));
        moves.addAll(this.line(-1, 1));
        moves.addAll(this.line(-1,-1));
        return moves;
    }
}
