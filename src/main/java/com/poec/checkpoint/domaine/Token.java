package com.poec.checkpoint.domaine;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Token {
    Position position;
    @Getter
    int color;

}
