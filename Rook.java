package com.bungogood.chess;
import java.awt.Point;
import java.util.*;

public class Rook extends Piece {

    public Rook (Point loc, boolean white, Board board) {
        super(loc, white, board);
        this.symbol = white ? '♖' : '♜';
        this.value = white ? 100 : -100;
        this.table = new int[][] {
            { 0, 0, 0, 0, 0, 0, 0, 0},
            { 1, 2, 2, 2, 2, 2, 2, 1},
            {-1, 0, 0, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 0, 0,-1},
            { 0, 0, 0, 1, 1, 0, 0, 0}
        };
    }

    public List<Move> generateMoves() {
        List<Move> moves = new ArrayList<Move>();
        moves.addAll(this.line( 0, 1));
        moves.addAll(this.line( 0,-1));
        moves.addAll(this.line( 1, 0));
        moves.addAll(this.line(-1, 0));
        return moves;
    }
}
