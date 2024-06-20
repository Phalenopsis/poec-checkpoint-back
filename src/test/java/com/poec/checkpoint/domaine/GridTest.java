package com.poec.checkpoint.domaine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void move() {
        Grid grid = new Grid("0000000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000000");
        grid.move(6, 1);
        assertEquals("0000000" + "0000000" + "0000000" + "0000000" + "0000000" + "0000001", grid.toString());
    }

    @Test
    void testToString() {
    }

    @Test
    void isMoveWinner() {
        Grid grid = new Grid("0000000" + "0000000" + "0000000" + "1000000" + "1000000" + "1000000");
        assertTrue(grid.isMoveWinner(new Position(0, 2), '1'));

        Grid grid2 = new Grid("0000000" + "0000000" + "0000000" + "0000000" + "0000000" + "1111001");
        assertTrue(grid2.isMoveWinner(new Position(3, 5), '1'));
        assertTrue(grid2.isMoveWinner(new Position(2, 5), '1'));
        assertFalse(grid2.isMoveWinner(new Position(6, 5), '1'));

        Grid grid3 = new Grid(
                  "0000000" +
                        "0000000" +
                        "0001000" +
                        "0000100" +
                        "0000010" +
                        "1000001"
        );
        assertTrue(grid3.isMoveWinner(new Position(3,2 ), '1'));
        assertTrue(grid3.isMoveWinner(new Position(6,5 ), '1'));
        assertTrue(grid3.isMoveWinner(new Position(4,3 ), '1'));
        assertFalse(grid3.isMoveWinner(new Position(0,5 ), '1'));

        Grid grid4 = new Grid(
                "0000000" +
                        "0000000" +
                        "0001000" +
                        "0010000" +
                        "0100000" +
                        "1000001"
        );
        assertTrue(grid4.isMoveWinner(new Position(0,5 ), '1'));
        assertTrue(grid4.isMoveWinner(new Position(3,2 ), '1'));
        assertTrue(grid4.isMoveWinner(new Position(2,3 ), '1'));
        assertFalse(grid4.isMoveWinner(new Position(6,5 ), '1'));

        Grid grid5 = new Grid(
                "0000000" +
                        "0000000" +
                        "0000000" +
                        "0010000" +
                        "0100000" +
                        "1000001"
        );
        assertFalse(grid5.isMoveWinner(new Position(0,5 ), '1'));
    }

    @Test
    void testPossibleMoves() {
        Grid grid = new Grid(
                "0000000" +
                        "0000000" +
                        "0000000" +
                        "0000000" +
                        "0000000" +
                        "1000001"
        );
        assertEquals(7, grid.getPossibleMoves().size());
    }

}