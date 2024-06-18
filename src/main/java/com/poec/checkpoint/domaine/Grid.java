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

    @Getter
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
       int column;
       while(isMoveIllegal(column = new Random().nextInt(width)));
       return move(column, iaColor);
    }

    private boolean isMoveIllegal(int column){
        return !(grid[0][column] == '0');
    }
}
