package com.bungogood.chess;
import java.util.*;
import java.awt.Point;

public class Minimax extends Player {
    int maxDepth = 8;

    public Minimax (Board board, boolean white) {
        super(board, white);
    }

    public Move Turn () {
        return bestMove(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public Move bestMove(int depth, int alpha, int beta) {
        List<Move> moves = board.generateMoves();
        int score;
        Move best = null;
        int bestscore;

        if (board.white) {
            bestscore = Integer.MIN_VALUE;
            for (Move move : moves) {
                board.move(move);
                score = this.minimax(depth+1, alpha, beta);
                board.undo();
                if (score > bestscore) {
                    bestscore = score;
                    best = move;
                }
                if (bestscore > alpha) {alpha = bestscore;}
                if (alpha >= beta) {break;}
            }
        } else {
            bestscore = Integer.MAX_VALUE;
            for (Move move : moves) {
                board.move(move);
                score = this.minimax(depth+1, alpha, beta);
                board.undo();
                if (score < bestscore) {
                    bestscore = score;
                    best = move;
                }
                if (bestscore < beta) {beta = bestscore;}
                if (alpha >= beta) {break;}
            }
        }
        return best;
    }

    public int minimax(int depth, int alpha, int beta) {
        if (depth == this.maxDepth || board.gameover()) {
            return board.score;
        }
        List<Move> moves = board.generateMoves();
        int score;
        Move best;
        int bestscore;

        if (board.white) {
            bestscore = Integer.MIN_VALUE;
            for (Move move : moves) {
                board.move(move);
                score = this.minimax(depth+1, alpha, beta);
                board.undo();
                if (score > bestscore) {
                    bestscore = score;
                    if (bestscore > alpha) {alpha = bestscore;}
                    if (alpha >= beta) {break;}
                }
            }
        } else {
            bestscore = Integer.MAX_VALUE;
            for (Move move : moves) {
                board.move(move);
                score = this.minimax(depth+1, alpha, beta);
                board.undo();
                if (score < bestscore) {
                    bestscore = score;
                    if (bestscore < beta) {beta = bestscore;}
                    if (alpha >= beta) {break;}
                }
            }
        }
        return bestscore;
    }
}
