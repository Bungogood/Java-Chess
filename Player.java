package com.bungogood.chess;

import java.awt.*;

public class Player {
    boolean white;
    Board board;

    public Player (Board board, boolean white) {
        this.board = board;
        this.white = white;
    }

    public Move Turn () {
        return this.board.fromTo(new Point(0, 0), new Point(1, 1));
    }
}
