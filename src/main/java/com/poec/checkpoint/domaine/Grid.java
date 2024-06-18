package com.poec.checkpoint.domaine;

import com.poec.checkpoint.domaine.exceptions.PositionOutOfGridException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Random;

@AllArgsConstructor
public class Grid {
    @Getter
    private int width = 7;
    @Getter
    private int height = 6;

    private int SIZE_TO_WIN = 4;

    private char[][] grid = new char[height][width];

   public Grid(String string) {
       for (int i = 0; i < height; i++) {
           String subString = string.substring(i * width, (i + 1) * width);
           int h = 7 - i;
           for(int j = 0; j < width; j++) {
               grid[i][j] = subString.charAt(j);
           }
       }
   }

   char getColorAtPosition(Position position) {
       if(position.isValid(this)) return grid[position.getY()][position.getX()];
       throw new PositionOutOfGridException("Position : " + position + " is not valid");
   }

   public int move(int column, int color) {
       char charColor = (char)(color + '0');
       for(int i = height - 1; i >= 0; i -= 1) {
           if(grid[i][column] == '0') {
               grid[i][column] = charColor;
               Position lastMove = new Position(column, i);
               //vérifier si le coup est gagnant
               if(isMoveWinner(lastMove, charColor)) return color;
               return 0;
           }
       }
       return 0;
   }

    @Override
    public String toString() {
       String str = "";
        for (int i = 0; i < height; i++) {
            str += new String(grid[i]);
        }
        return str;
    }

    public boolean isMoveWinner(Position position, char color) {
        if(isVerticalWinner(position, color)) return true;
        if(isHorizontalWinner(position, color)) return true;
        if(isDiagonalRightDownWinner(position, color)) return true;

       return isDiagonalLeftWinner(position, color);
    }

    private boolean isVerticalWinner(Position position, char color) {

       int numberOfToken = 1;
       for(int i = 1; i < 4; i++) {
           if((position.getY() + i) < 6 && grid[position.getY() + i][position.getX()] == color) {
               numberOfToken += 1;
           } else {
               break;
           }
       }
       return numberOfToken >= 4;
    }

    private boolean isHorizontalWinner(Position position, char color) {
       int numberOfToken = 1;
       int i = -1;
       int x = position.getX();
       int index = -1000;

       while((index = x + i) >= 0 && grid[position.getY()][index] == color ) {
           i -= 1;
           numberOfToken += 1;
       }
       i = 1;

       while(x + i < width && grid[position.getY()][x + i] == color) {
           i += 1;
           numberOfToken += 1;
       }

       return numberOfToken >= 4;
    }

    public boolean isDiagonalRightDownWinner(Position position, char color) {
       int numberOfTokens = 1;
       Position nextPosition = new Position(position.getX(), position.getY());
       char colorFound;
       while(
               (nextPosition = getNextDiagonalyRightDownPosition(nextPosition)).isValid(this) &&
                       (colorFound = getColorAtPosition(nextPosition)) == color) {
           numberOfTokens += 1;
       }
        nextPosition = new Position(position.getX(), position.getY());
        while(
                (nextPosition = getNextDiagonalyRightUpPosition(nextPosition)).isValid(this) &&
                        (colorFound = getColorAtPosition(nextPosition)) == color) {
            numberOfTokens += 1;
        }
       return numberOfTokens >= 4;
    }

    private Position getNextDiagonalyRightDownPosition(Position position) {
       return new Position(position.getX() - 1, position.getY() - 1);
    }

    private Position getNextDiagonalyRightUpPosition(Position position) {
       return new Position(position.getX() + 1, position.getY() + 1);
    }

    private boolean isDiagonalLeftWinner(Position position, char color) {
       int numberOfTokens = 1;
       Position nextPosition = new Position(position.getX(), position.getY());

       while((nextPosition = getNextDiagonalyLeftDownPosition(nextPosition)).isValid(this)
       && getColorAtPosition(nextPosition) == color) {
           numberOfTokens += 1;
       }
        nextPosition = new Position(position.getX(), position.getY());
       while((nextPosition = getNextDiagonalyLeftUpPosition(nextPosition)).isValid(this)
       && getColorAtPosition(nextPosition) == color) {
           numberOfTokens += 1;
       }
       return numberOfTokens >= 4;
    }

    private Position getNextDiagonalyLeftDownPosition(Position position) {
       return new Position(position.getX() + 1, position.getY() - 1);
    }

    private Position getNextDiagonalyLeftUpPosition(Position position) {
       return new Position(position.getX() - 1, position.getY() + 1);
    }

    public void playIa(String difficulty) {
       char iaColor = 2;
       switch (difficulty) {
           case "easy":
               move(new Random().nextInt(width), iaColor);
               break;
           default:
               throw new RuntimeException("not yet implemented");

       }
    }
}
