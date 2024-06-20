package com.poec.checkpoint.domaine;

import com.poec.checkpoint.domaine.exceptions.PositionOutOfGridException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
public class Grid {
    @Getter
    private int WIDTH = 7;
    @Getter
    private int HEIGHT = 6;

    private int SIZE_TO_WIN = 4;

    @Getter
    private List<Integer> possibleMoves = new ArrayList<>();

    @Getter
    private char[][] grid = new char[HEIGHT][WIDTH];

   public Grid(String string) {
       for (int i = 0; i < HEIGHT; i++) {
           String subString = string.substring(i * WIDTH, (i + 1) * WIDTH);

           for(int j = 0; j < WIDTH; j++) {
               grid[i][j] = subString.charAt(j);
               if (i == 0 && grid[i][j] == '0') possibleMoves.add(j);
           }
       }
   }


   public char getColorAtPosition(Position position) {
       if(position.isValid(this)) return grid[position.getY()][position.getX()];
       throw new PositionOutOfGridException("Position : " + position + " is not valid");
   }

   private void setColorAtPosition(Position position, char color) {
       grid[position.getY()][position.getX()] = color;
   }

   public int move(int column, int color) {
       char charColor = (char)(color + '0');
       Position nextMove = findEmptyCaseInColumn(column);
       setColorAtPosition(nextMove, charColor);
       return getResultOfMove(color, nextMove, charColor);

   }

    private int getResultOfMove(int color, Position movePlayed, char charColor) {
        if(isMoveWinner(movePlayed, charColor)) return color;
        if(movePlayed.getY() == 0) possibleMoves.remove(movePlayed.getX());
        if(possibleMoves.isEmpty()) return -1;
        return 0;
    }

    public Position findEmptyCaseInColumn(int column) {
       for(int i = HEIGHT - 1; i >= 0; i -= 1) {
           Position position = new Position(column, i);
           if(getColorAtPosition(position) == '0') {
               return position;
           }
       }
       throw new RuntimeException("No empty case in column");
   }

    @Override
    public String toString() {
       String str = "";
        for (int i = 0; i < HEIGHT; i++) {
            str += new String(grid[i]);
        }
        return str;
    }

    public boolean isMoveWinner(Position position, char color) {
        Victory victory = new Victory(this);
        return victory.isMoveWinner(position, color);
    }

    public int playIa(String difficulty) {
       int iaColor = 2;
       switch (difficulty) {
           case "easy":
               return playIaEasy(iaColor);
           case "medium":
               return playIaMedium(iaColor);
           default:
               throw new RuntimeException("not yet implemented");
       }
    }

    public int playIaEasy(int iaColor) {
       int column = possibleMoves.get(new Random().nextInt(possibleMoves.size()));
       return move(column, iaColor);
    }

    public int playIaMedium(int iaColor) {
       IaHeuristic iaHeuristic = new IaHeuristic(this);
       Position positionToPlay =  iaHeuristic.chooseMove();
       return move(positionToPlay.getX(), iaColor);
    }
}
