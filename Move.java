package com.bungogood.chess;
import java.awt.Point;

public class Move {
    private Piece attacking;
    private Piece moving;
    private Point from;
    private Point to;
    private boolean firstMove;
    private int score;

    public Move (Point from, Point to, Piece moving, Piece attacking, boolean firstMove) {
        this.from = from;
        this.to = to;
        this.moving = moving;
        this.attacking = attacking;
        this.firstMove = firstMove;
        moving.board.move(this);
        this.score = moving.board.score;
        moving.board.undo();
    }

    public boolean equals (Move m) {
        return  this.attacking == m.attacking &&
                this.moving == m.moving &&
                this.from.equals(m.from) &&
                this.to.equals(m.to) &&
                this.firstMove == m.firstMove;
    }

    public Point getFrom() {
        return this.from;
    }

    public Point getTo() {
        return this.to;
    }

    public Piece getMoving() {
        return this.moving;
    }

    public Piece getAttacking() {
        return this.attacking;
    }

    public boolean getFirstMove() {
        return this.firstMove;
    }

    public int getScore() {
        return this.score;
    }

}