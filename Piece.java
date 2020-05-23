package com.bungogood.chess;
import java.awt.Point;
import java.util.*;

public class Piece {
    boolean white;
    boolean taken;
    int[][] table;
    Board board;
    char symbol;
    Point loc;
    int value;

    public Piece (Point loc, boolean white, Board board) {
        this.loc = loc;
        this.white = white;
        this.board = board;
        this.taken = false;
    }

    public List<Move> generateMoves () {return null;}

    public int score () {
        if (this.white) {
            return this.value + this.table[this.loc.y][this.loc.x];
        } else {
            return this.value - this.table[7-this.loc.y][7-this.loc.x];
        }
    }
    
    public List<Move> line(int dx, int dy) {
        List<Move> moves = new ArrayList<Move>();
        Point tmp = new Point(this.loc.x+dx, this.loc.y+dy);
        Piece attacking = this.board.getPiece(tmp);
        while (this.board.withinBounds(tmp) && attacking == null) {
            moves.add(this.board.movingTo(this, tmp));
            tmp = new Point(tmp.x+dx, tmp.y+dy);
            attacking = this.board.getPiece(tmp);
        }
        if (this.board.withinBounds(tmp) && attacking != null && attacking.white != this.white) {
            moves.add(this.board.movingTo(this, tmp));
        }
        return moves;
    }
}