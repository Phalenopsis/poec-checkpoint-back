package com.poec.checkpoint.domaine.game;

public record StartingGameDTO(
    String difficulty,
    Long playerId,
    int playerColor
) {
}
