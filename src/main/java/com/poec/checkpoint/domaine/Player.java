package com.poec.checkpoint.domaine;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Player {
    private int color;
    private Player adversary;

    Player() {
        this.color = 0;
    }

    int play(Grid grid, int column) {
        return 0;
    }
}
