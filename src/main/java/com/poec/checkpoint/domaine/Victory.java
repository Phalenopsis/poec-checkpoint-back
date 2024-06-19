package com.poec.checkpoint.domaine;

public class Victory {
    Grid grid;

    public Victory(Grid grid) {
        this.grid = grid;
    }

    public boolean isMoveWinner(Position position, char color) {
        if(isVerticalWinner(position, color) >= 4 ) return true;
        if(isHorizontalWinner(position, color) >= 4) return true;
        if(isDiagonalRightDownWinner(position, color) >= 4) return true;

        return isDiagonalLeftWinner(position, color) >= 4;
    }

    private int isVerticalWinner(Position position, char color) {
        int numberOfToken = 1;
        for(int i = 1; i < 4; i++) {
            if((position.getY() + i) < 6 && grid.getGrid()[position.getY() + i][position.getX()] == color) {
                numberOfToken += 1;
            } else {
                break;
            }
        }
        return numberOfToken;
    }

    private int isHorizontalWinner(Position position, char color) {
        int numberOfToken = 1;
        int i = -1;
        int x = position.getX();
        int index = -1000;

        while((index = x + i) >= 0 && grid.getGrid()[position.getY()][index] == color ) {
            i -= 1;
            numberOfToken += 1;
        }
        i = 1;

        while(x + i < grid.getWIDTH() && grid.getGrid()[position.getY()][x + i] == color) {
            i += 1;
            numberOfToken += 1;
        }

        return numberOfToken;
    }

    private int isDiagonalRightDownWinner(Position position, char color) {
        int numberOfTokens = 1;
        Position nextPosition = new Position(position.getX(), position.getY());
        char colorFound;
        while(
                (nextPosition = nextPosition.getNextDiagonalyRightDownPosition()).isValid(grid) &&
                        (colorFound = grid.getColorAtPosition(nextPosition)) == color) {
            numberOfTokens += 1;
        }
        nextPosition = new Position(position.getX(), position.getY());
        while(
                (nextPosition = nextPosition.getNextDiagonalyRightUpPosition()).isValid(grid) &&
                        (colorFound = grid.getColorAtPosition(nextPosition)) == color) {
            numberOfTokens += 1;
        }
        return numberOfTokens;
    }

    private int isDiagonalLeftWinner(Position position, char color) {
        int numberOfTokens = 1;
        Position nextPosition = new Position(position.getX(), position.getY());

        while((nextPosition = nextPosition.getNextDiagonalyLeftDownPosition()).isValid(grid)
                && grid.getColorAtPosition(nextPosition) == color) {
            numberOfTokens += 1;
        }
        nextPosition = new Position(position.getX(), position.getY());
        while((nextPosition = nextPosition.getNextDiagonalyLeftUpPosition()).isValid(grid)
                && grid.getColorAtPosition(nextPosition) == color) {
            numberOfTokens += 1;
        }
        return numberOfTokens;
    }
}
