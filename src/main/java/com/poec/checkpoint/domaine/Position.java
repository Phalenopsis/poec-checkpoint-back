package com.poec.checkpoint.domaine;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class Position {

    private int x;
    private int y;

    public boolean isValid(Grid grid) {
        return (x >= 0 && x < grid.getWIDTH() && y >= 0 && y < grid.getHEIGHT());
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Position getNextDiagonalyRightDownPosition() {
        return new Position(getX() - 1, getY() - 1);
    }

    public Position getNextDiagonalyRightUpPosition() {
        return new Position(getX() + 1, getY() + 1);
    }

    public Position getNextDiagonalyLeftDownPosition() {
        return new Position(getX() + 1, getY() - 1);
    }

    public Position getNextDiagonalyLeftUpPosition() {
        return new Position(getX() - 1, getY() + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
