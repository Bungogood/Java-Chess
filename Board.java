package com.bungogood.chess;
import java.awt.Point;
import java.util.*;

public class Board {
    Stack<Move> history = new Stack<Move>();
    Piece[] pieces = {
        new   Rook(new Point(0, 7), true, this),
        new Knight(new Point(1, 7), true, this),
        new Bishop(new Point(2, 7), true, this),
        new   King(new Point(3, 7), true, this),
        new  Queen(new Point(4, 7), true, this),
        new Bishop(new Point(5, 7), true, this),
        new Knight(new Point(6, 7), true, this),
        new   Rook(new Point(7, 7), true, this),
        new   Pawn(new Point(0, 6), true, this),
        new   Pawn(new Point(1, 6), true, this),
        new   Pawn(new Point(2, 6), true, this),
        new   Pawn(new Point(3, 6), true, this),
        new   Pawn(new Point(4, 6), true, this),
        new   Pawn(new Point(5, 6), true, this),
        new   Pawn(new Point(6, 6), true, this),
        new   Pawn(new Point(7, 6), true, this),

        new   Rook(new Point(0, 0), false, this),
        new Knight(new Point(1, 0), false, this),
        new Bishop(new Point(2, 0), false, this),
        new   King(new Point(3, 0), false, this),
        new  Queen(new Point(4, 0), false, this),
        new Bishop(new Point(5, 0), false, this),
        new Knight(new Point(6, 0), false, this),
        new   Rook(new Point(7, 0), false, this),
        new   Pawn(new Point(0, 1), false, this),
        new   Pawn(new Point(1, 1), false, this),
        new   Pawn(new Point(2, 1), false, this),
        new   Pawn(new Point(3, 1), false, this),
        new   Pawn(new Point(4, 1), false, this),
        new   Pawn(new Point(5, 1), false, this),
        new   Pawn(new Point(6, 1), false, this),
        new   Pawn(new Point(7, 1), false, this)
    };
    boolean white = true;
    int score = 0;

    public void show () {
        System.out.print("");
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                for (Piece piece: this.pieces) {
                    if (!piece.taken && x == piece.loc.x && y == piece.loc.y) {
                        System.out.print(piece.symbol);
                    } else {
                        System.out.print(" ");
                    }
                }
            }
        }
    }

    public void move (Move m) {
        this.history.push(m);
        this.score -= m.getMoving().score();
        if (m.getFirstMove()) {
            Pawn p = (Pawn) m.getMoving();
            p.firstMove = false;
        }
        if (m.getAttacking() != null) {
            m.getAttacking().taken = true;
        }
        m.getMoving().loc = m.getTo();
        this.score += m.getMoving().score();
        this.white = !this.white;
    }

    public void undo () {
        Move m = this.history.pop();
        this.score -= m.getMoving().score();
        if (m.getFirstMove()) {
            Pawn p = (Pawn) m.getMoving();
            p.firstMove = true;
        }
        if (m.getAttacking() != null) {
            m.getAttacking().taken = false;
        }
        m.getMoving().loc = m.getFrom();
        this.score += m.getMoving().score();
        this.white = !this.white;
    }

    public Piece getPiece (Point loc) {
        for (Piece piece : this.pieces) {
            if (!piece.taken && piece.loc.equals(loc)) {
                return piece;
            }
        }
        return null;
    }

    public Move fromTo (Point from, Point to) {
        return new Move(from, to, this.getPiece(from), this.getPiece(to), false);
    }

    public Move movingTo (Piece moving, Point to) {
        return new Move((Point) moving.loc.clone(), to, moving, this.getPiece(to), false);
    }

    public Move movingTo (Piece moving, Point to, boolean firstMove) {
        return new Move((Point) moving.loc.clone(), to, moving, this.getPiece(to), firstMove);
    }

    public boolean withinBounds (Point loc) {
        return loc.x >= 0 && loc.y >= 0 && loc.x < 8 && loc.y < 8;
    }

    public List<Move> generateMoves() {
        List<Move> moves = new ArrayList<Move>();
        for (Piece piece: this.pieces) {
            if (!piece.taken && this.white == piece.white) {
                moves.addAll(piece.generateMoves());
            }
        }
        return moves;
    }

    public boolean gameover () {
        return false;
    }

    public int moveScore (Move move) {
        this.move(move);
        int score = this.score;
        this.undo();
        return score;
    }
    /*
    public int score() {
        int total = 0;
        for (int i = 0; i < this.Pieces.length; i++) {
            if (!this.Pieces[i].taken) {
                total += this.Pieces[i].score;
            }
        }
        return -total;
    }

    public void show() {
        for (let i=0; i < 64; i++) {
            setcell(i, "");
        }
        for (let i=0; i < this.Pieces.length; i++) {
            if (!this.Pieces[i].taken) {
                setcell(this.Pieces[i].i, this.Pieces[i].char);
            }
        }
    }

    move(move) {
        let [from, to] = split(move);
        let moving = this.getPiece(from);
        let taking = this.getPiece(to);
        if (taking != null) {
            taking.taken = true;
        }
        if (moving instanceof Pawn) {
            moving.firstMove = false;
        }
        moving.i = to;
        this.white = !this.white;
    }

    boolean gameover() {
        return this.generateMoves().length == 0;
    }

    int score() {
        int total = 0;
        for (int i = 0; i < this.Pieces.length; i++) {
            if (!this.Pieces[i].taken) {
                total += this.Pieces[i].score;
            }
        }
        return -total;
    }
     */
}