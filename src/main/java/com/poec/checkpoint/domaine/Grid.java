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

    private List<Integer> possibleMoves = new ArrayList<>();

    @Getter
    private char[][] grid = new char[HEIGHT][WIDTH];

   public Grid(String string) {
       for (int i = 0; i < HEIGHT; i++) {
           String subString = string.substring(i * WIDTH, (i + 1) * WIDTH);

           for(int j = 0; j < WIDTH; j++) {
               grid[i][j] = subString.charAt(j);
               if (j == 0 && grid[i][j] == '0') possibleMoves.add(i);
           }
       }
   }

   char getColorAtPosition(Position position) {
       if(position.isValid(this)) return grid[position.getY()][position.getX()];
       throw new PositionOutOfGridException("Position : " + position + " is not valid");
   }

   public int move(int column, int color) {
       char charColor = (char)(color + '0');
       for(int i = HEIGHT - 1; i >= 0; i -= 1) {
           if(grid[i][column] == '0') {
               grid[i][column] = charColor;
               Position lastMove = new Position(column, i);
               //vérifier si le coup est gagnant
               if(isMoveWinner(lastMove, charColor)) return color;
               if(i == 0) possibleMoves.remove((Integer) column);
               if(possibleMoves.isEmpty()) return -1;
               return 0;
           }
       }
       return 0;
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
       char iaColor = 2;
       switch (difficulty) {
           case "easy":
               return playIaEasy(iaColor);
           default:
               throw new RuntimeException("not yet implemented");
       }
    }

    public int playIaEasy(char iaColor) {
       int column = possibleMoves.get(new Random().nextInt(possibleMoves.size()));
       return move(column, iaColor);
    }
}
