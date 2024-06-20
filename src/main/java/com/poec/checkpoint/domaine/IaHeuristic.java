package com.poec.checkpoint.domaine;

import java.util.*;

public class IaHeuristic {
    private Grid grid;
    private Stack<Move> movePlayed = new Stack<>();

    public IaHeuristic(Grid grid) {
        this.grid = grid;
    }

    public void heuristic() {

    }

    public Position chooseMove() {

        List<Position> optimizedMoves = getOptimizedMoves();
        return optimizedMoves.get(new Random().nextInt(optimizedMoves.size()));
    }

    private int tryPlayMove(int column, int color) {
        movePlayed.add(new Move(column, color));
        return -1;
    }

    public List<Position> getOptimizedMoves() {
        List<Position> optimizedMoves = new ArrayList<>();
        int score = 0;
        List<Integer> possibleMoves = grid.getPossibleMoves();
        for(int column: possibleMoves) {
            Position positionTested = grid.findEmptyCaseInColumn(column);
            Victory victory = new Victory(grid);
            int scoreTested = victory.getScoreForMove(positionTested, '2');
            int scoreBlocked = victory.getScoreForMove(positionTested, '1');
            if(scoreBlocked == 1000) {
                return Collections.singletonList(positionTested);
            }
            if(scoreTested > score) {
                optimizedMoves.clear();
                optimizedMoves.add(positionTested);
                score = scoreTested;
            } else if (scoreTested == score) {
                optimizedMoves.add(positionTested);
            }
        }
        return optimizedMoves;
    }


}
