package com.bungogood.chess;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Player p1 = new Player(board, true);
        Player p2 = new Minimax(board, false);
    }
}