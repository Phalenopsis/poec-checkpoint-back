package com.poec.checkpoint.domaine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IaHeuristicTest {

    @Test
    void heuristic() {
        IaHeuristic iaHeuristic = new IaHeuristic(
                new Grid("" +
                        "0000000" + //0
                        "0000000" + //1
                        "0000000" + //2
                        "0000000" + //3
                        "1000000" + //4
                        "1122200")); //5
        assertEquals(new Position(5, 5), iaHeuristic.chooseMove());

        IaHeuristic iaHeuristic2 = new IaHeuristic(
                new Grid("" +
                        "0000000" + //0
                        "0000000" + //1
                        "2000000" + //2
                        "1000000" + //3
                        "1221000" + //4
                        "1122211")); //5
        assertEquals(new Position(1, 3), iaHeuristic2.chooseMove());

        IaHeuristic iaHeuristic3 = new IaHeuristic(
                new Grid("" +
                        "0000000" + //0
                        "0000000" + //1
                        "0000000" + //2
                        "1000000" + //3
                        "1221000" + //4
                        "1122211")); //5
        assertEquals(new Position(0, 2), iaHeuristic3.chooseMove());
    }
}