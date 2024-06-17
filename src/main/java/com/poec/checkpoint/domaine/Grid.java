package com.poec.checkpoint.domaine;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Grid {
    @Getter
    private int width;
    @Getter
    private int height;

    private int SIZE_TO_WIN = 4;

    private int[] playedCases = new int[width];

    private Token[][] grid = new Token[width][height];

    private int numberFreeCases;

    private int winner = 0;

    Grid() {
        width = 7;
        height = 6;
        initGrid();
        numberFreeCases = width * height;
    }

    void initGrid() {
        for (int column = 0; column < width; column++) {
            playedCases[column] = 0;
        }
        for (int line = 0; line < height; line++) {
            for (int column = 0; column < width; column++) {
                grid[line][column] = null;
            }
        }
    }

    boolean isFinish() {
        return winner != 0 || numberFreeCases == 0;
    }

    boolean validPlay(int played) {
        if(played < 0 || played > width) return false;
        return playedCases[played] < height;
    }

    boolean move(int played, Player player) {
        if(playedCases[played] == height) return false;
        grid[played][playedCases[played]] = new Token(new Position(played, playedCases[played]), player.getColor());
        playedCases[played] += 1;
        numberFreeCases -= 1;
        return true;
    }
}
