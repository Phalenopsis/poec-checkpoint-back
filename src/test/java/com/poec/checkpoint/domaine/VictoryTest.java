package com.poec.checkpoint.domaine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VictoryTest {

    @Test
    void getScoreForMove() {
        Grid grid = new Grid(
                "1100001" +
                        "0000000" +
                        "0001000" +
                        "0010001" +
                        "0100001" +
                        "1000001"
        );
        Victory victory = new Victory(grid);
        assertEquals(1000, victory.getScoreForMove(new Position(3,2), '1'));
        assertEquals(100, victory.getScoreForMove(new Position(6,3), '1'));
        assertEquals(10, victory.getScoreForMove(new Position(0,0), '1'));
        assertEquals(1, victory.getScoreForMove(new Position(6,0), '1'));

    }
}