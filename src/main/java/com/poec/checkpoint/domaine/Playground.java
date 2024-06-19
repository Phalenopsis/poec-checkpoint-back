package com.poec.checkpoint.domaine;

import java.util.Random;

public class Playground {

    Grid grid;
    Victory victory;

    public Playground(String gridInString) {
        this.grid = new Grid(gridInString);
    }

    public int move(int column, int color) {
        return grid.move(column, color);
    }

    public int playIa(String difficulty) {
        char iaColor = 2;
        switch (difficulty) {
            case "easy":
                return grid.playIaEasy(iaColor);
            default:
                throw new RuntimeException("not yet implemented");
        }
    }
}
