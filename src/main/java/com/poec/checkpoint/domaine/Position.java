package com.poec.checkpoint.domaine;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Position {

    private int x;
    private int y;

    public boolean isValid(Grid grid) {
        return (x >= 0 && x < grid.getWidth() && y >= 0 && y < grid.getHeight());
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
