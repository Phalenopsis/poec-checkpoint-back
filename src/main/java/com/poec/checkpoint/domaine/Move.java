package com.poec.checkpoint.domaine;

import lombok.Getter;

@Getter
public class Move {
    private int column;
    private int color;

    public Move(int column, int color) {
        this.column = column;
        this.color = color;
    }
}
